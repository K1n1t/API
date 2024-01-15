package COM.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class AirportDataPojo {

    private String id;
    private String type;
    private AirportAttributePojo attributes;
}
