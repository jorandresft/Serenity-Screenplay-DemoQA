package co.com.aprender.auto.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class FormSubmittingUI extends PageObject {

    public static final Target TITLE_TEXT = Target.the("TITLE_TEXT")
            .locatedBy("//div[@id='example-modal-sizes-title-lg']");
}
