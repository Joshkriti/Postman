package best.way.localhost3030.product;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProduct {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void deleteProduct(){
        RestAssured.baseURI = "http://localhost:3030/products/9999708";

        requestSpecification = given();

        response = requestSpecification.delete();

        String resString = response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);
    }
}
