package br.com.api.ibyte.infra.crosscutting.integration.route;

import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RouteBase extends RouteBuilder  {
    protected static final Map<String, AtomicBoolean> readyToProcess = new HashMap<>();
    final long _DELAY = 30000L;

    public boolean readyToProcess(){
        if (!RouteBase.readyToProcess.containsKey(this.thisClass())) {
            RouteBase.readyToProcess.put(this.thisClass(), new AtomicBoolean(true));
        }
        var ready = RouteBase.readyToProcess.get(this.thisClass()).get();
        if(ready){
            RouteBase.readyToProcess.get(this.thisClass()).set(false);
        }
        log.info("Integration - {}: {}", this.thisClass(), ready);
        return ready;
    }

    public void finishProcess(){
        log.info(this.thisClass());
        if(RouteBase.readyToProcess.containsKey(this.thisClass())){
            RouteBase.readyToProcess.get(this.thisClass()).set(true);
        }
    }

    private String thisClass(){
        return  this.getClass().getTypeName().split("_")[0];
    }

    @Override
    public void configure() throws Exception {

        onException(HttpOperationFailedException.class)
        .handled(true)
        .redeliveryDelay(_DELAY)
        .maximumRedeliveries(2)
        .useExponentialBackOff()
        .backOffMultiplier(3)
        // .useOriginalMessage()
        // .useOriginalBody()
        .onExceptionOccurred(e -> {
            log.warn("{} - {}º Tentativa sem sucesso ao enviar dados para endpoint {} ", e.getProperties().getOrDefault("code_error", "5555").toString(),e.getIn().getHeader(e.REDELIVERY_COUNTER),e.getProperty(Exchange.TO_ENDPOINT));
        })
        .logExhaustedMessageHistory(false)
        .process(e -> workWithException(e))
        .log("Enviando para Dead Letter Topic - DLT")
        .process(exchange -> finishProcess());
        //.recipientList(simple("kafka:${in.headers.kafka.TOPIC}.dlt?brokers=10.200.250.97:29094&requestRequiredAcks=1"));

        
        onException(Throwable.class)
        .handled(true)
        // .retryAttemptedLogLevel(LoggingLevel.INFO)
        // .maximumRedeliveries(3)
        // .redeliveryDelay(_DELAY)
        .logExhaustedMessageHistory(false)
        .process(exchange -> tryException(exchange))
        .log(LoggingLevel.ERROR,"${body}")
        .process(exchange -> finishProcess());

        onCompletion()
         .process(exchange -> this.finishProcess());
         
    }

    protected void tryException(Exchange exchange){
        Throwable exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        Optional<Throwable> rootCause = Stream.iterate(exception, Throwable::getCause)
            .filter(element -> element.getCause() == null)
            .findFirst();
        printLogError(exchange, exception,"9999"," - " + exception.getMessage() + " ROOT_CAUSE: "+rootCause.get() +" - ");
    }

    protected void workWithException(Exchange exchange){
        HttpOperationFailedException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
        String responseBody = exception.getResponseBody();  
        // int statusCode = exception.getStatusCode(); 
        printLogError(exchange, exception,"5555"," - " +exception.getMessage() + " ROOT_CAUSE: "+responseBody +" - ");
    }

    private void printLogError(Exchange exchange, Throwable e, String codeErroDefaul, String msg) {
        String code = exchange.getProperties().getOrDefault("code_error", codeErroDefaul).toString();
        String body = exchange.getProperties().getOrDefault("body", "").toString();
        log.error(code+msg+body, e);
        exchange.removeProperties("code_error");
        exchange.removeProperties("body");
    }
}
