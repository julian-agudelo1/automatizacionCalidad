package co.com.udea.calidad.advantage.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;

public class EnterSlowly {

    private static final long PAUSE_BETWEEN_KEYS_MS = 150;

    private EnterSlowly() {
    }

    public static Performable theValue(String value, Target target) {
        return Task.where("{0} escribe despacio en " + target.getName(),
                actor -> {
                    WebElementFacade field = target.resolveFor(actor);
                    field.click();
                    field.clear();
                    for (char character : value.toCharArray()) {
                        field.sendKeys(String.valueOf(character));
                        pause();
                    }
                });
    }

    private static void pause() {
        try {
            Thread.sleep(PAUSE_BETWEEN_KEYS_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}