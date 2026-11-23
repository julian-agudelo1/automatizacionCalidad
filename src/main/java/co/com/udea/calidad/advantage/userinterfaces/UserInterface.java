package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class UserInterface {

    private UserInterface() {
    }

    public static final Target LOADER =
            Target.the("indicador de carga de la página")
                    .located(By.cssSelector("div.loader"));
}