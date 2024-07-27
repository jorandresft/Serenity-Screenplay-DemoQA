package co.com.aprender.auto.tasks;

import co.com.aprender.auto.models.UserModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actions.selectactions.SelectByVisibleTextFromBy;
import org.openqa.selenium.Keys;

import static co.com.aprender.auto.ui.PracticeFormUI.*;

public class IngresarUserInfo implements Task {

    private UserModel userModel;

    public IngresarUserInfo(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(FIRST_NAME),
                Enter.theValue(userModel.getFirstName()).into(FIRST_NAME),
                Enter.theValue(userModel.getLastName()).into(LAST_NAME),
                Enter.theValue(userModel.getEmail()).into(EMAIL),
                Click.on(GENDER.of(userModel.getGender())),
                Enter.theValue(userModel.getMobil()).into(MOBIL),
                Scroll.to(STATE),
                Click.on(STATE),
                Click.on(SELECT_STATE.of(userModel.getState())),
                Click.on(CITY),
                Click.on(SELECT_CITY.of(userModel.getState())),
                Scroll.to(BTN_SUBMIT),
                Click.on(BTN_SUBMIT)
        );
    }

    public static IngresarUserInfo ingresarUserInfo(UserModel userModel){
        return Tasks.instrumented(IngresarUserInfo.class, userModel);
    }
}
