package co.com.udea.calidad.advantage.interactions;

import co.com.udea.calidad.advantage.userinterfaces.UserInterface;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class WaitForLoader implements Interaction {

    public static WaitForLoader toFinish() {
        return instrumented(WaitForLoader.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(UserInterface.LOADER, isNotVisible())
                .forNoMoreThan(60).seconds());
    }
}