package COM.post;

import COM.pojo.AirportPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Airports {
    @Test
    public void getMyAirport(){

        RestAssured.baseURI = "https://airportgap.com";
        RestAssured.basePath = "api/airports/distance";

        File file = new File("src/test/resources/jsonFiles/airport.json");
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(file)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        AirportPojo parsedResponse = response.as(AirportPojo.class);
        Double actualMiles = parsedResponse.getData().getAttributes().getMiles();
        Double expectedMiles = 1198.4365657701198;

        String actualId = parsedResponse.getData().getId();
        String expectedId = "ORD-MIA";

        Assert.assertEquals(actualMiles, expectedMiles );
        Assert.assertEquals(actualId, expectedId);

        }
    }

