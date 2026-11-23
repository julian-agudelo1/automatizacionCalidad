package co.com.udea.calidad.advantage.stepdefinitions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import co.com.udea.calidad.advantage.interactions.WaitForLoader;
import co.com.udea.calidad.advantage.questions.CartContents;
import co.com.udea.calidad.advantage.tasks.AddProductToCart;
import co.com.udea.calidad.advantage.tasks.EmptyCart;
import co.com.udea.calidad.advantage.tasks.OpenCart;
import co.com.udea.calidad.advantage.tasks.RemoveProductFromCart;
import co.com.udea.calidad.advantage.tasks.UpdateCartQuantity;
import co.com.udea.calidad.advantage.userinterfaces.CartPage;
import co.com.udea.calidad.advantage.userinterfaces.CatalogPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingStepDefinitions {

    private static final String STORE_URL = "https://www.advantageonlineshopping.com/";

    @Given("the user opens the product catalog")
    public void openCatalog() {
        actor().attemptsTo(
                Open.url(STORE_URL),
                OpenCart.page(),
                EmptyCart.beforeStarting(),
                Open.url(STORE_URL),
                WaitForLoader.toFinish(),
                WaitUntil.the(CatalogPage.HOME_LOGO, isVisible())
                        .forNoMoreThan(60).seconds());
    }

    @When("the user adds the following products to the cart")
    public void addProducts(DataTable products) {
        for (Map<String, String> row : products.asMaps(String.class, String.class)) {
            actor().attemptsTo(AddProductToCart.withId(
                    row.get("category"),
                    row.get("productId"),
                    Integer.parseInt(row.get("quantity"))));
        }
    }

    @When("the user opens the cart")
    public void openCart() {
        actor().attemptsTo(OpenCart.page());
    }

    @Then("the following products appear in the cart")
    public void productsAppearInCart(DataTable products) {
        String cart = CartContents.summary().answeredBy(actor());
        for (Map<String, String> row : products.asMaps(String.class, String.class)) {
            assertTrue("The cart should contain " + row.get("productName"),
                    cart.contains(row.get("productName")));
        }
    }

    @When("the user updates the product quantity to the following value")
    public void updateProductQuantity(DataTable data) {
        int quantity = Integer.parseInt(
                data.asMaps(String.class, String.class).get(0).get("quantity"));
        actor().attemptsTo(
                OpenCart.page(),
                UpdateCartQuantity.to(quantity),
                OpenCart.page());
    }

    @Then("the cart reflects the following quantity and amount")
    public void cartReflectsQuantityAndAmount(DataTable data) {
        Map<String, String> row = data.asMaps(String.class, String.class).get(0);
        int quantity = Integer.parseInt(row.get("quantity"));
        BigDecimal expectedAmount = new BigDecimal(row.get("unitPrice"))
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, RoundingMode.HALF_UP);
        String expectedPrice = "$" + expectedAmount.toPlainString();
        String displayedQuantity = Text.of(CartPage.ITEM_QUANTITY).answeredBy(actor()).trim();
        String displayedPrice = Text.of(CartPage.ITEM_PRICE).answeredBy(actor())
                .replaceAll("\\s+", " ").trim();
        assertEquals(Integer.toString(quantity), displayedQuantity);
        assertTrue("Expected " + expectedPrice + " but was " + displayedPrice,
                displayedPrice.contains(expectedPrice));
    }

    @Then("the subtotal and the total are the following")
    public void subtotalAndTotalAre(DataTable data) {
        String expectedAmount = data.asMaps(String.class, String.class).get(0).get("amount");
        String itemPrice = Text.of(CartPage.ITEM_PRICE).answeredBy(actor());
        String total = Text.of(CartPage.TOTAL_ROW).answeredBy(actor());
        assertTrue("The subtotal must match " + expectedAmount + " but was " + itemPrice,
                itemPrice.contains(expectedAmount));
        assertTrue("The total must match " + expectedAmount + " but was " + total,
                total.contains(expectedAmount));
    }

    @When("the user removes the first product from the cart")
    public void removeFirstProduct() {
        actor().attemptsTo(
                OpenCart.page(),
                RemoveProductFromCart.firstItem());
    }

    @Then("the cart is empty")
    public void cartIsEmpty() {
        assertTrue("The empty cart message must be displayed",
                CartPage.EMPTY_CART_MESSAGE.resolveFor(actor()).isVisible());
    }

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }
}