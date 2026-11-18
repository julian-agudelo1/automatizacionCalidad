package co.com.udea.calidad.advantage.questions;

import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PaymentBlocked implements Question<Boolean> {

    public static PaymentBlocked untilRequiredFieldsAreCompleted() {
        return new PaymentBlocked();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return !CheckoutPage.PAY_NOW.resolveFor(actor).isEnabled();
    }
}
