package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestBaseClass {

    public static RequestSpecification trademeSandbox_requestSpec;
    public static ResponseSpecification trademeSandbox_responseSpec;

    @BeforeClass
    public static void setup(){

        // RestAssured.proxy("localhost", 8888); // Use to run REST Assured through a proxy tools like Charles or Fiddler

        /* TARGET AUT endpoint: https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false */
        /* Request Specifications */
        trademeSandbox_requestSpec = new RequestSpecBuilder().
            setBaseUri("https://api.tmsandbox.co.nz").
            setBasePath("/v1/").
            build();

        RestAssured.requestSpecification = trademeSandbox_requestSpec;

        /* Response Specifications */
        trademeSandbox_responseSpec = new ResponseSpecBuilder().
            build();

        RestAssured.responseSpecification = trademeSandbox_responseSpec;
    }
}
