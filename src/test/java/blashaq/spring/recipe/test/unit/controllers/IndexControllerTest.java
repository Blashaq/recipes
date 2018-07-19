package blashaq.spring.recipe.test.unit.controllers;

import blashaq.spring.recipe.controllers.IndexController;
import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    private Set<Recipe> recipes;
    private String index = "index";
    private IndexController indexController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    @Before
    public void setup() {
        initMocks(this);

        var recipe1 = new Recipe();
        var recipe2 = new Recipe();
        recipe1.setDescription("recipe1");
        recipe2.setDescription("recipe2");
        recipes = Set.of(recipe1, recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        assertEquals(indexController.getIndexPage(model), index);
        verify(recipeService, times(1)).getRecipes();

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        var recipesInController = captor.getValue();
        assertEquals(recipes.size(), recipesInController.size());

    }
}