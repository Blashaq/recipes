package blashaq.spring.recipe.services;

import blashaq.spring.recipe.entities.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(long id);
}
