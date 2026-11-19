
package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.LoginPage;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
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
                actor -> {
                    actor.attemptsTo(Click.on(LoginPage.USER_MENU));
                    boolean loginFormVisible = isTargetVisible(LoginPage.USERNAME, actor);
                    boolean signOutVisible = isTargetVisible(LoginPage.SIGN_OUT_OPTION, actor);
                    if (!loginFormVisible && signOutVisible) {
                        actor.attemptsTo(
                                Click.on(LoginPage.SIGN_OUT_OPTION),
                                Click.on(LoginPage.USER_MENU));
                    }
                    actor.attemptsTo(
                            WaitUntil.the(LoginPage.USERNAME, isVisible())
                                    .forNoMoreThan(15).seconds(),
                            Enter.theValue(username).into(LoginPage.USERNAME),
                            WaitUntil.the(LoginPage.PASSWORD, isVisible())
                                    .forNoMoreThan(10).seconds());
                    LoginPage.PASSWORD.resolveFor(actor).sendKeys(password);
                    actor.attemptsTo(Click.on(LoginPage.SIGN_IN_BUTTON));
                });
    }

    private static boolean isTargetVisible(
            net.serenitybdd.screenplay.targets.Target target,
            Actor actor) {
        return target.resolveAllFor(actor)
                .stream()
                .map(WebElementFacade::isVisible)
                .anyMatch(Boolean::booleanValue);
    }
}
