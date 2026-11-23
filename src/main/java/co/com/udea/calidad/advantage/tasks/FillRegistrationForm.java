package co.com.udea.calidad.advantage.tasks;

import co.com.udea.calidad.advantage.models.UserData;
import co.com.udea.calidad.advantage.userinterfaces.RegistrationPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class FillRegistrationForm {

    private FillRegistrationForm() {
    }

    public static Performable with(UserData user) {
        return Task.where("{0} fills the registration form",
                Enter.theValue(user.username()).into(RegistrationPage.USERNAME),
                Enter.theValue(user.email()).into(RegistrationPage.EMAIL),
                Enter.theValue(user.password()).into(RegistrationPage.PASSWORD),
                Enter.theValue(user.password()).into(RegistrationPage.CONFIRM_PASSWORD),
                Enter.theValue(user.firstName()).into(RegistrationPage.FIRST_NAME),
                Enter.theValue(user.lastName()).into(RegistrationPage.LAST_NAME),
                Enter.theValue(user.phone()).into(RegistrationPage.PHONE_NUMBER),
                SelectFromOptions.byVisibleText(user.country()).from(RegistrationPage.COUNTRY),
                Enter.theValue(user.city()).into(RegistrationPage.CITY),
                Enter.theValue(user.address()).into(RegistrationPage.ADDRESS),
                Enter.theValue(user.state()).into(RegistrationPage.STATE),
                Enter.theValue(user.postalCode()).into(RegistrationPage.POSTAL_CODE),
                Click.on(RegistrationPage.AGREEMENT));
    }
}