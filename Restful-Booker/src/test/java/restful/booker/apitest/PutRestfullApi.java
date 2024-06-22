package restful.booker.apitest;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
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
        String accessToken = "ec02847bd616fbf";
        given().auth()
                .oauth2(accessToken)
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
                .put("https://restful-booker.herokuapp.com/booking/199")
                .then().log().all()
                .statusCode(200);
    }



    @Test
    public void updateBooking(){
        RestAssured.authentication = basic("c1d73629071cd49");

        String updateData = "{\n" +
                "    \"firstname\" : \"Komal\",\n" +
                "    \"lastname\" : \"Kanji\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}" ;
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/4432";

        requestSpecification = given().contentType(ContentType.JSON);

        /*String cookieValue = response.getCookie("7c8b4263cd967d9");
        System.out.println(cookieValue);*/



        response = requestSpecification.put();
        requestSpecification.body(updateData);

        validatableResponse = response.then();

        validatableResponse.statusCode(200);

    }

    private AuthenticationScheme basic(String cookie) {
        return null;
    }


    @Test
    public void update(){
        String updateData = "{\n" +
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

        requestSpecification = given().contentType(ContentType.JSON);
        requestSpecification.body(updateData);

        requestSpecification = requestSpecification.header("Cookie"," \"token\": \"a6964e3b9964344\"", "text");

       response = requestSpecification.put();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);


    }
}
