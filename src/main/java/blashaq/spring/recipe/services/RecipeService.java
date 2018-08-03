package blashaq.spring.recipe.services;

import blashaq.spring.recipe.commands.RecipeCommand;
import blashaq.spring.recipe.entities.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findRecipeById(long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(long id);

    void deleteRecipe(long id);
}
