package COM.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CatFacts {

    @Test
    public void validateTotalCatFacts(){

        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://catfact.ninja/facts")
                .then() // .log().body()
                .statusCode(200)  // this is validation // validating status code.
                .extract()
                .response();

        Map<String , Object> parsedResponce = response.as(new TypeRef<Map<String, Object>>(){});

        int expectedTotal = 332;
        int actualTotal = (int) parsedResponce.get("total");
        Assert.assertEquals(actualTotal, expectedTotal, "Failed to validate total");


    }
    @Test
    public void validateDataSize(){

        Response response = RestAssured.given().header("Accept", "application/json")
                .queryParam("limit" , 100)
                .when()
                .get("https://catfact.ninja/facts")
                .then() // .log().body()
                .statusCode(200)  // this is validation // validating status code.
                .extract()
                .response();

        Map<String , Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>(){});

        //Object obj = parsedResponce.get("data");
        List<Map<String, Object>> data = (List<Map<String, Object>>) parsedResponse.get("data");
        Integer expectedListSize = 100;
        Integer actualListSize = data.size();

        Assert.assertEquals(actualListSize,expectedListSize, "Failed to validate list size");

    }
}
