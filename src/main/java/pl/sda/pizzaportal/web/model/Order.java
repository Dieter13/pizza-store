package pl.sda.pizzaportal.web.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PizzaOrder")
@Table(name = "PIZZA_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String recipient;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pizza> pizzas = new HashSet<>();

    public Order() {
    }

    public Order(Long id, String recipient, String address, String email, String phoneNumber, LocalDate orderDate, Set<Pizza> pizzas) {
        this.id = id;
        this.recipient = recipient;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;

        pizzas.forEach(pizza -> pizza.setOrder(this));

        this.pizzas = pizzas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public double getTotalPrice() {
        return pizzas.stream().mapToDouble(Pizza::getTotalPrice).sum();
    }
}