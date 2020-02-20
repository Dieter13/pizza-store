package pl.sda.pizzaportal.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PizzaTopping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public PizzaTopping() {
    }

    public PizzaTopping(Long id, String name, float extraPrice) {
        this.id = id;
        this.name = name;
        this.extraPrice = extraPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(float extraPrice) {
        this.extraPrice = extraPrice;
    }

}