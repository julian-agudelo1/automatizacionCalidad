package co.com.udea.calidad.advantage.questions;

import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CartContents implements Question<String> {

    public static CartContents summary() {
        return new CartContents();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CartPage.ITEM_ROWS).answeredBy(actor)
                + "\n"
                + Text.of(CartPage.TOTAL_ROW).answeredBy(actor);
    }
}
