package pl.sda.pizzaportal.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.pizzaportal.web.dao.PizzaDAO;
import pl.sda.pizzaportal.web.dto.NewPizzaRequest;
import pl.sda.pizzaportal.web.helpers.PizzaUtils;
import pl.sda.pizzaportal.web.model.Pizza;
import pl.sda.pizzaportal.web.model.PizzaTopping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class PizzaController {

    private final PizzaDAO pizzaDAO;

    public PizzaController(@Qualifier("PizzaDAOImpl") PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }

    @GetMapping("/menu")
    public ModelAndView menuView() {
        ModelAndView modelAndView = new ModelAndView("menu");
        addAttributes(modelAndView);

        return modelAndView;
    }

    @PostMapping("/addPizza")
    public String addPizza(@ModelAttribute("newPizza") @Valid NewPizzaRequest newPizza, BindingResult binding, ModelMap model, HttpSession session) {
        if (binding.hasErrors()) {
            model.addAttribute("pizzaTypes", pizzaDAO.getPizzaTypes());
            model.addAttribute("pizzaSizes", pizzaDAO.getPizzaSizes());
            model.addAttribute("pizzaToppings", pizzaDAO.getPizzaToppings());

            return "menu";
        } else {
            updateCart(newPizza, session);
            return "redirect:/menu";
        }
    }

    private void addAttributes(ModelAndView modelAndView) {
        modelAndView.addObject("pizzaTypes", pizzaDAO.getPizzaTypes());
        modelAndView.addObject("pizzaSizes", pizzaDAO.getPizzaSizes());
        modelAndView.addObject("pizzaToppings", pizzaDAO.getPizzaToppings());
        modelAndView.addObject("newPizza", new NewPizzaRequest());
    }

    private void updateCart(NewPizzaRequest selectedPizza, HttpSession session) {
        List<Pizza> pizzaList = (List<Pizza>) session.getAttribute(PizzaUtils.PIZZA_LIST_ATTR);

        if (pizzaList == null) {
            pizzaList = new ArrayList<>();

        }

        Set<PizzaTopping> toppings = selectedPizza.getPizzaToppingIds().stream()
                .map(toppingId -> pizzaDAO.getPizzaTopping(toppingId))
                .collect(Collectors.toSet());

        Pizza newPizza = new Pizza(
                pizzaDAO.getPizzaType(selectedPizza.getPizzaTypeId()),
                pizzaDAO.getPizzaSize(selectedPizza.getPizzaSizeId()),
                toppings
        );

        pizzaList.add(newPizza);
        session.setAttribute(PizzaUtils.PIZZA_LIST_ATTR, pizzaList);
    }
}
