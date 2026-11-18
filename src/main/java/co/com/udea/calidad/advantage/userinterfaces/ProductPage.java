package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductPage {

    private ProductPage() {
    }

    public static final Target QUANTITY =
            Target.the("cantidad del producto")
                    .located(By.cssSelector("#product_2 input[name='quantity']"));

    public static final Target ADD_TO_CART =
            Target.the("botón para agregar el producto al carrito")
                    .located(By.cssSelector("#product_2 button[name='save_to_cart']"));

    public static Target productCard(String imageId) {
        return Target.the("producto " + imageId)
                .located(By.xpath("//li[.//img[@id='" + imageId + "']]"));
    }
}
