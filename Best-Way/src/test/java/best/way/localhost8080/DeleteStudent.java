package best.way.localhost8080;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteStudent {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void deleteStudent(){
        RestAssured.baseURI = "http://localhost:8080/student/110";

        requestSpecification = given();

        response = requestSpecification.delete();

        String student = response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(204);

       validatableResponse.statusLine("HTTP/1.1 204 ");
   }
}
