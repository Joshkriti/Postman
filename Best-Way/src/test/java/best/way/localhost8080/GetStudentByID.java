package best.way.localhost8080;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetStudentByID {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getStudentByID(){
        given()
                .when()
                .get("http://localhost:8080/student/110")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void verifyStatusCode(){
        RestAssured.baseURI = "http://localhost:8080/student/2";

        requestSpecification = RestAssured.given();

        response = requestSpecification.get();

        String name = response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);

        validatableResponse.statusLine("HTTP/1.1 200 ");
    }
    @Test
    public void verifyStudentID(){
        given()
                .when()
                .get("http://localhost:8080/student/2")
                .then()
                .statusCode(200)
                .body("id", equalTo(2));
    }
    @Test
    public void verifyStudentName(){
        Response response = given()
                .when()
                .get("http://localhost:8080/student/7");

        String name = response.then().extract().path("firstName");
        String expectedName = "Roth";
        Assert.assertEquals(name,expectedName);
    }
    @Test
    public void verifyStudentLastname(){
        ValidatableResponse validatableResponse = given()
                .when()
                .get("http://localhost:8080/student/7")
                .then();

        String name = validatableResponse.extract().path("lastName");
        String expectedName = "Grant";
        Assert.assertEquals(name,expectedName);
    }
}
