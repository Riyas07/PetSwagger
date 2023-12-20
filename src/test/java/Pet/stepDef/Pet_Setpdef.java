package Pet.stepDef;

import Pet.PetPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
public class Pet_Setpdef {
    PetPojo pojo=new PetPojo();
    LinkedHashMap<String,String>category=new LinkedHashMap<>();
    @Given("build the POST request payload with given details")
    public void build_the_post_request_payload_with_given_details(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String,String>>datas= dataTable.asMaps(String.class,String.class);
    for (Map<String,String>map:datas)
    {
        for (String key:map.keySet())
        {
            switch (key)
            {
                case "id":if (!map.get(key).isEmpty())pojo.setId(map.get(key));else System.exit(-1);
                break;
                case "name":if (!map.get(key).isEmpty())pojo.setName(map.get(key));else System.exit(-1);
                break;
                case "status":if (!map.get(key).isEmpty())pojo.setStatus(map.get(key));else System.exit(-1);
            }
        }
    }
    }
    @When("add the category {string}")
    public void add_the_category(String string, io.cucumber.datatable.DataTable dataTable) {
   List<Map<String,String>> datas= dataTable.asMaps(String.class,String.class);
     for (Map<String,String> map:datas)
        {
            for (String key:map.keySet())
            {
                switch (key)
                {
                    case "id":if (!map.get(key).isEmpty())category.put(key,map.get(key));else System.exit(-1);
                    break;
                    case "name":if (!map.get(key).isEmpty())category.put(key,map.get(key));else System.exit(-1);
                    break;
                }
            }
        }
        Map<String,Object> ob=new LinkedHashMap<>();
     ob.put(string,category);
     pojo.setCategory(ob);
    }
    @Then("add photo urls")
    public void add_photo_urls(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String,String>>data= dataTable.asMaps(String.class,String.class);
    pojo.setPhotoUrls(Collections.singletonList(data.get(0).get("urls")));
    }
    @Then("add tags")
    public void add_tags(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>>data=dataTable.asMaps(String.class,String.class);
      pojo.setTags(data);
    }

    @Then("generate the payload")
    public void generateThePayload() throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        String payload=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        //System.out.println(payload);
    }
    @Then("trigger the POST request")
    public void triggerThePOSTRequest() {
      given()
              .baseUri("https://petstore.swagger.io/")
              .basePath("v2/")
              .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
              .contentType(ContentType.JSON)
              .body(pojo)
              .when()
              .post("pet")
              .then()
              .assertThat().statusCode(200).body("name", Matchers.containsString("doggie"));
    }
}
