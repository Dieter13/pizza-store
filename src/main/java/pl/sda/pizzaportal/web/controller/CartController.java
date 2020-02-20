package pl.sda.pizzaportal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.pizzaportal.web.dto.NewOrderRequest;
import pl.sda.pizzaportal.web.helpers.PizzaUtils;
import pl.sda.pizzaportal.web.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CartController {

    @GetMapping("/cart")
    public ModelAndView cartView(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("cart");

        List<Pizza> pizzaList = (List<Pizza>) session.getAttribute(PizzaUtils.PIZZA_LIST_ATTR);

        if (pizzaList == null) {
            pizzaList = new ArrayList<>();

        }

        double totalPrice = pizzaList.stream().mapToDouble(Pizza::getTotalPrice).sum();

        modelAndView.addObject(PizzaUtils.PIZZA_LIST_ATTR, pizzaList);
        modelAndView.addObject("totalCount", totalPrice);
        modelAndView.addObject("newOrder", new NewOrderRequest());

        return modelAndView;
    }

    @GetMapping("/clear")
    protected String clearCart(HttpSession session) {
        session.setAttribute(PizzaUtils.PIZZA_LIST_ATTR, new ArrayList<>()); // tutaj ustawiamy pustę listę na wybranym atrybucie

        return "redirect:/cart"; //wykonujemy przekierowanie do widoku koszyczka
    }


    @GetMapping("/remove")
    protected String removeOneElement(@RequestParam(value = "index") int index, HttpSession session) throws ServletException, IOException {
        List<Pizza> pizzaList = (List<Pizza>) session.getAttribute(PizzaUtils.PIZZA_LIST_ATTR);
        pizzaList.remove(index); // Wywołanie metody usuwającej element z listy

        return "redirect:/cart"; //wykonujemy przekierowanie do widoku koszyczka
    }
}
