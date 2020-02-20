package pl.sda.pizzaportal.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import pl.sda.pizzaportal.web.dao.PizzaDAO;
import pl.sda.pizzaportal.web.dao.PizzaDAOMock;
import pl.sda.pizzaportal.web.dto.NewOrderRequest;
import pl.sda.pizzaportal.web.helpers.PizzaUtils;
import pl.sda.pizzaportal.web.model.Order;
import pl.sda.pizzaportal.web.model.Pizza;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {

    private final PizzaDAO pizzaDAO;

    public OrderController(@Qualifier("PizzaDAOImpl") PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }

    @GetMapping("/order")
    public ModelAndView ordersView() {
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", pizzaDAO.getOrders());

        return modelAndView;
    }

    @GetMapping("/order/{orderId}")
    public ModelAndView orderDetailsView(@PathVariable("orderId") Long orderId) {
        ModelAndView modelAndView = new ModelAndView("orderDetails");
        Order order = pizzaDAO.getOrder(orderId);
        modelAndView.addObject("orderDetails", order);

        return modelAndView;
    }

    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute("newOrder") @Valid NewOrderRequest newOrderRequest, BindingResult binding, ModelMap model, HttpSession session) {
        List<Pizza> pizzaList = (List<Pizza>) session.getAttribute(PizzaUtils.PIZZA_LIST_ATTR);

        if (binding.hasErrors() || CollectionUtils.isEmpty(pizzaList)) {
            if (CollectionUtils.isEmpty(pizzaList)) {
                model.addAttribute("pizzaListIsEmpty", true);
            }

            if (pizzaList == null) {
                pizzaList = new ArrayList<>();
            }

            double totalPrice = pizzaList.stream().mapToDouble(Pizza::getTotalPrice).sum();

            model.addAttribute(PizzaUtils.PIZZA_LIST_ATTR, pizzaList);
            model.addAttribute("totalCount", totalPrice);

            return "cart";
        }

        Order newOrder = new Order(
                pizzaDAO instanceof PizzaDAOMock ? PizzaUtils.createID() : null,
                newOrderRequest.getRecipient(),
                newOrderRequest.getAddress(),
                newOrderRequest.getEmail(),
                newOrderRequest.getPhoneNumber(),
                LocalDate.now(),
                new HashSet<>(pizzaList)
        );

        pizzaDAO.addOrder(newOrder);

        session.setAttribute(PizzaUtils.PIZZA_LIST_ATTR, new ArrayList<>()); // tutaj ustawiamy pustę listę na wybranym atrybucie

        return "redirect:/menu";
    }
}
