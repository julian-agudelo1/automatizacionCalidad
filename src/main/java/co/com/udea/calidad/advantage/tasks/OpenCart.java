package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.interactions.WaitForLoader;
import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenCart {

    private OpenCart() {
    }

    public static Performable page() {
        return Task.where("{0} consulta el carrito",
                WaitForLoader.toFinish(),
                WaitUntil.the(CartPage.CART_LINK, isVisible())
                        .forNoMoreThan(30).seconds(),
                Click.on(CartPage.CART_LINK),
                WaitForLoader.toFinish(),
                WaitUntil.the(CartPage.CART_TITLE, isVisible())
                        .forNoMoreThan(30).seconds());
    }
}