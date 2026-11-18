package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    private CartPage() {
    }

    public static final Target CART_LINK =
            Target.the("acceso al carrito").located(By.id("shoppingCartLink"));

    public static final Target CART_TITLE =
            Target.the("título del carrito")
                    .located(By.cssSelector("article.sp > h3"));

    public static final Target ITEM_ROWS =
            Target.the("productos del carrito")
                    .located(By.cssSelector("#shoppingCart table.fixedTableEdgeCompatibility tbody"));

    public static final Target ITEM_QUANTITY =
            Target.the("cantidad del producto en el carrito")
                    .located(By.cssSelector("#shoppingCart tbody tr:first-child td:nth-child(5) label.ng-binding"));

    public static final Target ITEM_PRICE =
            Target.the("importe del producto en el carrito")
                    .located(By.cssSelector("#shoppingCart tbody tr:first-child td:last-child p.price"));

    public static final Target TOTAL_ROW =
            Target.the("total del carrito")
                    .located(By.cssSelector("#shoppingCart table.fixedTableEdgeCompatibility tfoot tr:first-child td:last-child"));

    public static final Target EMPTY_CART_MESSAGE =
            Target.the("mensaje de carrito vacío")
                    .located(By.xpath("//*[@id='shoppingCart' and contains(., 'Your shopping cart is empty')]"));

    public static final Target REMOVE_FIRST_ITEM =
            Target.the("eliminar el producto del carrito")
                    .located(By.cssSelector("#shoppingCart a.remove"));

    public static final Target EDIT_FIRST_ITEM =
            Target.the("editar el producto del carrito")
                    .located(By.cssSelector("#shoppingCart a[href*='pageState=edit']"));

    public static final Target CHECKOUT_BUTTON =
            Target.the("botón para continuar al pago")
                    .located(By.id("checkOutButton"));
}
