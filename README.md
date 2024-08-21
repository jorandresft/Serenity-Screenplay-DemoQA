
# Serenity-Screenplay-DemoQA

Proyecto para validar el login en la página DemoQA con Serenity BDD, Cucumber y Screenplay.

## Paso a Paso

Pasos de como realice el proyecto

```bash
    Abrir IntelliJ y crear un proyecto Java y Gradle
```

Modificar el archivo build.gradle con la siguiente estructura

```bash
buildscript {
    ext.serenityCoreVersion = '3.3.0'
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("net.serenity-bdd:serenity-gradle-plugin:$serenityCoreVersion")
        classpath "net.serenity-bdd:serenity-single-page-report:$serenityCoreVersion"
    }
}
plugins {
    id "net.serenity-bdd.serenity-gradle-plugin" version "${serenityCoreVersion}"
    id 'java'
    id 'eclipse'
    id 'idea'
}

defaultTasks 'clean','test','aggregate'
apply plugin: 'net.serenity-bdd.serenity-gradle-plugin'

group 'co.com.screenplay.project'
version '1.0-SNAPSHOT'

compileJava.options.encoding = "UTF-8"
compileTestJava.options.encoding = "UTF-8"

sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
    mavenCentral()
}

ext {
    slf4jVersion = '1.7.7'
    serenityCoreVersion = '3.3.4'
    serenityCucumberVersion = '3.3.4'
    junitVersion = '5.8.2'
    assertJVersion = '3.22.0'
    logbackVersion = '1.2.10'
    lombokVersion = '1.18.22'
    log4jVersion = '2.17.1'
}

dependencies {
    testImplementation ("net.serenity-bdd:serenity-core:${serenityCoreVersion}"){
        exclude group: 'org.apache.groovy', module: 'groovy'
    }
    implementation "net.serenity-bdd:serenity-ensure:${serenityCoreVersion}"
    implementation "net.serenity-bdd:serenity-junit5:${serenityCoreVersion}"
    implementation "net.serenity-bdd:serenity-cucumber:${serenityCucumberVersion}"
    implementation "net.serenity-bdd:serenity-screenplay:${serenityCoreVersion}"
    implementation "net.serenity-bdd:serenity-screenplay-webdriver:${serenityCoreVersion}"
    implementation "net.serenity-bdd:serenity-screenplay-rest:${serenityCoreVersion}"

    implementation "org.junit.jupiter:junit-jupiter-api:${junitVersion}"
    implementation "org.junit.jupiter:junit-jupiter-engine:${junitVersion}"
    implementation "org.assertj:assertj-core:${assertJVersion}"
    implementation "ch.qos.logback:logback-classic:${logbackVersion}"

    compileOnly "org.projectlombok:lombok:${lombokVersion}"
    annotationProcessor "org.projectlombok:lombok:${lombokVersion}"
    testCompileOnly "org.projectlombok:lombok:${lombokVersion}"
    testAnnotationProcessor "org.projectlombok:lombok:${lombokVersion}"
    implementation group: 'com.github.javafaker', name: 'javafaker', version: '1.0.2'

}
test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
    systemProperties System.getProperties()
}

serenity {
    testRoot = "co.com.aprender.auto.runnesrs"
    requirementsBaseDir = "src/test/resources/features"
    reports = ["single-page-html"]
}

gradle.startParameter.continueOnFailure = true
test.finalizedBy(aggregate)
```

Crear las carpetas necesarias para el proyecto

```bash
    src/test/java/resources/features
    src/test/java/.../runners, stepdefinitions/hook
    src/main/java/.../ui, tasks, questions, hook... etc
```

Dentro de la carpeta resources crear el archivo serenity.conf con la siguiente estructura

```bash
webdriver {
  driver = edge
}
//headless.mode = true

webdriver {
  capabilities {
    browserName = "MicrosoftEdge"
    "ms:edgeOptions" {
      args = ["start-maximized","test-type", "ignore-certificate-errors",
        "incognito", "disable-infobars", "disable-gpu", "disable-default-apps", "disable-popup-blocking", "remote-allow-origins=*"]
    }
  }
}

#
# This section defines environment-specific configuration for different environments.
# You can define nowermal Serenity properties, such as webdriver.base.url, or custom ones
# You can find more details about this feature at https://johnfergusonsmart.com/environment-specific-configuration-in-serenity-bdd/
#

environments {
  default {
    webdriver.base.url = "https://demoqa.com/"
  }
}

serenity {
  encoding = "UTF-8"
  compress.filenames = true
  take.screenshots = FOR_EACH_ACTION
}
```

Dentro de la carpeta resources crear el archivo logback-test.xml con la siguiente estructura

```bash
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
            </pattern>
        </encoder>
    </appender>

    <logger name="root" level="WARN"/>
    <logger name="net.serenitybdd" level="INFO"/>
    <logger name="net.thucydides" level="INFO"/>

    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>

</configuration>
```
Dentro de la carpeta runners se crea una clase java con la siguiente estructura

```bash
Ej:
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
```

Dentro de la carpeta features se crea el archivo .feature con la siguiente estructura

```bash
Ej:
Característica: realizar el registro de un estudiante en el sitio web DemoQA

  Yo como estudiante
  Quiero realizar el registro en el sitio web DemoQA
  Para comprabar que el registro sea exitoso

  Antecedentes:
    Dado "estudiante" abre el sitio web DemoQA

  @successful
  Esquema del escenario: : validar el registro de un estudiante en el sitio web DemoQA
    Y selecciona el elemento Form y la sub funcion Practice Form
    Cuando se ingresa la informacion en el formulario
      | firstName   | lastName   | email   | gender   | mobil   | state   | city   |
      | <firstName> | <lastName> | <email> | <gender> | <mobil> | <state> | <city> |
    Entonces se valida el mensaje "Thanks for submitting the form"

    Ejemplos:
      | firstName | lastName | email           | gender | mobil      | state   | city    |
      | jorge     | franco   | jorge@gmail.com | Male   | 5555555555 | Haryana | Panipat |
```

Dentro de la carpeta stepdefinitions/hook crear la clase java Hook con la siguiente estructura

```bash
package co.com.saucelabs.web.stepdefinitions.hook;

import co.com.saucelabs.web.hook.OpenWeb;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.saucelabs.web.utils.Constants.WEB_URL;

public class Hook {

    @Before
    public void setup() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("The {string} open the web site")
    public void openWebSite(String actor) {
        OnStage.theActorCalled(actor).attemptsTo(
                OpenWeb.browserUrl(WEB_URL)
        );
    }
}

```

Dentro de la carpeta main/java/.../hook crear la clase java OpenWeb con la siguiente estructura

```bash
package co.com.saucelabs.web.hook;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenWeb implements Task {

    private EnvironmentVariables environmentVariables;
    private String webUrl;

    public OpenWeb(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String pathWebUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty(webUrl);

        actor.attemptsTo(Open.url(pathWebUrl));
    }

    public static Performable browserUrl(String webUrl) {
        return instrumented(OpenWeb.class, webUrl);
    }
}
```

Dentro de la carpeta stepdefinitions se crea la clase java de los pasos con la siguiente estructura

```bash
Ej:
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
```

Dentro de la carpeta ui crear la clase java para localizar los elementos con la siguiente estructura

```bash
Ej:
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
```

Dentro de la carpeta tasks crear la clase java con las tareas con la siguiente estructura

```bash
Ej:
package co.com.aprender.auto.tasks;

import co.com.aprender.auto.models.UserModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;

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
```

Dentro de la carpeta questions crear la clase java para las validaciones con la siguiente estructura

```bash
Ej:
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
```

Crear los archivos necesarios segun el proyecto

```bash
    utils, models, etc
```

## Ejecutar Tests

Por comando gradle

```bash
  gradle clean test
```

Desde la clase java del runners

```bash
  IntelliJ: clic derecho - Run 'nombre de la clase java'
```

## Authors

- Jorge Franco

