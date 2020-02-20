package pl.sda.pizzaportal.web.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.sda.pizzaportal.web.model.Order;
import pl.sda.pizzaportal.web.model.PizzaSize;
import pl.sda.pizzaportal.web.model.PizzaTopping;
import pl.sda.pizzaportal.web.model.PizzaType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Klasa obsługująca połączenie z bazą danych
 */
@Component
@Qualifier("PizzaDAOImpl")
public class PizzaDAOImpl implements PizzaDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PizzaType> getPizzaTypes() {
        Query query = entityManager.createQuery("SELECT pt FROM PizzaType pt");

        List<PizzaType> pizzaTypes = query.getResultList();

        return pizzaTypes;
    }

    @Override
    public PizzaType getPizzaType(Long id) {
        return entityManager.find(PizzaType.class, id);
    }

    @Override
    public List<PizzaSize> getPizzaSizes() {
        Query query = entityManager.createQuery("SELECT ps FROM PizzaSize ps");

        List<PizzaSize> pizzaSizes = query.getResultList();

        return pizzaSizes;
    }

    @Override
    public PizzaSize getPizzaSize(Long id) {
        return entityManager.find(PizzaSize.class, id);
    }

    @Override
    public PizzaTopping getPizzaTopping(Long id) {
        return entityManager.find(PizzaTopping.class, id);
    }

    @Override
    public List<PizzaTopping> getPizzaToppings() {
        Query query = entityManager.createQuery("SELECT pt FROM PizzaTopping pt");

        List<PizzaTopping> pizzaToppings = query.getResultList();

        return pizzaToppings;
    }

    @Transactional
    @Override
    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public List<Order> getOrders() {
        Query query = entityManager.createQuery("SELECT o FROM PizzaOrder o");

        List<Order> orders = query.getResultList();

        return orders;
    }

    @Override
    public Order getOrder(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }

}


