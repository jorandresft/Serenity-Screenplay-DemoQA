package co.com.aprender.auto.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/registroformularioestudiante.feature",
        glue = "co.com.aprender.auto.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@successful"
)
public class RunnerRegistroFormularioEstudiante {
}
