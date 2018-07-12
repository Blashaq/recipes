package blashaq.spring.recipe.controllers;

import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.repositories.CategoryRepository;
import blashaq.spring.recipe.repositories.RecipeRepository;
import blashaq.spring.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private UnitOfMeasureRepository uomRepo;
    private CategoryRepository categoryRepo;
    private RecipeRepository recipeRepo;

    @Autowired
    public IndexController(UnitOfMeasureRepository uomRepo, CategoryRepository categoryRepo, RecipeRepository recipeRepo) {
        this.uomRepo = uomRepo;
        this.categoryRepo = categoryRepo;
        this.recipeRepo = recipeRepo;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        Iterable<Recipe> recipes = recipeRepo.findAll();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
