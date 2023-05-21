import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Chapter1Tests {
    @Test
    public void checkPlaceNameInResponseBody (){
        given().when().get("https://api.zippopotam.us/US/00210")
                .then().assertThat()
                .body("places[0].'place name'",equalTo("Portsmouth"));
    }

}
