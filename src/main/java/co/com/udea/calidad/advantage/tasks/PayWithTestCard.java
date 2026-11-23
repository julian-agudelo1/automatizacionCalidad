package co.com.udea.calidad.advantage.tasks;

import java.time.Year;

import co.com.udea.calidad.advantage.interactions.WaitForLoader;
import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import co.com.udea.calidad.advantage.interactions.EnterSlowly;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PayWithTestCard {

    private PayWithTestCard() {
    }

    public static Performable completePurchase() {
        String expiryYear = Integer.toString(Year.now().getValue() + 1);
        return Task.where("{0} paga con datos ficticios de prueba",
                WaitForLoader.toFinish(),
                Click.on(CheckoutPage.MASTER_CREDIT),
                WaitForLoader.toFinish(),
                WaitUntil.the(CheckoutPage.CARD_NUMBER, isVisible())
                        .forNoMoreThan(60).seconds(),
                EnterSlowly.theValue("123456789123", CheckoutPage.CARD_NUMBER),
                EnterSlowly.theValue("8915", CheckoutPage.CARD_CVV),
                SelectFromOptions.byVisibleText("12").from(CheckoutPage.CARD_MONTH),
                SelectFromOptions.byVisibleText(expiryYear).from(CheckoutPage.CARD_YEAR),
                Enter.theValue("AUTOMATION QA").into(CheckoutPage.CARDHOLDER),
                WaitUntil.the(CheckoutPage.PAY_NOW, isEnabled())
                        .forNoMoreThan(80).seconds(),
                WaitForLoader.toFinish(),
                Click.on(CheckoutPage.PAY_NOW),
                WaitUntil.the(CheckoutPage.ORDER_SUCCESS, isVisible())
                        .forNoMoreThan(180).seconds());
    }
}