package COM.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class FaceStore {

    @Test
    public void validatePriseAtFakeStore(){
        Response response = RestAssured.given().accept("application/json")
                .when()
                .get("https://fakestoreapi.com/products/1")
                .then() // .log().body()
                .statusCode(200)  // this is validation // validating status code.
                .extract()
                .response();

        Map<String, Object > parsedResponce = response.as(new TypeRef<Map<String, Object>>() {
        });

        Double expectedPrice = 109.95;
        Double actualPrise = (Double) parsedResponce.get("price");
        Assert.assertEquals(expectedPrice,actualPrise, "Failed to ");


        Map<String, Object> rating = (Map<String, Object>) parsedResponce.get("rating");
        Assert.assertEquals(rating.get("rate"), 3.9);
        Assert.assertEquals(rating.get("count"), 120);


    }

    @Test
    public void validateTotalSumOfPrices(){

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get("https://fakestoreapi.com/products/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> parsedResponce = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        Double sumOfPrices = 0.0;
        for (int i = 0; i < parsedResponce.size(); i++) {
            Map<String , Object> map = parsedResponce.get(i);
            sumOfPrices += Double.parseDouble(map.get("price").toString());

        }
        System.out.println(sumOfPrices);

    }
}
