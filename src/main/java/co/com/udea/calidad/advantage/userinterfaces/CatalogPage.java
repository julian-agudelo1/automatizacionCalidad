package co.com.udea.calidad.advantage.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CatalogPage {

    private CatalogPage() {
    }

    public static final Target HOME_LOGO =
            Target.the("logo de la tienda")
                    .located(By.cssSelector("header .logo a"));  // el selector sale del inspector

    public static final Target SPEAKERS =
            Target.the("categoría de parlantes").located(By.id("speakersTxt"));

    public static final Target TABLETS =
            Target.the("categoría de tabletas").located(By.id("tabletsTxt"));

    public static final Target LAPTOPS =
            Target.the("categoría de portátiles").located(By.id("laptopsTxt"));

    public static final Target MICE =
            Target.the("categoría de ratones").located(By.id("miceTxt"));

    public static final Target HEADPHONES =
            Target.the("categoría de audífonos").located(By.id("headphonesTxt"));

    public static Target category(String category) {
        switch (category.toLowerCase()) {
            case "speakers":
                return SPEAKERS;
            case "tablets":
                return TABLETS;
            case "laptops":
                return LAPTOPS;
            case "mice":
                return MICE;
            case "headphones":
                return HEADPHONES;
            default:
                throw new IllegalArgumentException("Categoría no soportada: " + category);
        }
    }
}
