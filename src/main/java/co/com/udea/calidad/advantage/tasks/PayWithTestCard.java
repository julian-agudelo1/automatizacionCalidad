package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.interactions.CheckIfUnselected;
import co.com.udea.calidad.advantage.interactions.EnterSlowly;
import co.com.udea.calidad.advantage.interactions.WaitForLoader;
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

    public static Performable completePurchase(String cardNumber, String cvv, String month,
                                               String year, String cardholder) {
        return Task.where("{0} pays with the test card",
                WaitForLoader.toFinish(),
                Click.on(CheckoutPage.MASTER_CREDIT),
                WaitForLoader.toFinish(),
                WaitUntil.the(CheckoutPage.CARD_NUMBER, isVisible())
                        .forNoMoreThan(60).seconds(),
                EnterSlowly.theValue(cardNumber, CheckoutPage.CARD_NUMBER),
                EnterSlowly.theValue(cvv, CheckoutPage.CARD_CVV),
                SelectFromOptions.byVisibleText(month).from(CheckoutPage.CARD_MONTH),
                SelectFromOptions.byVisibleText(year).from(CheckoutPage.CARD_YEAR),
                Enter.theValue(cardholder).into(CheckoutPage.CARDHOLDER),
                CheckIfUnselected.the(CheckoutPage.SAVE_CARD),
                WaitUntil.the(CheckoutPage.PAY_NOW, isEnabled())
                        .forNoMoreThan(60).seconds(),
                WaitForLoader.toFinish(),
                Click.on(CheckoutPage.PAY_NOW),
                WaitUntil.the(CheckoutPage.ORDER_SUCCESS, isVisible())
                        .forNoMoreThan(120).seconds());
    }
}