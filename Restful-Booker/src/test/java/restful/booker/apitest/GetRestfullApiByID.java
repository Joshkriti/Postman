package restful.booker.apitest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRestfullApiByID {

    RequestSpecification requestSpecification;
    Response response;

    @Test
    public void GetAllBookingByID(){
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/96")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyStatusCode(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking/96";

        requestSpecification = given();

        response = requestSpecification.get();

        String resString = response.prettyPrint();
        System.out.println("Response Detail: " + resString);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200,statusCode);
    }

    @Test
    public void verifyUserByID(){

       Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/17");

        String name = response.then().extract().path("firstname");
        String expectedName = "Josh";
        Assert.assertEquals(name,expectedName);


    }



}
