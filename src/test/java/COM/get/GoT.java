package COM.get;

import COM.pojo.ContinentPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GoT {
    @Test
    public void basicCall(){

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters/";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                //.log().body()
                .extract()
                .response();

        List<Map<String , Object>> familyMembers = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        List<String> names = new ArrayList<>();
//        for (Map<String, Object> family : familyMembers){
//            if (family.get("family").equals("House Stark")){
//               names.add((String) family.get("fullName"));
//            }
//
//        }
        for (int i = 0; i < familyMembers.size(); i++) {
            if (familyMembers.get(i).get("family").equals("House Stark")){
                names.add((String) familyMembers.get(i).get("fullName"));
            }

        }
        System.out.println("====");
        System.out.println(names);

    }

    @Test
    public void printContinents(){

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents/";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                //.log().body()
                .extract()
                .response();


        List<Map<String, Object>> continentNames = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for (Map<String, Object> continent : continentNames){

            System.out.println(continent.get("name"));

        }

    }

    @Test
    public void getContinentPojo(){
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        ContinentPojo continentPojo = response.as(ContinentPojo.class);
        String expectedName = "Essos";
        int expectedID = 1 ;

        String actualName = continentPojo.getName();
        int actualID = continentPojo.getId();

        Assert.assertEquals(actualName, expectedName, "Failed to validate");
        Assert.assertEquals(actualID, expectedID, "Failed to validate");

        System.out.println(actualName);
        System.out.println(actualID);

    }

    @Test
    public void getAllContinents(){

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        ContinentPojo [] continentPojo = response.as(ContinentPojo[].class);
        Map<String , Integer> map = new HashMap<>();
        for (int i = 0; i < continentPojo.length; i++) {
            map.put(continentPojo[i].getName(), continentPojo[i].getId());
        }
        System.out.println(map);

        System.out.println();


    }
}
