package co.com.udea.calidad.advantage.stepdefinitions;

import java.util.Map;

import co.com.udea.calidad.advantage.interactions.WaitForLoader;
import co.com.udea.calidad.advantage.questions.PaymentBlocked;
import co.com.udea.calidad.advantage.tasks.ContinueToPayment;
import co.com.udea.calidad.advantage.tasks.OpenCart;
import co.com.udea.calidad.advantage.tasks.PayWithTestCard;
import co.com.udea.calidad.advantage.tasks.StartCheckout;
import co.com.udea.calidad.advantage.userinterfaces.CheckoutPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;

import static org.junit.Assert.assertTrue;

public class CheckoutStepDefinitions {

    @When("the user pays with the following test card")
    public void payWithTestCard(DataTable cardData) {
        Map<String, String> card = cardData.asMaps(String.class, String.class).get(0);
        actor().attemptsTo(
                OpenCart.page(),
                StartCheckout.fromCart(),
                ContinueToPayment.withShippingDetails(),
                PayWithTestCard.completePurchase(
                        card.get("cardNumber"), card.get("cvv"),
                        card.get("month"), card.get("year"), card.get("cardholder")));
    }

    @Then("the purchase confirmation is displayed")
    public void purchaseIsConfirmed() {
        String confirmation = CheckoutPage.ORDER_SUCCESS.resolveFor(actor()).getText();
        assertTrue("The purchase confirmation was not found: " + confirmation,
                confirmation.contains("Thank you for buying with Advantage"));
    }

    @When("the user tries to pay without completing the required fields")
    public void attemptPaymentWithMissingFields() {
        actor().attemptsTo(
                OpenCart.page(),
                StartCheckout.fromCart(),
                ContinueToPayment.withShippingDetails(),
                WaitForLoader.toFinish(),
                Click.on(CheckoutPage.MASTER_CREDIT));
    }

    @Then("the payment is blocked")
    public void paymentIsBlocked() {
        assertTrue("The pay button must be disabled without the required data",
                PaymentBlocked.untilRequiredFieldsAreCompleted().answeredBy(actor()));
    }

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }
}