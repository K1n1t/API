package COM.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Pokemon {
    @Test
    public void findPikachu(){
        RestAssured.baseURI = "https://pokeapi.co";
        RestAssured.basePath = "api/v2/pokemon";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        String pikachuURL = "";

        while (parsedResponse.get("next") != null){
            response = RestAssured.given().accept("application/json")
                    .when()
                    .get((String) parsedResponse.get("next"))
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            parsedResponse = response.as(new TypeRef<Map<String, Object>>(){});

            List<Map<String, Object>> results = (List<Map<String, Object>>) parsedResponse.get("results");
            for (int i = 0; i < results.size(); i++) {
                Map<String,Object> map = results.get(i);
                if (map.get("name").equals("pikachu")){

                    pikachuURL = (String) map.get("url");

                    response = RestAssured.given().accept("application/json")
                            .when()
                            .get((String) parsedResponse.get("next"))
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

                    parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
                    });

                    List<Map<String, Object>> abilities = (List<Map<String, Object>>) parsedResponse.get("abilities");
                    System.out.println(abilities);


                }
            }

        }

        response = RestAssured.given().accept("application/json")
                .when()
                .get(pikachuURL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> abilities = (List<Map<String, Object>>) parsedResponse.get("abilities");
        System.out.println("=====");
        System.out.println(abilities);

        for (int i = 0; i < abilities.size(); i++) {
            Map<String, Object> map = abilities.get(i);
            Map<String, Object> innerMap = (Map<String, Object>) map.get("ability");
            if (innerMap.get("name").equals("lightning-rod")){
                System.out.println("======");
                System.out.println(innerMap.get("name"));
                System.out.println(innerMap.get("url"));



            }
        }

    }

}
