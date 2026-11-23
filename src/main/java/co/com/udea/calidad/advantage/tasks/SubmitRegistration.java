package co.com.udea.calidad.advantage.tasks;

import java.time.Duration;

import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class SubmitRegistration {

    private SubmitRegistration() {
    }

    public static Performable form() {
        return Task.where("{0} submits the registration form",
                WaitUntil.the(RegistrationPage.REGISTER_BUTTON, isEnabled())
                        .forNoMoreThan(30).seconds(),
                Click.on(RegistrationPage.REGISTER_BUTTON),
                WaitUntil.the(driver -> {
                    boolean registrationPageClosed = !driver.getCurrentUrl().contains("#/register");
                    boolean successMessageVisible = driver.findElements(By.id("registerSuccessCover"))
                            .stream()
                            .anyMatch(WebElement::isDisplayed);
                    return registrationPageClosed || successMessageVisible;
                }).forNoMoreThan(Duration.ofSeconds(60)));
    }
}