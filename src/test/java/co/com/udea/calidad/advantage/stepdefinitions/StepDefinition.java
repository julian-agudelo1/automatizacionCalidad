package co.com.udea.calidad.advantage.stepdefinitions;

import java.util.Map;

import co.com.udea.calidad.advantage.models.UserData;
import co.com.udea.calidad.advantage.questions.LoggedUser;
import co.com.udea.calidad.advantage.questions.RegistrationBlocked;
import co.com.udea.calidad.advantage.tasks.FillRegistrationForm;
import co.com.udea.calidad.advantage.tasks.LoginUser;
import co.com.udea.calidad.advantage.tasks.SubmitRegistration;
import co.com.udea.calidad.advantage.userinterfaces.LoginPage;
import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.junit.Assert.assertTrue;

public class StepDefinition {

    private static final String STORE_URL = "https://www.advantageonlineshopping.com/";
    private static final String REGISTER_URL = STORE_URL + "#/register";

    @Given("the user opens the registration form")
    public void openRegistrationForm() {
        actor().attemptsTo(
                Open.url(REGISTER_URL),
                WaitUntil.the(RegistrationPage.REGISTER_BUTTON, isVisible())
                        .forNoMoreThan(90).seconds());
    }

    @When("the user fills the registration form with the following data")
    public void fillRegistrationForm(DataTable data) {
        UserData user = UserData.from(firstRow(data), actor().recall("username"),
                actor().recall("email"), actor().recall("password"));
        actor().attemptsTo(FillRegistrationForm.with(user));
    }

    @When("the user submits the registration form")
    public void submitRegistrationForm() {
        actor().attemptsTo(SubmitRegistration.form());
    }

    @Then("the account is registered successfully")
    public void accountIsRegistered() {
        String currentUrl = BrowseTheWeb.as(actor()).getDriver().getCurrentUrl();
        boolean successMessageVisible = RegistrationPage.REGISTRATION_SUCCESS
                .resolveAllFor(actor())
                .stream()
                .anyMatch(element -> element.isVisible());
        assertTrue("The registration form is still open without confirmation: " + currentUrl,
                !currentUrl.contains("#/register") || successMessageVisible);
    }

    @Then("the registration is blocked by the form validation")
    public void registrationIsBlocked() {
        assertTrue("The register button should remain disabled",
                RegistrationBlocked.onForm().answeredBy(actor()));
    }

    @When("the user tries to register without completing the required fields")
    public void registerWithRequiredFieldsEmpty() {
        assertTrue("The empty form should prevent the submission",
                RegistrationBlocked.onForm().answeredBy(actor()));
    }

    @Then("the user remains on the following page")
    public void userRemainsOnPage(DataTable data) {
        String expectedPage = firstRow(data).get("page");
        String currentUrl = BrowseTheWeb.as(actor()).getDriver().getCurrentUrl();
        assertTrue("The application should stay on " + expectedPage + " but is at " + currentUrl,
                currentUrl.contains(expectedPage));
    }

    @Given("a new user is registered with the following password")
    public void newUserIsRegistered(DataTable data) {
        actor().remember("password", firstRow(data).get("password"));
        registerNewUser();
    }

    @Given("a new user is registered and signed in")
    public void newUserIsRegisteredAndSignedIn() {
        UserData user = registerNewUser();
        String shown = LoggedUser.name().answeredBy(actor());
        if (shown == null || !shown.contains(user.username())) {
            actor().attemptsTo(
                    LoginUser.withCredentials(user.username(), user.password()),
                    WaitUntil.the(LoginPage.USER_MENU, containsText(user.username()))
                            .forNoMoreThan(60).seconds());
        }
    }

    @When("the user signs in with the following password")
    public void signIn(DataTable data) {
        actor().attemptsTo(LoginUser.withCredentials(
                actor().recall("username"), firstRow(data).get("password")));
    }

    @Then("the user is authenticated with the registered account")
    public void userIsAuthenticated() {
        String username = actor().recall("username");
        actor().attemptsTo(WaitUntil.the(LoginPage.USER_MENU, containsText(username))
                .forNoMoreThan(30).seconds());
        String displayedUser = LoggedUser.name().answeredBy(actor());
        assertTrue("The menu does not show the authenticated user: " + displayedUser,
                displayedUser.contains(username));
    }

    @Then("the sign in is rejected")
    public void signInIsRejected() {
        actor().attemptsTo(
                WaitUntil.the(LoginPage.SIGN_IN_RESULT, isVisible())
                        .forNoMoreThan(30).seconds());
        String result = LoginPage.SIGN_IN_RESULT.resolveFor(actor()).getText();
        assertTrue("The sign in form should still be visible",
                LoginPage.USERNAME.resolveFor(actor()).isVisible());
        assertTrue("The rejection reason must be displayed", !result.isBlank());
    }

    private UserData registerNewUser() {
        UserData user = UserData.withDefaults(actor().recall("username"),
                actor().recall("email"), actor().recall("password"));
        actor().attemptsTo(
                Open.url(REGISTER_URL),
                WaitUntil.the(RegistrationPage.REGISTER_BUTTON, isVisible())
                        .forNoMoreThan(60).seconds(),
                FillRegistrationForm.with(user),
                SubmitRegistration.form(),
                Open.url(STORE_URL),
                WaitUntil.the(LoginPage.USER_MENU, isVisible())
                        .forNoMoreThan(60).seconds());
        return user;
    }

    private Map<String, String> firstRow(DataTable data) {
        return data.asMaps(String.class, String.class).get(0);
    }

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }
}