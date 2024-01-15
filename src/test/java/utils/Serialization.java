package utils;

import COM.pojo.PetCategoryPojo;
import COM.pojo.PetPojo;
import COM.post.PostPet;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import java.io.File;
@Getter
@Setter

public class Serialization {
    @Test
    public void createJsonBody(){

        File file = new File("src/test/resources/jsonFiles/petfile.json");
        PetPojo petPojo = new PetPojo();
        petPojo.setId(1995);
        petPojo.setName("Boorsok");

        PetCategoryPojo petCategoryPojo = new PetCategoryPojo();
        petCategoryPojo.setName("Tuzzik");
        petCategoryPojo.setId(9999);

        petPojo.setCategory(petCategoryPojo);

        ObjectMapper objectMapper = new ObjectMapper();




    }
}
