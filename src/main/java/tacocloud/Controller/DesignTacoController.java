package tacocloud.Controller;
/*
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacocloud.Data.Ingredient;
import tacocloud.Data.Taco;
import tacocloud.Repository.IngredientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import tacocloud.Data.Ingredient.Type;
import tacocloud.Repository.TacoRepository;


import javax.validation.Valid;



@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository=tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }
        return "design";
    }

    /*
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", Type.WRAP),
                new Ingredient("COTO", "kukurydziana", Type.WRAP),
                new Ingredient("GRBF", "wołowina", Type.PROTEIN),
                new Ingredient("CHIC", "kurczak", Type.PROTEIN),
                new Ingredient("TMTO", "pomidor", Type.VEGGIES),
                new Ingredient("LUTC", "sałata", Type.VEGGIES),
                new Ingredient("CHED", "cheedar", Type.CHEESE),
                new Ingredient("GOUD", "gouda", Type.CHEESE),
                new Ingredient("SALS", "salsa", Type.SAUCE),
                new Ingredient("SRCR", "śmietana", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("design",new Taco());
        return "design";
    }
*/ /*


    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@ModelAttribute("design") @Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacocloud.Data.Ingredient;
import tacocloud.Data.Order;
import tacocloud.Data.Taco;
import tacocloud.Repository.IngredientRepository;
import tacocloud.Repository.TacoRepository;

import tacocloud.Data.Ingredient.Type;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;


    private TacoRepository designRepo;



/*
  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

   */

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }


    // tag::modelAttributes[]
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Taco design, Errors errors,
            @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}