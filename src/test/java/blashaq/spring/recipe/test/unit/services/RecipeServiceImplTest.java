package blashaq.spring.recipe.test.unit.services;

import blashaq.spring.recipe.repositories.RecipeRepository;
import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepo);
        var recipe = new Recipe();
        Set<Recipe> recipes = Set.of(recipe);
        when(recipeRepo.findAll()).thenReturn(recipes);
    }

    @Test
    public void getRecipes() {
        var recipes = recipeService.getRecipes();
        assertNotEquals(recipes.size(), 0);
        verify(recipeRepo, times(1)).findAll();
    }

}