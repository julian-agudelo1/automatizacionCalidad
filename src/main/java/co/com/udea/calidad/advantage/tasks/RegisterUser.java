
package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;

import java.time.Duration;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class RegisterUser {

    private RegisterUser() {
    }

    public static Performable withData(
            String username, String email, String password) {

        return Task.where("{0} registra un nuevo usuario",
                fillForm(username, email, password),
                WaitUntil.the(RegistrationPage.REGISTER_BUTTON, isEnabled())
                        .forNoMoreThan(10).seconds(),
                Click.on(RegistrationPage.REGISTER_BUTTON),
                WaitUntil.the(driver -> {
                    boolean registrationPageClosed = !driver.getCurrentUrl().contains("#/register");
                    boolean successMessageVisible = driver.findElements(By.id("registerSuccessCover"))
                            .stream()
                            .anyMatch(WebElement::isDisplayed);
                    return registrationPageClosed || successMessageVisible;
                }).forNoMoreThan(Duration.ofSeconds(20)));
    }

    public static Performable fillForm(
            String username, String email, String password) {

        return Task.where("{0} completa el formulario de registro",
                Enter.theValue(username)
                        .into(RegistrationPage.USERNAME),

                Enter.theValue(email)
                        .into(RegistrationPage.EMAIL),

                Enter.theValue(password)
                        .into(RegistrationPage.PASSWORD),

                Enter.theValue(password)
                        .into(RegistrationPage.CONFIRM_PASSWORD),

                Enter.theValue("Usuario")
                        .into(RegistrationPage.FIRST_NAME),

                Enter.theValue("Prueba")
                        .into(RegistrationPage.LAST_NAME),

                Enter.theValue("3001234567")
                        .into(RegistrationPage.PHONE_NUMBER),

                SelectFromOptions.byVisibleText("Colombia")
                        .from(RegistrationPage.COUNTRY),

                Enter.theValue("Medellin")
                        .into(RegistrationPage.CITY),

                Enter.theValue("Calle 10 # 20-30")
                        .into(RegistrationPage.ADDRESS),

                Enter.theValue("Antioquia")
                        .into(RegistrationPage.STATE),

                Enter.theValue("050001")
                        .into(RegistrationPage.POSTAL_CODE),

                Click.on(RegistrationPage.AGREEMENT));
    }
}
