package restful.booker.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRestfullApi {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void createNewBooking() {
        given()
                .when()
                .body("{\r\n    \"firstname\" : \"Komal\",\r\n    \"lastname\" : \"Kanji\",\r\n    \"totalprice\" : 111,\r\n    \"depositpaid\" : true,\r\n    \"bookingdates\" : {\r\n        \"checkin\" : \"2022-12-01\",\r\n        \"checkout\" : \"2023-10-01\"\r\n    },\r\n    \"additionalneeds\" : \"Full meal\"\r\n}")
                //.body("{\"firstname\":\"John\",\"lastname\":\"Smith\",\"totalprice\":\"111\",\"depositpaid\":\"true\",\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\",\"additionalneeds\":\"Breakfast\"}")
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode(){
        String bookingData = "{\r\n    \"firstname\" : \"Komal\",\r\n    \"lastname\" : \"Kanji\",\r\n    \"totalprice\" : 111,\r\n    \"depositpaid\" : true,\r\n    \"bookingdates\" : {\r\n        \"checkin\" : \"2022-12-01\",\r\n        \"checkout\" : \"2023-10-01\"\r\n    },\r\n    \"additionalneeds\" : \"Full meal\"\r\n}";

        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";

        requestSpecification = RestAssured.given().contentType(ContentType.JSON);

        requestSpecification.body(bookingData);

        response = requestSpecification.post();

        String resString = response.prettyPrint();
        System.out.println("Response details: " + resString);

        validatableResponse = response.then();

        validatableResponse.statusCode(200);

        validatableResponse.statusLine("HTTP/1.1 200 OK");

    }

    @Test
    public void verifyUser() {

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/1690");

        String name = response.then().extract().path("firstname");
        String eName = "Komal";

        Assert.assertEquals(name, eName);
    }

    @Test
    public void getEmployeeById() {

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/1690")
                .prettyPrint();
    }


}
