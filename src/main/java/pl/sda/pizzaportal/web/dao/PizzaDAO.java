package pl.sda.pizzaportal.web.dao;

import pl.sda.pizzaportal.web.model.Order;
import pl.sda.pizzaportal.web.model.PizzaSize;
import pl.sda.pizzaportal.web.model.PizzaTopping;
import pl.sda.pizzaportal.web.model.PizzaType;

import java.util.List;

public interface PizzaDAO {

    List<PizzaType> getPizzaTypes();

    PizzaType getPizzaType(Long id);

    List<PizzaSize> getPizzaSizes();

    PizzaSize getPizzaSize(Long id);

    PizzaTopping getPizzaTopping(Long id);

    List<PizzaTopping> getPizzaToppings();

    void addOrder(Order order);

    List<Order> getOrders();

    Order getOrder(Long id);

    void deleteOrder(Long id);
}
