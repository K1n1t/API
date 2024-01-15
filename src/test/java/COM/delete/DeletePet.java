package COM.delete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePet {

    @Test
    public void deletePet(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .delete(String.valueOf(5005))
                .then()
                .statusCode(200)
                .extract()
                .response();

    }

    @Test
    public void testDeletedDog(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/4321";

        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
