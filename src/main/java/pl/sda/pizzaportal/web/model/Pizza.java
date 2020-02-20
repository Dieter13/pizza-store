package pl.sda.pizzaportal.web.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private PizzaType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private PizzaSize size;

    @ManyToMany()
    @JoinTable(name = "PIZZA_TO_PIZZA_TOPPING",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id")
    )
    private Set<PizzaTopping> toppings = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Pizza() {
    }

    public Pizza(PizzaType type, PizzaSize size, Set<PizzaTopping> toppings) {
        this.type = type;
        this.size = size;
        this.toppings = toppings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public Set<PizzaTopping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<PizzaTopping> toppings) {
        this.toppings = toppings;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getTotalPrice() {
        float totalPrice = type.getPrice() + size.getExtraPrice();

        if (toppings.size() > 0) {
            totalPrice += toppings.stream().mapToDouble(PizzaTopping::getExtraPrice).sum();
        }

        return totalPrice;
    }
}