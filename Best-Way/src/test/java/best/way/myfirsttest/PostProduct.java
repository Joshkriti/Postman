package best.way.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostProduct {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void createNewProduct() {

        given()
                .when()
                .body( "{\n\t\"name\": \"New Product\",\n\t\"type\": \"Hard Good\",\n\t\"upc\": \"12345676\",\n\t\"price\": 99.99,\n\t\"description\": \"This is a placeholder request for creating a new product.\",\n\t\"model\": \"NP12345\"\n}")
                //.body("{\"name\":\"Duracell - AA Batteries (16-Pack)\",\"type\":\"HardGood\",\"price\":\"10.00\",\"upc\":\"041333825014\",\"shipping\":\"2\",\"description\":\"Compatible with select electronic devices; AA size; DURALOCK Power Preserve technology; 8-pack\",\"manufacturer\":\"Duracell\",\"model\":\"MN1500B8Z\"}")
                .post("http://localhost:3030/products")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void verifyStatusCode() {

        String productData =  "{\n\t\"name\": \"New Product\",\n\t\"type\": \"Hard Good\",\n\t\"upc\": \"12345676\",\n\t\"price\": 99.99,\n\t\"description\": \"This is a placeholder request for creating a new product.\",\n\t\"model\": \"NP12345\"\n}";

        RestAssured.baseURI = "http://localhost:3030/products";

        requestSpecification = RestAssured.given().contentType(ContentType.JSON);

        requestSpecification.body(productData);

        response = requestSpecification.post();

        String resString = response.prettyPrint();
        System.out.println("Response Details : " + resString);

        validatableResponse = response.then();

        validatableResponse.statusCode(201);

        validatableResponse.statusLine("HTTP/1.1 201 Created");
    }



}
