package pl.sda.pizzaportal.web.dto;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class NewPizzaRequest {

    @NotNull
    private Long pizzaTypeId;
    @NotNull
    private Long pizzaSizeId;

    private List<Long> pizzaToppingIds = new ArrayList<>();

    public Long getPizzaTypeId() {
        return pizzaTypeId;
    }

    public void setPizzaTypeId(Long pizzaTypeId) {
        this.pizzaTypeId = pizzaTypeId;
    }

    public Long getPizzaSizeId() {
        return pizzaSizeId;
    }

    public void setPizzaSizeId(Long pizzaSizeId) {
        this.pizzaSizeId = pizzaSizeId;
    }

    public List<Long> getPizzaToppingIds() {
        return pizzaToppingIds;
    }

    public void setPizzaToppingIds(List<Long> pizzaToppingIds) {
        this.pizzaToppingIds = pizzaToppingIds;
    }
}
