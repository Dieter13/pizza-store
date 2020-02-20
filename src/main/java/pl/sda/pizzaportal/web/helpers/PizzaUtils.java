package pl.sda.pizzaportal.web.helpers;

/**
 * Klasa pomocnicza przechowująca parametry wykorzystywane w wielu klasach
 */
public class PizzaUtils {

    public static final String PIZZA_LIST_ATTR = "pizzaCartList";

    private static long idCounter = 1; // zmienna wykorzystywana do tworzenia obiektów w klasie mokującej

    private PizzaUtils() {
    }

    public static synchronized Long createID() {
        return idCounter++;
    }
}
