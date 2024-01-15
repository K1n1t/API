package COM.get;

import COM.pojo.PetPojo;
import COM.pojo.PetTagPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class PetStore {
    @Test
    public void  getDog(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/1289";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

            // parsing is happening here

        PetPojo parsedResponse = response.as(PetPojo.class);
        String name = parsedResponse.getCategory().getName();
        List<PetTagPojo> tags = parsedResponse.getTags();
        for (int i = 0; i < tags.size(); i++) {
            System.out.println(tags.get(i).getName());
            System.out.println(tags.get(i).getId());
        }

    }
}
