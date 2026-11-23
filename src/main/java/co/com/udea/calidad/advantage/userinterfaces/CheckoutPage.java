package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    private CheckoutPage() {
    }

    public static final Target ORDER_PAYMENT_TITLE =
            Target.the("sección de pago del pedido")
                    .located(By.cssSelector("#orderPayment h3"));

    public static final Target SHIPPING_NEXT =
            Target.the("continuar desde los datos de envío")
                    .located(By.id("next_btn"));

    public static final Target PAYMENT_METHOD =
            Target.the("selección del medio de pago")
                    .located(By.id("paymentMethod"));

    public static final Target MASTER_CREDIT =
            Target.the("medio de pago MasterCredit")
                    .located(By.cssSelector("input[name='masterCredit']"));

    public static final Target CARD_NUMBER =
            Target.the("número de tarjeta de prueba")
                    .located(By.name("card_number"));

    public static final Target CARD_CVV =
            Target.the("CVV de prueba")
                    .located(By.name("cvv_number"));

    public static final Target CARD_MONTH =
            Target.the("mes de vencimiento")
                    .located(By.name("mmListbox"));

    public static final Target CARD_YEAR =
            Target.the("año de vencimiento")
                    .located(By.name("yyyyListbox"));

    public static final Target CARDHOLDER =
            Target.the("titular de tarjeta ficticio")
                    .located(By.name("cardholder_name"));

    public static final Target SAVE_CARD =
            Target.the("guardar tarjeta en el perfil")
                    .located(By.name("save_master_credit"));

    public static final Target PAY_NOW =
            Target.the("botón para pagar con MasterCredit")
                    .located(By.id("pay_now_btn_ManualPayment"));

    public static final Target ORDER_SUCCESS =
            Target.the("confirmación de compra")
                    .located(By.id("orderPaymentSuccess"));
}
