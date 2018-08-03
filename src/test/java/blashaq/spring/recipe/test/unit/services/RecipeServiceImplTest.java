package blashaq.spring.recipe.test.unit.services;

import blashaq.spring.recipe.commands.RecipeCommand;
import blashaq.spring.recipe.converters.RecipeCommandToRecipe;
import blashaq.spring.recipe.converters.RecipeToRecipeCommand;
import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.repositories.RecipeRepository;
import blashaq.spring.recipe.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    private RecipeRepository recipeRepo;


    private Long recId = 5l;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepo, recipeCommandToRecipe, recipeToRecipeCommand);
        var recipe = new Recipe();
        recipe.setId(recId);
        var recipeCommand = new RecipeCommand();
        recipeCommand.setId(recId);
        Set<Recipe> recipes = Set.of(recipe);
        when(recipeRepo.findAll()).thenReturn(recipes);
        when(recipeRepo.findById(recId)).thenReturn(Optional.of(recipe));
        when(recipeToRecipeCommand.convert(any(Recipe.class))).thenReturn(recipeCommand);
        when(recipeCommandToRecipe.convert(any(RecipeCommand.class))).thenReturn(recipe);
    }

    @Test
    public void getRecipes() {
        var recipes = recipeService.getRecipes();
        assertNotEquals(recipes.size(), 0);
        verify(recipeRepo, times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        var recipe = recipeService.findRecipeById(recId);
        assertEquals(recipe.getId(), recId);
    }

    @Test(expected = RuntimeException.class)
    public void errorOnBadId() {
        recipeService.findRecipeById(9090909l);
    }

    @Test
    public void deleteRecipe() {
        recipeService.deleteRecipe(recId);
        verify(recipeRepo, times(1)).deleteById(recId);

    }

}