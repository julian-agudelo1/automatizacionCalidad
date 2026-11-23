package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.userinterfaces.CatalogPage;
import co.com.udea.calidad.advantage.userinterfaces.ProductPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart {

    private AddProductToCart() {
    }

    public static Performable withId(String category, String productId, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        return Task.where("{0} agrega un producto al carrito",
                WaitUntil.the(CatalogPage.HOME_LOGO, isVisible())
                        .forNoMoreThan(30).seconds(),
                Click.on(CatalogPage.HOME_LOGO),
                WaitUntil.the(CatalogPage.category(category), isVisible())
                        .forNoMoreThan(60).seconds(),
                Click.on(CatalogPage.category(category)),
                WaitUntil.the(ProductPage.productCard(productId), isVisible())
                        .forNoMoreThan(180).seconds(),
                Click.on(ProductPage.productCard(productId)),
                WaitUntil.the(ProductPage.QUANTITY, isVisible())
                        .forNoMoreThan(90).seconds(),
                Enter.theValue(Keys.chord(Keys.CONTROL, "a") + quantity).into(ProductPage.QUANTITY),
                Click.on(ProductPage.ADD_TO_CART));
    }
}