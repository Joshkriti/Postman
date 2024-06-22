package best.way.localhost3030.store;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetAllStore {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllStores(){
        given()
                .when()
                .get("http://localhost:3030/stores")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode(){
        RestAssured.baseURI = "http://localhost:3030/stores";

        requestSpecification = RestAssured.given();

        response = requestSpecification.get();

        String stores = response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);
    }

    @Test
    public void verifyAddress(){
        given()
                .when()
                .get("http://localhost:3030/stores")
                .then()
                .statusCode(200)
                .body("data[0].address",equalTo("13513 Ridgedale Dr"));
    }


}
