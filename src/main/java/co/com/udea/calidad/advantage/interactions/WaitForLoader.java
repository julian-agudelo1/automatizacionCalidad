package co.com.udea.calidad.advantage.interactions;

import co.com.udea.calidad.advantage.userinterfaces.UserInterface;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class WaitForLoader {

    private WaitForLoader() {
    }

    public static Performable toFinish() {
        return Task.where("{0} espera a que la página termine de cargar",
                WaitUntil.the(UserInterface.LOADER, isNotVisible())
                        .forNoMoreThan(60).seconds());
    }
}