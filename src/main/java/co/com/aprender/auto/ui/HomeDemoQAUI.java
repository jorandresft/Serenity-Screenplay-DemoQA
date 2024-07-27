package co.com.aprender.auto.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class HomeDemoQAUI extends PageObject {

    public static final Target FORMS = Target.the("Formulario")
            .locatedBy("//h5[contains(text(),'Forms')]");
    public static final Target PRACTICE_FORM = Target.the("Practica formulario")
            .locatedBy("//span[contains(text(),'Practice Form')]");
}
