package pl.sda.pizzaportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.pizzaportal.web.dao.PizzaDAOMock;

@SpringBootApplication
public class PizzaPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaPortalApplication.class, args);

        PizzaDAOMock pizzaDAOMock = new PizzaDAOMock();
    }

}
