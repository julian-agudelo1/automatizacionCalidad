package co.com.udea.calidad.advantage.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EnterSlowly implements Interaction {

    private static final long PAUSE_BETWEEN_KEYS_MS = 150;

    private final String value;
    private final Target target;

    public EnterSlowly(String value, Target target) {
        this.value = value;
        this.target = target;
    }

    public static EnterSlowly theValue(String value, Target target) {
        return instrumented(EnterSlowly.class, value, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade field = target.resolveFor(actor);
        field.click();
        field.clear();
        for (char character : value.toCharArray()) {
            field.sendKeys(String.valueOf(character));
            pause();
        }
    }

    private static void pause() {
        try {
            Thread.sleep(PAUSE_BETWEEN_KEYS_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}