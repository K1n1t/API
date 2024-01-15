package COM.post;

import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Telegram {
    @Test
    public void sendMessageToTelegram() {

        String botToken = "6529483626:AAFlpn11QUCmB1ndfPmlw1NW82nxneUU20c";
        String chatId = "-4060811533";
        String message = "test Kanat KAnat Kanat tanaK";

       Response response = given()
                .baseUri("https://api.telegram.org")
                .basePath("/bot" + botToken + "/sendMessage")
                .queryParam("chat_id", chatId)
                .queryParam("text", message)
                .when()
                .post()
                .then()//.log().all()
                .statusCode(200).extract().response();

       //Map<String, Object> map = response.as(new TypeRef<Map<String, Object>>() {});

        JsonPath parsedResponse = response.jsonPath();
        System.out.println(parsedResponse.getString("result.from.first_name"));


    }
}
