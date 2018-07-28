package blashaq.spring.recipe.test.integration.services;

import blashaq.spring.recipe.converters.RecipeCommandToRecipe;
import blashaq.spring.recipe.converters.RecipeToRecipeCommand;
import blashaq.spring.recipe.repositories.RecipeRepository;
import blashaq.spring.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    private String description = "description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() {
        var recipes = recipeRepository.findAll();
        var testRecipe = recipes.iterator().next();
        var recipeCommand = recipeToRecipeCommand.convert(testRecipe);

        recipeCommand.setDescription(description);
        testRecipe.setDescription(description);

        var savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
        assertEquals(testRecipe.getDescription(), savedRecipeCommand.getDescription());

    }

}
