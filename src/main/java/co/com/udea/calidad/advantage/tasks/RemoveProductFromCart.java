package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RemoveProductFromCart {

    private RemoveProductFromCart() {
    }

    public static Performable firstItem() {
        return Task.where("{0} elimina el producto del carrito",
                OpenCart.page(),
                Click.on(CartPage.REMOVE_FIRST_ITEM),
                WaitUntil.the(CartPage.CART_TITLE, isVisible())
                        .forNoMoreThan(15).seconds());
    }
}
