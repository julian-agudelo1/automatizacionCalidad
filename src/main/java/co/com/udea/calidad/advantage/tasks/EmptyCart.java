package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.interactions.WaitForLoader;
import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EmptyCart {

    private static final int MAX_REMOVALS = 20;

    private EmptyCart() {
    }

    public static Performable beforeStarting() {
        return Task.where("{0} empties the cart before the scenario",
                actor -> {
                    int removals = 0;
                    while (!CartPage.REMOVE_FIRST_ITEM.resolveAllFor(actor).isEmpty()
                            && removals < MAX_REMOVALS) {
                        actor.attemptsTo(
                                Click.on(CartPage.REMOVE_FIRST_ITEM),
                                WaitForLoader.toFinish());
                        removals++;
                    }
                    actor.attemptsTo(WaitUntil.the(CartPage.EMPTY_CART_MESSAGE, isVisible())
                            .forNoMoreThan(30).seconds());
                });
    }
}