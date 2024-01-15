package COM.put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Payloads;

import static io.restassured.RestAssured.given;

public class PetStorePut {

    @Test
    public void updatePet(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        String updateDogName= "Borman";
        int dogId = 4321;

        // updated existing Pet
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .body(Payloads.getPetPayload(dogId, updateDogName))
                .post()
                .then().log().all()
                .body("id", Matchers.is(4321))
                .and()
                .body("name", Matchers.is(updateDogName))
                .statusCode(200)
                .extract()
                .response();



        // retrieving updated Pet
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .get()
                .then().log().all()
                .body("id", Matchers.is(4321))
                .and()
                .body("name", Matchers.is(updateDogName))
                .statusCode(200)
                .extract()
                .response();




//        given().accept(ContentType.JSON).contentType(ContentType.JSON)
//                .when()
//                .delete(String.valueOf(dogId))
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();

    }



}
