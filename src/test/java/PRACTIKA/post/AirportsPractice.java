package PRACTIKA.post;

import PRACTIKA.post.airportPojo.AirportPracticePojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class AirportsPractice {
    @Test
    public void getHome(){

        RestAssured.baseURI = "https://airportgap.com";
        RestAssured.basePath = "api/airports/distance";
        File file = new File("src/test/resources/jsonFiles/airport.json");

        Response response = given().accept(ContentType.JSON).accept(ContentType.JSON)
                .body(file)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        AirportPracticePojo parsedResponse = response.as(AirportPracticePojo.class);

        Double expectedMiles = 6455.486418376178;
        Double actualMiles = parsedResponse.getData().getAttributes().getMiles();

        String expectedId = parsedResponse.getData().getId();
        String actualId = "ORD-FRU";

        Assert.assertEquals(actualMiles,expectedMiles);
        Assert.assertEquals(actualId,expectedId);

    }
}
