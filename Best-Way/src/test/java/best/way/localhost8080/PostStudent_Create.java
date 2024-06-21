package best.way.localhost8080;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostStudent_Create {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void createNewStudent(){
        given().log().all()
                .when()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstName\": \"Komal\",\n" +
                        "    \"lastName\": \"Kan\",\n" +
                        "    \"email\": \"Komal.k11@gmail.com\",\n" +
                        "    \"programme\": \"Financial Analysis\",\n" +
                        "    \"courses\": [\n" +
                        "        \"Accounting\"\n" +
                        "    ]\n" +
                        "}")
                 .post("http://localhost:8080/student")
                .then().log().all()
                .statusCode(201);
    }
    @Test
    public void verifyStatusCode(){
       String studentData ="{\n" +
               "    \"firstName\": \"Komal\",\n" +
               "    \"lastName\": \"Kanji\",\n" +
               "    \"email\": \"Komal.kan1@gmail.com\",\n" +
               "    \"programme\": \"Financial Analysis\",\n" +
               "    \"courses\": [\n" +
               "        \"Accounting\"\n" +
               "    ]\n" +
               "}";
        RestAssured.baseURI = "http://localhost:8080/student";

        requestSpecification = RestAssured.given().contentType(ContentType.JSON);

        requestSpecification.body(studentData);

        response = requestSpecification.post();

        String studentDetails = response.prettyPrint();

        validatableResponse = response.then();

        String name = validatableResponse.extract().path("msg");
        String expectedName = "Student added";
        Assert.assertEquals(name,expectedName);

        validatableResponse.statusCode(201);
    }

}
