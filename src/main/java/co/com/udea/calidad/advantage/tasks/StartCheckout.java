package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class StartCheckout {

    private StartCheckout() {
    }

    public static Performable fromCart() {
        return Task.where("{0} starts the checkout",
                Click.on(CartPage.CHECKOUT_BUTTON),
                WaitUntil.the(CheckoutPage.SHIPPING_NEXT, isVisible())
                        .forNoMoreThan(60).seconds());
    }
}