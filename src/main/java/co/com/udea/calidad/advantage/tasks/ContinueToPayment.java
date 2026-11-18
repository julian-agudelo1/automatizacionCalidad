package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ContinueToPayment {

    private ContinueToPayment() {
    }

    public static Performable withShippingDetails() {
        return Task.where("{0} confirma los datos de envío y selecciona el pago",
                Click.on(CheckoutPage.SHIPPING_NEXT),
                WaitUntil.the(CheckoutPage.PAYMENT_METHOD, isVisible())
                        .forNoMoreThan(15).seconds());
    }
}
