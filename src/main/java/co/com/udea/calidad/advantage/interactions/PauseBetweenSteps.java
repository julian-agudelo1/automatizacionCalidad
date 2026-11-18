package co.com.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class PauseBetweenSteps implements Interaction {

    private final long delayMillis;

    private PauseBetweenSteps(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public static PauseBetweenSteps configuredForDemonstration() {
        long delay = Long.parseLong(System.getProperty("demo.delay.ms", "0"));
        if (delay < 0) {
            throw new IllegalArgumentException("demo.delay.ms no puede ser negativo");
        }
        return new PauseBetweenSteps(delay);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (delayMillis == 0) {
            return;
        }
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Se interrumpió la pausa de demostración", exception);
        }
    }
}
