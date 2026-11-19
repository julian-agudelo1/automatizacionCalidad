package co.com.udea.calidad.advantage.stepdefinitions;

import co.com.udea.calidad.advantage.questions.PaymentBlocked;
import co.com.udea.calidad.advantage.tasks.AddProductToCart;
import co.com.udea.calidad.advantage.tasks.ContinueToPayment;
import co.com.udea.calidad.advantage.tasks.EmptyCart;
import co.com.udea.calidad.advantage.tasks.LoginUser;
import co.com.udea.calidad.advantage.tasks.PayWithTestCard;
import co.com.udea.calidad.advantage.tasks.StartCheckout;
import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import co.com.udea.calidad.advantage.userinterfaces.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static org.junit.Assert.assertTrue;

public class CheckoutStepDefinitions {

    @Given("que el usuario autenticado tiene un producto en el carrito")
    public void authenticatedUserHasProductInCart() {
        actor().attemptsTo(
                Open.url("https://www.advantageonlineshopping.com/"),
                EmptyCart.beforeStarting(),
                LoginUser.withCredentials(registeredUsername(), registeredPassword()),
                WaitUntil.the(LoginPage.USER_MENU, containsText(registeredUsername()))
                        .forNoMoreThan(20).seconds(),
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

    private String registeredUsername() {
        return requiredRegisteredCredential(
                "registeredUsername", "ADVANTAGE_TEST_USERNAME");
    }

    private String registeredPassword() {
        return requiredRegisteredCredential(
                "registeredPassword", "ADVANTAGE_TEST_PASSWORD");
    }

    private String requiredRegisteredCredential(String key, String environmentVariable) {
        String credential = actor().recall(key);
        if (credential == null || credential.isBlank()) {
            throw new IllegalStateException(
                    "Configura la variable de entorno " + environmentVariable
                            + " con una cuenta de prueba ya registrada.");
        }
        return credential;
    }
}
