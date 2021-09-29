package br.com.api.ibyte.infra.crosscutting.integration.route.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.LoggingLevel;

import br.com.api.ibyte.infra.crosscutting.integration.route.RouteBase;

@ApplicationScoped
public class RouteTest extends RouteBase {
    @Override
    public void configure() throws Exception {
        
        super.configure();

        from("direct:process-test")
            // .onCompletion().process(exchange -> this.finishProcess()).end()
            .log("Funcionando...");
    }
}
