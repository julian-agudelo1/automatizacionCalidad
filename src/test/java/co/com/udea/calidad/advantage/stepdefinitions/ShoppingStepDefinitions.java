package co.com.udea.calidad.advantage.stepdefinitions;

import co.com.udea.calidad.advantage.questions.CartContents;
import co.com.udea.calidad.advantage.tasks.AddProductToCart;
import co.com.udea.calidad.advantage.tasks.EmptyCart;
import co.com.udea.calidad.advantage.tasks.OpenCart;
import co.com.udea.calidad.advantage.tasks.RemoveProductFromCart;
import co.com.udea.calidad.advantage.tasks.UpdateCartQuantity;
import co.com.udea.calidad.advantage.userinterfaces.CatalogPage;
import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Locale;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingStepDefinitions {

    private static final String SPEAKER = "BOSE SOUNDLINK BLUETOOTH SPEAKER III";
    private static final String TABLET = "HP ELITEPAD 1000 G2 TABLET";

    @Given("que el usuario abre el catálogo de productos")
    public void openCatalog() {
        actor().attemptsTo(
                Open.url("https://www.advantageonlineshopping.com/"),
                EmptyCart.beforeStarting(),
                Open.url("https://www.advantageonlineshopping.com/"),
                WaitUntil.the(CatalogPage.category("speakers"), isVisible())
                        .forNoMoreThan(20).seconds());
    }

    @When("agrega un producto al carrito")
    public void addOneProduct() {
        actor().attemptsTo(
                AddProductToCart.withId("speakers", "20", 1),
                OpenCart.page());
    }

    @Then("el producto aparece en el carrito")
    public void productAppearsInCart() {
        assertTrue(CartContents.summary().answeredBy(actor()).contains(SPEAKER));
    }

    @When("agrega productos de dos categorías diferentes")
    public void addProductsFromDifferentCategories() {
        actor().attemptsTo(
                AddProductToCart.withId("tablets", "16", 1),
                AddProductToCart.withId("speakers", "20", 1),
                OpenCart.page());
    }

    @Then("ambos productos aparecen en el carrito")
    public void bothProductsAppearInCart() {
        String cart = CartContents.summary().answeredBy(actor());
        assertTrue(cart.contains(TABLET));
        assertTrue(cart.contains(SPEAKER));
    }

    @When("modifica la cantidad del producto a {int}")
    public void updateProductQuantity(int quantity) {
        actor().attemptsTo(
                AddProductToCart.withId("speakers", "20", 1),
                UpdateCartQuantity.to(quantity));
    }

    @Then("el carrito refleja la cantidad {int} y su importe")
    public void cartReflectsQuantityAndAmount(int quantity) {
        String displayedQuantity = Text.of(CartPage.ITEM_QUANTITY).answeredBy(actor()).trim();
        String displayedPrice = Text.of(CartPage.ITEM_PRICE).answeredBy(actor()).replaceAll("\\s+", " ").trim();
        String expectedPrice = "$" + String.format(Locale.US, "%.2f", 269.99 * quantity);
        assertEquals(Integer.toString(quantity), displayedQuantity);
        assertTrue(displayedPrice.contains(expectedPrice));
    }

    @When("agrega dos unidades del parlante")
    public void addTwoSpeakers() {
        actor().attemptsTo(
                AddProductToCart.withId("speakers", "20", 2),
                OpenCart.page());
    }

    @Then("el subtotal de los productos y el total son {string}")
    public void subtotalAndTotalAre(String expectedAmount) {
        String itemPrice = Text.of(CartPage.ITEM_PRICE).answeredBy(actor());
        String total = Text.of(CartPage.TOTAL_ROW).answeredBy(actor());
        assertTrue("El subtotal debe coincidir con " + expectedAmount + ", pero fue " + itemPrice,
                itemPrice.contains(expectedAmount));
        assertTrue("El total debe coincidir con " + expectedAmount + ", pero fue " + total,
                total.contains(expectedAmount));
    }

    @When("elimina el producto del carrito")
    public void removeProduct() {
        actor().attemptsTo(
                AddProductToCart.withId("speakers", "20", 1),
                RemoveProductFromCart.firstItem());
    }

    @Then("el carrito queda vacío")
    public void cartIsEmpty() {
        assertTrue("Debe mostrarse el mensaje de carrito vacío",
                CartPage.EMPTY_CART_MESSAGE.resolveFor(actor()).isVisible());
    }

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }
}
