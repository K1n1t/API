package COM.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Currencies {
    /* https://openexchangerates.org/api/currencies.json

    We will make a get call to this endpoint, validate our status code os 200
    then validate that USD is added
     */

    @Test
    public void validateUST(){

       Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://openexchangerates.org/api/currencies.json")
                .then() // .log().body()
                .statusCode(200)  // this is validation // validating status code.
                .extract()
                .response();


        // Converting json objects into Java is called Deseriolization

        // Parse = is also converting
        //             Storing in map        response.as(new TypeRef) is parsing our json into Java
        // There are multiple ways of deseriolization , and one of the called TypeRef
        Map<String , Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>(){});
        System.out.println(parsedResponse);

        String expectedUSD = "United States Dollar";
        String actual = (String)parsedResponse.get("USD");
        Assert.assertEquals(actual,expectedUSD, "Failed to validate response IYYYYYY");



    }

}
