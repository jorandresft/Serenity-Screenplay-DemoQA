package co.com.aprender.auto.stepdefinitions;

import co.com.aprender.auto.models.UserModel;
import co.com.aprender.auto.questions.ValidarTextTitle;
import co.com.aprender.auto.tasks.IngresarUserInfo;
import co.com.aprender.auto.tasks.SeleccionarFormularioRegistro;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static co.com.aprender.auto.ui.FormSubmittingUI.TITLE_TEXT;
import static co.com.aprender.auto.utils.Constants.ACTOR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistroFormularioEstudianteStep {

    @DataTableType(replaceWithEmptyString = "[anonymous]")
    public UserModel userEntryTransformer(Map<String, String> row) {
        return new UserModel(
                row.get("firstName"),
                row.get("lastName"),
                row.get("email"),
                row.get("gender"),
                row.get("mobil"),
                row.get("state"),
                row.get("city")
        );
    }

    @And("selecciona el elemento Form y la sub funcion Practice Form")
    public void seleccionaElElementoFormYLaSubFuncionPracticeForm() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                SeleccionarFormularioRegistro.seleccionarFormularioRegistro()
        );
    }

    @When("se ingresa la informacion en el formulario")
    public void seIngresaLaInformacionEnElFormulario(List<UserModel> userInfo) {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                IngresarUserInfo.ingresarUserInfo(userInfo.get(0))
        );
    }

    @Then("se valida el mensaje {string}")
    public void seValidaElMensaje(String message) {
        OnStage.theActorCalled(ACTOR).should(
                seeThat(ValidarTextTitle.validarTextTitle(TITLE_TEXT),
                        equalTo(message))
        );
    }
}
