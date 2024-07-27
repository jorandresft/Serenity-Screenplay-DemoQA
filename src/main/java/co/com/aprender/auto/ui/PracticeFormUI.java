package co.com.aprender.auto.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class PracticeFormUI extends PageObject {

    public static final Target FIRST_NAME = Target.the("FIRST_NAME")
            .locatedBy("//input[@id='firstName']");
    public static final Target LAST_NAME = Target.the("LAST_NAME")
            .locatedBy("//input[@id='lastName']");
    public static final Target EMAIL = Target.the("EMAIL")
            .locatedBy("//input[@id='userEmail']");
    public static final Target GENDER = Target.the("GENERO")
            .locatedBy("//label[contains(text(),'{0}')]");
    public static final Target MOBIL = Target.the("MOBIL")
            .locatedBy("//input[@id='userNumber']");
    public static final Target STATE = Target.the("STATE")
            .locatedBy("//div[contains(text(),'Select State')]");
    public static final Target SELECT_STATE = Target.the("STATE")
            .locatedBy("//div[contains(text(),'{0}')]");
    public static final Target CITY = Target.the("CITY")
            .locatedBy("//div[contains(text(),'Select City')]");
    public static final Target SELECT_CITY = Target.the("CITY")
            .locatedBy("//div[contains(text(),'{0}')]");
    public static final Target BTN_SUBMIT = Target.the("BTN_SUBMIT")
            .locatedBy("//button[@id='submit']");
}
