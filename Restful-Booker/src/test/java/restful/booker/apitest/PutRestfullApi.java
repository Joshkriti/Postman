package restful.booker.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutRestfullApi {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;

    @Test
    public void putNewBooking(){
        given()
                .when()
                .body("{\r\n    \"firstname\" : \"Jiya\",\r\n    \"lastname\" : \"Shah\",\r\n    \"totalprice\" : 111,\r\n    \"depositpaid\" : true,\r\n    \"bookingdates\" : {\r\n        \"checkin\" : \"2022-12-01\",\r\n        \"checkout\" : \"2023-10-01\"\r\n    },\r\n    \"additionalneeds\" : \"Full meal\"\r\n}")
                .put("https://restful-booker.herokuapp.com/booking/199")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void updateBooking(){

        String updateData = "{\r\n    \"firstname\" : \"Jiya\",\r\n    \"lastname\" : \"Shah\",\r\n    \"totalprice\" : 111,\r\n    \"depositpaid\" : true,\r\n    \"bookingdates\" : {\r\n        \"checkin\" : \"2022-12-01\",\r\n        \"checkout\" : \"2023-10-01\"\r\n    },\r\n    \"additionalneeds\" : \"Full meal\"\r\n}";

        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/12";

        requestSpecification = given().contentType(ContentType.JSON);

        //response = requestSpecification.put("key : Cookie , value : token=73670c4cfab7e01, type : text");
        response = requestSpecification.put();
        requestSpecification.body(updateData);

        requestSpecification = given().contentType(ContentType.JSON);
        validatableResponse = response.then();

        // Get status code
        validatableResponse.statusCode(200);

    }

    @Test
    public void update(){
        String updateData = "{\r\n    \"firstname\" : \"Jiya\",\r\n    \"lastname\" : \"Shah\",\r\n    \"totalprice\" : 111,\r\n    \"depositpaid\" : true,\r\n    \"bookingdates\" : {\r\n        \"checkin\" : \"2022-12-01\",\r\n        \"checkout\" : \"2023-10-01\"\r\n    },\r\n    \"additionalneeds\" : \"Full meal\"\r\n}";

        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/12";

        requestSpecification = given().contentType(ContentType.JSON);
        requestSpecification.body(updateData);

        requestSpecification = requestSpecification.header("Cookie"," \"token\": \"a6964e3b9964344\"", "text");

       response = requestSpecification.put();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);


    }
}
