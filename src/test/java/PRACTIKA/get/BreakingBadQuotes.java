package PRACTIKA.get;

import PRACTIKA.pojo.BbQuotesPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

@Getter
@Setter
public class BreakingBadQuotes {
    @Test
    private void getQuotes(){
        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        RestAssured.basePath = "v1/quotes/";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        BbQuotesPojo [] pojos = response.as(BbQuotesPojo[].class);
        for (int i = 0; i < pojos.length; i++) {
            System.out.println(pojos[i].getQuote());
            System.out.println(pojos[i].getAuthor());
        }


    }

}
