package blashaq.spring.recipe.services;

import blashaq.spring.recipe.commands.RecipeCommand;
import blashaq.spring.recipe.converters.RecipeCommandToRecipe;
import blashaq.spring.recipe.converters.RecipeToRecipeCommand;
import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepo;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepo, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepo = recipeRepo;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        log.debug("saving recipe!");
        Recipe savedRecipe = recipeRepo.save(detachedRecipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }


}
