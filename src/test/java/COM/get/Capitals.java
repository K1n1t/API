package COM.get;

import COM.pojo.BbQuotesPojo;
import COM.pojo.CapitalsPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class Capitals {
    @Test
    public void findCapital(){

        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        List<String> cap;
        String capital = "";
        for (Map<String, Object> data : parsedResponse){

            Map<String, Object> name = (Map<String, Object>) data.get("name");

            if (name.get("common").equals("Germany")){

                cap = (List<String>) data.get("capital");
                capital = cap.get(0);
            }
        }
        System.out.println(capital);
    }

    @Test
    public void getCapitalPojo(){


        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

        CapitalsPojo[] capitals = response.as(CapitalsPojo[].class);

        for (int i = 0; i < capitals.length; i++) {
            if (capitals[i].getName().getCommon().equalsIgnoreCase("Kyrgyzstan")) {
                System.out.println(capitals[i].getCapital());
            }

        }
    }
}
