package qaops.automation.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import qaops.automation.api.support.api.UserApi;

public class Config {

    private final UserApi userApi;

    public Config(){
        userApi = new UserApi();
    }

    @Before
    public void setup() {
       // RestAssured.registerParser("text/html", Parser.JSON);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "";
        RestAssured.requestSpecification = new RequestSpecBuilder().
                addHeader("Authorization",getToken()).
                //setContentType(ContentType.JSON).
                       setContentType("application/json").
                build();
//        RestAssured.responseSpecification = new ResponseSpecBuilder().
//                expectContentType(ContentType.JSON).
//                build();

    }

    private String getToken() {
        return "grant access";
    }

    @After("@deleteAllUsers")
    public void deleteAllUsers(){
        System.out.println("Deletando Usuarios");
        userApi.deleteAllUsers();
    }
}
