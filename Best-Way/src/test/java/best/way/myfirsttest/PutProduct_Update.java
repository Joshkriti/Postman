package best.way.myfirsttest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutProduct_Update {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void updateProductDetails(){
        given()
                .when()
                .body("{\"name\":\"Duracell - AA Batteries (16-Pack)\",\"type\":\"HardGood\",\"price\":\"10.00\",\"upc\":\"041333825014\",\"shipping\":\"2\",\"description\":\"Compatible with select electronic devices; AA size; DURALOCK Power Preserve technology; 8-pack\",\"manufacturer\":\"Duracell\",\"model\":\"MN1500B8Z\"}")
                .put("http://localhost:3030/products/9999683")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void updateProduct(){
        // Adding product name
        String productUpdateData =  "{\n\t\"name\": \"Hand Watch\",\n\t\"type\": \"Hard Good\",\n\t\"upc\": \"12345676\",\n\t\"price\": 99.99,\n\t\"description\": \"This is a placeholder request for creating a new product.\",\n\t\"model\": \"NP12345\"\n}";

        RestAssured.baseURI = "http://localhost:3030/products/9999694";

        requestSpecification = given().contentType(ContentType.JSON);

        requestSpecification.body(productUpdateData);

        response = requestSpecification.put();

        String response1 = response.prettyPrint();

        validatableResponse = response.then();

        validatableResponse.statusCode(200);






    }


}
