import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class Chapter3Tests {

    @DataProvider
    public static Object[][] zipCodeAndPlaces (){
        return new Object[][] {
                {"us" , "00210" , "Portsmouth"},
                {"us" , "90210" , "Beverly Hills"},
                {"ca" , "B2R" , "Waverley"}
        };
    }

    @Test (dataProvider = "zipCodeAndPlaces")
    public void checkPlaceNameInResponseBody (String countryCode , String zipCode , String expectedPlace){
        given()
                .pathParam("countryCode" ,countryCode)
                .pathParam("zipCode", zipCode)
                .when().get("https://api.zippopotam.us/{countryCode}/{zipCode}")
                .then().assertThat()
                .body("places[0].'place name'",equalTo(expectedPlace));
    }
}
