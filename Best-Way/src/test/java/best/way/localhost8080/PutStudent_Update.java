package best.way.localhost8080;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutStudent_Update {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void updateStudentDetails(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 110,\n" +
                        "    \"firstName\": \"Komal\",\n" +
                        "    \"lastName\": \"Kanji\",\n" +
                        "    \"email\": \"Komal.kanji30@gmail.com\",\n" +
                        "    \"programme\": \"Financial Analysis\",\n" +
                        "    \"courses\": [\n" +
                        "        \"Accounting\",\n" +
                        "        \"business\"\n" +
                        "    ]\n" +
                        "}")
                .put("http://localhost:8080/student/110")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void verifyStatusCode(){
        String studentData = "{\n" +
                "    \"id\": 120,\n" +
                "    \"firstName\": \"Komal\",\n" +
                "    \"lastName\": \"Kanji\",\n" +
                "    \"email\": \"Komal.kanji199@gmail.com\",\n" +
                "    \"programme\": \"Financial Analysis\",\n" +
                "    \"courses\": [\n" +
                "        \"Accounting\",\n" +
                "        \"business\"\n" +
                "    ]\n" +
                "}";
        RestAssured.baseURI = "http://localhost:8080/student/110";

        requestSpecification = given().contentType(ContentType.JSON);

        requestSpecification.body(studentData);

        response = requestSpecification.put();

        String name = response.prettyPrint();

        validatableResponse = response.then();

        String actualName = validatableResponse.extract().path("msg");
        String expectedName = "Student Updated";
        Assert.assertEquals(actualName,expectedName);

        validatableResponse.statusCode(200);
    }
}
