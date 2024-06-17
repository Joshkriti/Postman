package best.way.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetProductByID {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getProductByID(){
        given()
                .when()
                .get("http://localhost:3030/products/9999682")
                .then()
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void verifyStatusCode(){
        RestAssured.baseURI = "http://localhost:3030/products/127687";

        requestSpecification = RestAssured.given();

        response = requestSpecification.get();

        response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);

        validatableResponse.statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void verifyProductModelNumber(){
        given()
                .when()
                .get("http://localhost:3030/products")
                .then()
                .statusCode(200)
                .body("data[1].model", equalTo("MN1500B4Z"));
    }

    @Test
    public void verifyProductName() {
        Response response = given()
                .when()
                .get("http://localhost:3030/products");

        String actualName = response.then().extract().path("data[1].name");
        String expectedName = "Duracell - AA 1.5V CopperTop Batteries (4-Pack)";
        Assert.assertEquals(actualName, expectedName);
    }

    @Test
    public void verifyProductByID(){
        ValidatableResponse validatableResponse = given()
                .when()
                .get("http://localhost:3030/products")
                .then();

        String actualOutcome = validatableResponse.extract().path("data[1].categories[2].id");
        String expectedOutcome = "pcmcat303600050001";
        Assert.assertEquals(actualOutcome,expectedOutcome);
    }

    @Test
    public void verifyProductPrice(){
        given()
                .when()
                .get("http://localhost:3030/products")
                .then()
                .statusCode(200)
                .body("data[6].type",equalTo("HardGood"));
    }

    @Test
    public void verifyProductDescription(){
        Response response = given()
                .when()
                .get("http://localhost:3030/products");

        String name = response.then().extract().path("data[8].description");
        String expectedName = "Alkaline batteries; 1.5V";
        Assert.assertEquals(name,expectedName);
    }
}
