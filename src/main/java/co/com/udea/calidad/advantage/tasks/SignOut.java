package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SignOut {

    private SignOut() {
    }

    public static Performable ofCurrentUser() {
        return Task.where("{0} signs out",
                Click.on(LoginPage.USER_MENU),
                WaitUntil.the(LoginPage.SIGN_OUT_OPTION, isVisible())
                        .forNoMoreThan(10).seconds(),
                Click.on(LoginPage.SIGN_OUT_OPTION));
    }
}
