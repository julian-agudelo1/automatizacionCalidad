package co.com.udea.calidad.advantage.stepdefinitions;

import co.com.udea.calidad.advantage.questions.PaymentBlocked;
import co.com.udea.calidad.advantage.tasks.AddProductToCart;
import co.com.udea.calidad.advantage.tasks.ContinueToPayment;
import co.com.udea.calidad.advantage.tasks.EmptyCart;
import co.com.udea.calidad.advantage.tasks.PayWithTestCard;
import co.com.udea.calidad.advantage.tasks.RegisterUser;
import co.com.udea.calidad.advantage.tasks.StartCheckout;
import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.junit.Assert.assertTrue;

public class CheckoutStepDefinitions {

    @Given("que el usuario autenticado tiene un producto en el carrito")
    public void authenticatedUserHasProductInCart() {
        actor().attemptsTo(
                Open.url("https://www.advantageonlineshopping.com/"),
                EmptyCart.beforeStarting(),
                Open.url("https://www.advantageonlineshopping.com/#/register"),
                WaitUntil.the(RegistrationPage.REGISTER_BUTTON, isVisible())
                        .forNoMoreThan(20).seconds(),
                RegisterUser.withData(
                        actor().recall("username"),
                        actor().recall("email"),
                        actor().recall("password")),
                AddProductToCart.withId("speakers", "20", 1));
    }

    @When("completa los datos ficticios de pago y confirma la compra")
    public void submitDemoPayment() {
        actor().attemptsTo(
                StartCheckout.fromCart(),
                ContinueToPayment.withShippingDetails(),
                PayWithTestCard.completePurchase());
    }

    @Then("se muestra la confirmación de la compra")
    public void purchaseIsConfirmed() {
        String confirmation = CheckoutPage.ORDER_SUCCESS.resolveFor(actor()).getText();
        assertTrue("No se encontró la confirmación de compra: " + confirmation,
                confirmation.contains("Thank you for buying with Advantage"));
    }

    @When("intenta pagar sin completar los datos obligatorios")
    public void attemptPaymentWithMissingFields() {
        actor().attemptsTo(
                StartCheckout.fromCart(),
                ContinueToPayment.withShippingDetails(),
                net.serenitybdd.screenplay.actions.Click.on(CheckoutPage.MASTER_CREDIT));
    }

    @Then("el sistema impide continuar con el pago")
    public void paymentIsBlocked() {
        assertTrue("El botón de pago debe estar deshabilitado sin los datos requeridos",
                PaymentBlocked.untilRequiredFieldsAreCompleted().answeredBy(actor()));
    }

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }
}
