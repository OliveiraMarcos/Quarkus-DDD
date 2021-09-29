package br.com.api.ibyte;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.runtime.Quarkus;

@OpenAPIDefinition(
    // tags = {
    //         @Tag(name="widget", description="Widget operations."),
    //         @Tag(name="gasket", description="Operations related to gaskets")
    // },
    info = @Info(
        title="Model REST API",
        description = "Application model to constrution of APIs with infraestructure modern, using DDD Arquiteture.",
        version = "1.2.1",
        contact = @Contact(
            name = "Marcos Oliveira",
            url = "https://github.com/OliveiraMarcos",
            email = "marcos.ads.ti@gmail.com"),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class IbyteApplication extends Application{
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
