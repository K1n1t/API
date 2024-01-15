package COM.pojo.telegramPojo;

import COM.pojo.telegramPojo.TelegramPojo;
import COM.pojo.telegramPojo.TelegramResultPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class GetTelegramUpdates {
    @Test
    public void getUpdates(){

       Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("https://api.telegram.org/bot6529483626:AAFlpn11QUCmB1ndfPmlw1NW82nxneUU20c/getUpdates")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath parsedResponse = response.jsonPath();
        System.out.println(parsedResponse.getString("result.message.new_chat_participant.first_name"));

        List<String> list = parsedResponse.getList("result.message.new_chat_participant.first_name");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase("Kanat_Bot")){
                System.out.println(list.get(i));
            }
        }



    }

    @Test
    public void getUpdatesWithPojo(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("https://api.telegram.org/bot6529483626:AAFlpn11QUCmB1ndfPmlw1NW82nxneUU20c/getUpdates")
                .then()
                .statusCode(200)
                .extract()
                .response();


        TelegramPojo parsedResponse = response.as(TelegramPojo.class);
        List<TelegramResultPojo> result = parsedResponse.getResult();
        System.out.println(result.get(0).getMessage().getNew_chat_participant().getUsername());
    }
}
