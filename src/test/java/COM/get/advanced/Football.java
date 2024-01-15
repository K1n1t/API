package COM.get.advanced;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class Football {
    @Test
    public void getCompetitions(){

                                     // Deserializing With Groovy Script

    RestAssured.baseURI = "http://api.football-data.org";
    RestAssured.basePath = "v2/competitions" ;

    Response response = RestAssured.given().accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(200)                    // inside the body we are simply doing validation
            .body("competitions.find {it.id == 2023}.name", Matchers.is("Primera B Nacional"))
            .and()
           .body("competitions.findAll {it.area.name == 'Africa'}.name", Matchers.hasItems("Africa Cup", "WC Qualification CAF", "AFC Champions League"))
            .extract().response();

    String name = response.path("competitions.find {it.id == 2023}.name");
    System.out.println(name);

    List<String> championships = response.path("competitions.findAll {it.area.name == 'Africa'}.name");
    System.out.println(championships);

        // category.tags.name.is

}

@Test
public void getCapitalWithGroovy(){
    RestAssured.baseURI = "http://restcountries.com";
    RestAssured.basePath = "v3.1/all" ;

    Response response = RestAssured.given().accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(200)                    // inside the body we are simply doing validation
            .body("find {it.name.common == 'Germany'}.capital", Matchers.hasItem("Berlin"))
            .extract().response();

    List<String> capital = response.path("find {it.name.common == 'Germany'}.capital");
    System.out.println(capital);

    String expectedCapital = capital.get(0);
    System.out.println(expectedCapital);

}
 }