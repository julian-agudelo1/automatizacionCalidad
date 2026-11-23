package co.com.udea.calidad.advantage.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class CheckIfUnselected {

    private CheckIfUnselected() {
    }

    public static Performable the(Target checkbox) {
        return Task.where("{0} marca " + checkbox.getName() + " si no está marcada",
                actor -> {
                    WebElementFacade element = checkbox.resolveFor(actor);
                    if (!element.isSelected()) {
                        actor.attemptsTo(Click.on(checkbox));
                    }
                });
    }
}