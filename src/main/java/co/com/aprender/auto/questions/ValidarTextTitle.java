package co.com.aprender.auto.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ValidarTextTitle implements Question<String> {

    private Target target;

    public ValidarTextTitle(Target target) {
        this.target = target;
    }

    @Override
    public String answeredBy(Actor actor) {
        return target.resolveFor(actor).getText();
    }

    public static ValidarTextTitle validarTextTitle(Target target) {
        return new ValidarTextTitle(target);
    }
}
