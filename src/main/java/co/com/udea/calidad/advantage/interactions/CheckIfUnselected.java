package co.com.udea.calidad.advantage.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CheckIfUnselected implements Interaction {

    private final Target checkbox;

    public CheckIfUnselected(Target checkbox) {
        this.checkbox = checkbox;
    }

    public static CheckIfUnselected the(Target checkbox) {
        return instrumented(CheckIfUnselected.class, checkbox);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade element = checkbox.resolveFor(actor);
        if (!element.isSelected()) {
            actor.attemptsTo(Click.on(checkbox));
        }
    }
}