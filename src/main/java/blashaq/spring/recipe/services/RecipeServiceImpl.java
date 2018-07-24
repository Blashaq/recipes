package blashaq.spring.recipe.services;

import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepo;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I am in the service!\n" +
                "Getting recipes!");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepo.findAll().forEach(recipes::add);
        return recipes;
    }

    public Recipe getRecipeById(long id) {
        var recipe = recipeRepo.findById(id);
        if (!recipe.isPresent()) {
            throw new RuntimeException("unexpected missing data!");
        }
        return recipe.get();
    }
}
