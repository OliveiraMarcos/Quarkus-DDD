package br.com.api.ibyte.integration;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MainControllerTest{

    @Test
    public void helloWordTestEndPoint() {
        given().when().get("/config/supersonic").then().statusCode(200);//.body(is("Mach"));
    }
}
