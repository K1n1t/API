package PRACTIKA.post.airportPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class AirportPracticePojo {

    private AirportPracticeDataPojo data;
}
