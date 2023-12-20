package Pet;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:Reports/Pet.json","html:Reports/Pet.html"},
features = "Feature",
glue = "Pet.stepDef",
tags = "@Pet")
public class PetRunner {

}
