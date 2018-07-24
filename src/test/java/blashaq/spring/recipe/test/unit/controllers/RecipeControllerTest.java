package blashaq.spring.recipe.test.unit.controllers;

import blashaq.spring.recipe.controllers.RecipeController;
import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    @Mock
    RecipeService service;

    RecipeController controller;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new RecipeController(service);
    }

    @Test
    public void getRecipe() throws Exception {
        var recipe = new Recipe();
        recipe.setId(1l);
        when(service.getRecipeById(anyLong())).thenReturn(recipe);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
    }
}