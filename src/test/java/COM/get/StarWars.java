package COM.get;

import COM.pojo.CharacterGoTPojo;
import COM.pojo.PlanetPojo;
import COM.pojo.SWCharacterPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StarWars {
    @Test
    public void getSWCharacters(){

        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people/4";

        Response response = given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        SWCharacterPojo parsedResponse = response.as(SWCharacterPojo.class);
        Assert.assertEquals(parsedResponse.getName(), "Darth Vader");
        Assert.assertEquals(parsedResponse.getBirth_year(), "41.9BBY");

    }

    @Test
    public void getPlanetPojoTest(){

        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/planets/1";


        Response response = given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        PlanetPojo planetPojo = response.as(PlanetPojo.class);
        String expectedName = "Alderaan";
        String actualName = planetPojo.getName();

        String expectedDiameter = "12500";
        String actualDiameter = planetPojo.getDiameter();

        String expectedGravity = "1 standart";
        String actualGravity = planetPojo.getGravity();

        Assert.assertEquals(actualName, expectedName, "Failed to validate");
        Assert.assertEquals(actualDiameter, expectedDiameter, "Failed to validate");
        Assert.assertEquals(actualGravity, expectedGravity, "Failed to validate");



    }

}
