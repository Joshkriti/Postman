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
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"firstname\" : \"Komal\",\n" +
                        "    \"lastname\" : \"Kanji\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode(){
        String bookingData = "{\n" +
                "    \"firstname\" : \"Komal\",\n" +
                "    \"lastname\" : \"Kanji\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";

        requestSpecification = given().contentType(ContentType.JSON);

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
