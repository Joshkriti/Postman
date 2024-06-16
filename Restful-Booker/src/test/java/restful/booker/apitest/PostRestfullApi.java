package restful.booker.apitest;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRestfullApi {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void createNewBooking(){
        given()
                .when()
                .body("{\"firstname\":\"John\",\"lastname\":\"Smith\",\"totalprice\":\"111\",\"depositpaid\":\"true\",\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\",\"additionalneeds\":\"Breakfast\"}")
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200);
    }


}
