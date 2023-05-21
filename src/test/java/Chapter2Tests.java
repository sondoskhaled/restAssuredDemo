import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Chapter2Tests {
    @Test
    public void checkStatusCode (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .statusCode(201);
    }

    @Test
    public void checkContentType (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .contentType(ContentType.JSON);
        //or .contentType(application/json);
    }

    @Test
    public void loggingRequestANDResponse (){
        given()
                .log().all()
                .when().get("https://api.zippopotam.us/US/00210")
                .then()
                .log().body();
    }

    @Test
    public void checkStateInResponseBody (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .body("places[0].'state'",equalTo("New Hampshire"));
    }

    @Test
    public void checkHasStateInResponseBody (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .body("places.'state'",hasItem("New Hampshire"));
    }

    @Test
    public void checkNotHasStateInResponseBody (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .body("places.'state'",not(hasItem("New Hampshire")));
    }

    @Test
    public void checkPlacesSizeInResponseBody (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .body("places.'state'",hasSize(1));
    }

}
