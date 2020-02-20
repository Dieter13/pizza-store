package pl.sda.pizzaportal.web.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.sda.pizzaportal.web.model.Order;
import pl.sda.pizzaportal.web.model.PizzaSize;
import pl.sda.pizzaportal.web.model.PizzaTopping;
import pl.sda.pizzaportal.web.model.PizzaType;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("PizzaDAOMock")
public class PizzaDAOMock implements PizzaDAO {

    /**
     * Mockowa lista dostępnych rodzajów pizz
     */
    private static final List<PizzaType> pizzaTypes = new ArrayList<>();

    /**
     * Mockowa lista dostępnych rozmiarów pizz
     */
    private static final List<PizzaSize> pizzaSizes = new ArrayList<>();

    /**
     * Mockowa lista dostępnych dodatków do pizzy
     */
    private static final List<PizzaTopping> pizzaToppings = new ArrayList<>();

    /**
     * Mockowa lista do przechowywania zamówień
     */
    private static List<Order> orders = new ArrayList<>();


    /**
     * Z racji że listy są statyczne inicjalizacja wykonywana jest tylko raz
     * */
    static {
        pizzaTypes.add(new PizzaType(1L, "Wiejska", "kiełbasa, ser, pieczarki", 15));
        pizzaTypes.add(new PizzaType(2L, "Vegetarian", "mozarella, pomidory, bazylia, zioła", 11));
        pizzaTypes.add(new PizzaType(3L, "Kebab pizza", "kurczak,  cebula czerwona, ogórki kiszone", 14));
        pizzaTypes.add(new PizzaType(4L, "Serowa", "mozarella, ser feta, sera żółty", 17));

        pizzaSizes.add(new PizzaSize(1L, "Mała", 0));
        pizzaSizes.add(new PizzaSize(2L, "Średnia", 3f));
        pizzaSizes.add(new PizzaSize(3L, "Duża", 6f));

        pizzaToppings.add(new PizzaTopping(1L, "Podwójny ser", 2f));
        pizzaToppings.add(new PizzaTopping(2L, "Podwójny ser mozzarella", 2f));
        pizzaToppings.add(new PizzaTopping(3L, "Podwójny kurczak", 2f));
        pizzaToppings.add(new PizzaTopping(4L, "Podwójne ciasto", 2f));
        pizzaToppings.add(new PizzaTopping(5L, "Sos czosnkowy", 2f));
        pizzaToppings.add(new PizzaTopping(6L, "Sos pomidorowy", 2f));
    }

    @Override
    public List<PizzaType> getPizzaTypes() {
        return pizzaTypes;
    }

    @Override
    public PizzaType getPizzaType(Long id) {
        return pizzaTypes.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<PizzaSize> getPizzaSizes() {
        return pizzaSizes;
    }

    @Override
    public PizzaSize getPizzaSize(Long id) {
        return pizzaSizes.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public PizzaTopping getPizzaTopping(Long id) {
        return pizzaToppings.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<PizzaTopping> getPizzaToppings() {
        return pizzaToppings;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Order getOrder(Long id) {
        return orders.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteOrder(Long id) {
         orders.remove(id);
    }
}
