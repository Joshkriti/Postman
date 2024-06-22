package best.way.localhost3030.store;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetStoreByID {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getStoresByID(){
        given()
                .when()
                .get("http://localhost:3030/stores/10")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode(){
        RestAssured.baseURI = "http://localhost:3030/stores/10";

        requestSpecification = RestAssured.given();

        response = requestSpecification.get();

        String storeID = response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);

        validatableResponse.statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void verifyAddress(){
        given()
                .when()
                .get("http://localhost:3030/stores")
                .then()
                .statusCode(200)
                .body("data[4].address", equalTo("1795 County Rd D E"))
                .body("data[4].city",equalTo("Maplewood"));
    }

}
