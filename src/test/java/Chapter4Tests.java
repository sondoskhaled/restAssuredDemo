import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Chapter4Tests {

    private static RequestSpecification requestSpec;

    @BeforeClass public static void createRequestSpec (){
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.zippopotam.us")
                .setContentType(ContentType.JSON)
                .build();
    }

    private static ResponseSpecification  responseSpec;

    @BeforeClass
    public static void createResponseSpec (){
        responseSpec =  new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void checkStatusCodeOfTheResponse (){
        given()
                .spec(requestSpec)
                .when()
                .get("us/90210")
                .then()
                .spec(responseSpec)
                .assertThat()
                .body("places[0].'place name'",equalTo("Beverly Hills"));
    };

    @Test
    public void extractPlaceName (){
        String placeName = given()
                .spec(requestSpec)
                .when()
                .get("us/90210")
                .then()
                .spec(responseSpec)
                .extract()
                .path("places[0].'place name'");

        Assert.assertEquals(placeName,"Beverly Hills");
    }
}
