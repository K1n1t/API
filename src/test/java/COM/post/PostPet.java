package COM.post;

import COM.pojo.PetCategoryPojo;
import COM.pojo.PetPojo;
import COM.pojo.PetTagPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;
import utils.Payloads;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
@Setter
@Getter

public class PostPet {
    @Test
    public void createPet(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .body(Payloads.getPetPayload(47567, "Sharik"))
                .post()
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

        // DESERIALIZATION USING JsonPath
        JsonPath parsedResponse = response.jsonPath();

        System.out.println("====");
        System.out.println(parsedResponse.getString("name"));
        System.out.println(parsedResponse.getList("tags.name"));
    }

    @Test
     public void createPetWithSetters(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        PetPojo petPojo = new PetPojo();
        petPojo.setId(1995);

        PetCategoryPojo petCategoryPojo = new PetCategoryPojo();
        petCategoryPojo.setId(3423);
        petCategoryPojo.setName("Bobik");

        petPojo.setName("Tuzik");

        PetTagPojo petTagPojo = new PetTagPojo();
        petTagPojo.setId(585);
        petTagPojo.setName("Borsok");

        petPojo.setStatus("Always playing");
        petPojo.setCategory(petCategoryPojo);
        List<PetTagPojo> tags = new ArrayList<>();
        tags.add(petTagPojo);
        petPojo.setTags(tags);

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .body(petPojo)
                .post()
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();




    }

    @Test
    public void createAnotherPetWithJson(){

        File file = new File("src/test/resources/jsonFiles/petfile.json");

        RestAssured.baseURI = "";
        RestAssured.basePath = "";

        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(file)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();
    }


}
