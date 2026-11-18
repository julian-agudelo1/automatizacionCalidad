
package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target USER_MENU =
            Target.the("menú del usuario")
                    .located(By.id("menuUserLink"));

    public static final Target LOGIN_MODAL =
            Target.the("modal de inicio de sesión")
                    .located(By.cssSelector("div.login"));

    public static final Target USERNAME =
            Target.the("campo de usuario")
                    .located(By.name("username"));

    public static final Target PASSWORD =
            Target.the("campo de contraseña")
                    .located(By.name("password"));

    public static final Target SIGN_IN_BUTTON =
            Target.the("botón de inicio de sesión")
                    .located(By.id("sign_in_btn"));

    public static final Target SIGN_OUT_OPTION =
            Target.the("opción para cerrar sesión")
                    .located(By.cssSelector("#loginMiniTitle label.option"));

    public static final Target SIGN_IN_RESULT =
            Target.the("resultado del inicio de sesión")
                    .located(By.id("signInResultMessage"));
}
