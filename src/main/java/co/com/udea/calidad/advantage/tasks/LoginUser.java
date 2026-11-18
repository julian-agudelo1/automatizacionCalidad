
package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.LoginPage;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoginUser {

    private LoginUser() {}

    public static Performable withCredentials(
            String username, String password) {

        return Task.where("{0} inicia sesión",

                Click.on(LoginPage.USER_MENU),

                WaitUntil.the(LoginPage.USERNAME, isVisible())
                        .forNoMoreThan(15).seconds(),

                Enter.theValue(username)
                        .into(LoginPage.USERNAME),

                Enter.theValue(password)
                        .into(LoginPage.PASSWORD),

                Click.on(LoginPage.SIGN_IN_BUTTON)
        );
    }
}
