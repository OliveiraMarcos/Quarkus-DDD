package br.com.api.ibyte.infra.crosscutting.integration;

import javax.enterprise.context.ApplicationScoped;

import br.com.api.ibyte.infra.crosscutting.integration.route.RouteBase;
import br.com.api.ibyte.infra.crosscutting.integration.route.routes.RouteTest;

@ApplicationScoped
public class MainRoute extends RouteBase {
    
    @Override
    public void configure() throws Exception {
        
        from("timer:rest?period=10s")
            .filter(method(RouteTest.class, "readyToProcess"))
            .to("direct:process-test").end();

    }
}
