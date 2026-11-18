
package co.com.udea.calidad.advantage.questions;

import co.com.udea.calidad.advantage.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class LoggedUser implements Question<String> {

    public static LoggedUser name() {
        return new LoggedUser();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(LoginPage.USER_MENU).answeredBy(actor);
    }
}
