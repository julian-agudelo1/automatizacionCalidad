
package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationPage {

    // ACCOUNT DETAILS

    public static final Target USERNAME = Target.the("campo de nombre de usuario")
            .located(By.name("usernameRegisterPage"));

    public static final Target EMAIL = Target.the("campo de correo electrónico")
            .located(By.name("emailRegisterPage"));

    public static final Target PASSWORD = Target.the("campo de contraseña")
            .located(By.name("passwordRegisterPage"));

    public static final Target CONFIRM_PASSWORD = Target.the("campo de confirmación de contraseña")
            .located(By.name("confirm_passwordRegisterPage"));

    // PERSONAL DETAILS

    public static final Target FIRST_NAME = Target.the("campo de nombre")
            .located(By.name("first_nameRegisterPage"));

    public static final Target LAST_NAME = Target.the("campo de apellido")
            .located(By.name("last_nameRegisterPage"));

    public static final Target PHONE_NUMBER = Target.the("campo de teléfono")
            .located(By.name("phone_numberRegisterPage"));

    // ADDRESS

    public static final Target COUNTRY = Target.the("lista de países")
            .located(By.name("countryListboxRegisterPage"));

    public static final Target CITY = Target.the("campo de ciudad")
            .located(By.name("cityRegisterPage"));

    public static final Target ADDRESS = Target.the("campo de dirección")
            .located(By.name("addressRegisterPage"));

    public static final Target STATE = Target.the("campo de departamento o provincia")
            .located(By.name("state_/_province_/_regionRegisterPage"));

    public static final Target POSTAL_CODE = Target.the("campo de código postal")
            .located(By.name("postal_codeRegisterPage"));

    // PREFERENCES AND AGREEMENT

    public static final Target OFFERS_PROMOTION = Target.the("casilla de ofertas y promociones")
            .located(By.name("allowOffersPromotion"));

    public static final Target AGREEMENT = Target.the("casilla de aceptación de condiciones")
            .located(By.name("i_agree"));
    
    public static final Target REGISTER_BUTTON = Target.the("botón para registrar la cuenta")
            .located(By.id("register_btn"));

    public static final Target REGISTRATION_SUCCESS =
            Target.the("confirmación de registro")
                    .located(By.id("registerSuccessCover"));

    public static final Target CONTINUE_SHOPPING =
            Target.the("continuar después del registro")
                    .located(By.cssSelector("#registerSuccessCover a"));
}
