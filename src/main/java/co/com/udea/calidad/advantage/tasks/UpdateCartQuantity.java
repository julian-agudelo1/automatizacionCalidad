package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import co.com.udea.calidad.advantage.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class UpdateCartQuantity {

    private UpdateCartQuantity() {
    }

    public static Performable to(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("The quantity must be greater than zero");
        }

        return Task.where("{0} updates the product quantity",
                Click.on(CartPage.EDIT_FIRST_ITEM),
                WaitUntil.the(ProductPage.QUANTITY, isVisible())
                        .forNoMoreThan(60).seconds(),
                Enter.theValue(Keys.chord(Keys.CONTROL, "a") + quantity).into(ProductPage.QUANTITY),
                Click.on(ProductPage.ADD_TO_CART));
    }
}