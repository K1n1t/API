package COM.pojo.telegramPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class TelegramMessagePojo {

    private NewChatParticipantTelegramPojo new_chat_participant;

}
