package COM.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetPojo {
    private int id ;
    private PetCategoryPojo category;
    private String name;
    private List<String> photoUrls;
    private List<PetTagPojo> tags;
    private String status;
}
