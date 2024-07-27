package co.com.aprender.auto.stepdefinitions.hook;

import co.com.aprender.auto.hook.OpenWeb;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.aprender.auto.utils.Constants.WEB_URL;

public class Hook {

    @Before
    public void setup() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{string} abre el sitio web DemoQA")
    public void abreElSitioWebDemoQA(String actor) {
        OnStage.theActorCalled(actor).attemptsTo(
                OpenWeb.browserUrl(WEB_URL)
        );
    }
}
