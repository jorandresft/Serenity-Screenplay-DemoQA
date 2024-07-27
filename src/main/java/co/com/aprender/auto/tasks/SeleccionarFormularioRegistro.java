package co.com.aprender.auto.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;

import static co.com.aprender.auto.ui.HomeDemoQAUI.FORMS;
import static co.com.aprender.auto.ui.HomeDemoQAUI.PRACTICE_FORM;

public class SeleccionarFormularioRegistro implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(FORMS),
                Click.on(FORMS),
                Scroll.to(PRACTICE_FORM),
                Click.on(PRACTICE_FORM)
        );
    }

    public static SeleccionarFormularioRegistro seleccionarFormularioRegistro() {
        return Tasks.instrumented(SeleccionarFormularioRegistro.class);
    }
}
