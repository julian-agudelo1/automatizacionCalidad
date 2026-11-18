package co.com.udea.calidad.advantage.stepdefinitions;

import co.com.udea.calidad.advantage.questions.LoggedUser;
import co.com.udea.calidad.advantage.questions.RegistrationBlocked;
import co.com.udea.calidad.advantage.tasks.LoginUser;
import co.com.udea.calidad.advantage.tasks.RegisterUser;
import co.com.udea.calidad.advantage.userinterfaces.LoginPage;
import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.junit.Assert.assertTrue;

public class StepDefinition {

    @Given("que el usuario abre el formulario de registro")
    public void openRegistrationForm() {
        actor().attemptsTo(
                Open.url("https://www.advantageonlineshopping.com/#/register"),
                WaitUntil.the(RegistrationPage.REGISTER_BUTTON, isVisible())
                        .forNoMoreThan(20).seconds());
    }

    @When("completa el formulario de registro con datos válidos")
    public void registerWithValidData() {
        actor().attemptsTo(RegisterUser.withData(
                actor().recall("username"),
                actor().recall("email"),
                actor().recall("password")));
    }

    @When("intenta registrarse con un correo inválido")
    public void registerWithInvalidEmail() {
        actor().attemptsTo(RegisterUser.fillForm(
                actor().recall("username"),
                "correo-invalido",
                actor().recall("password")));
    }

    @When("intenta registrarse sin completar los campos obligatorios")
    public void registerWithRequiredFieldsEmpty() {
        assertTrue("El formulario vacío debería impedir el envío",
                RegistrationBlocked.onForm().answeredBy(actor()));
    }

    @Then("la cuenta queda registrada correctamente")
    public void accountIsRegisteredAndAuthenticated() {
        actor().attemptsTo(
                WaitUntil.the(LoginPage.USER_MENU, isVisible())
                        .forNoMoreThan(10).seconds());
        String displayedUser = LoggedUser.name().answeredBy(actor());
        assertTrue("El usuario no quedó autenticado después del registro: " + displayedUser,
                displayedUser.contains(actor().recall("username")));
    }

    @Then("el registro es bloqueado por la validación del formulario")
    public void registrationIsBlocked() {
        assertTrue("El botón de registro debería permanecer deshabilitado",
                RegistrationBlocked.onForm().answeredBy(actor()));
    }

    @Given("que existe una cuenta de prueba para iniciar sesión")
    public void createAccountForLogin() {
        openRegistrationForm();
        registerWithValidData();
        actor().attemptsTo(co.com.udea.calidad.advantage.tasks.SignOut.ofCurrentUser());
    }

    @When("inicia sesión con credenciales válidas")
    public void signInWithValidCredentials() {
        actor().attemptsTo(LoginUser.withCredentials(
                actor().recall("username"),
                actor().recall("password")));
    }

    @Then("el usuario queda autenticado con su cuenta")
    public void userIsAuthenticated() {
        String displayedUser = LoggedUser.name().answeredBy(actor());
        assertTrue("El menú no muestra el usuario autenticado: " + displayedUser,
                displayedUser.contains(actor().recall("username")));
    }

    @When("inicia sesión con una contraseña incorrecta")
    public void signInWithIncorrectPassword() {
        actor().attemptsTo(LoginUser.withCredentials(
                actor().recall("username"),
                "WrongPassword2026"));
    }

    @Then("el inicio de sesión es rechazado")
    public void signInIsRejected() {
        assertTrue("El formulario de inicio de sesión debería seguir visible",
                LoginPage.USERNAME.resolveFor(actor()).isVisible());
    }

    @Then("el usuario permanece en el formulario de registro")
    public void userRemainsOnRegistrationForm() {
        String currentUrl = BrowseTheWeb.as(actor()).getDriver().getCurrentUrl();
        assertTrue("La aplicación no debería abandonar el formulario: " + currentUrl,
                currentUrl.contains("#/register"));
    }

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }
}
