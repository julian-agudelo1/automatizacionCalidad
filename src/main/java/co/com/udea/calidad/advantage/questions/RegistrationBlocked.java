package co.com.udea.calidad.advantage.questions;

import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RegistrationBlocked implements Question<Boolean> {

    public static RegistrationBlocked onForm() {
        return new RegistrationBlocked();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return !RegistrationPage.REGISTER_BUTTON.resolveFor(actor).isEnabled();
    }
}
