package co.com.udea.calidad.advantage.tasks;

import java.time.Year;

import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PayWithTestCard {

    private PayWithTestCard() {
    }

    public static Performable completePurchase() {
        String expiryYear = Integer.toString(Year.now().getValue() + 1);
        return Task.where("{0} paga con datos ficticios de prueba",
                Click.on(CheckoutPage.MASTER_CREDIT),
                Enter.theValue("12345678901234").into(CheckoutPage.CARD_NUMBER),
                Enter.theValue("123").into(CheckoutPage.CARD_CVV),
                SelectFromOptions.byVisibleText("12").from(CheckoutPage.CARD_MONTH),
                SelectFromOptions.byVisibleText(expiryYear).from(CheckoutPage.CARD_YEAR),
                Enter.theValue("AUTOMATION QA").into(CheckoutPage.CARDHOLDER),
                Click.on(CheckoutPage.SAVE_CARD),
                WaitUntil.the(CheckoutPage.PAY_NOW, isEnabled())
                        .forNoMoreThan(10).seconds(),
                Click.on(CheckoutPage.PAY_NOW),
                WaitUntil.the(CheckoutPage.ORDER_SUCCESS, isVisible())
                        .forNoMoreThan(30).seconds());
    }
}
