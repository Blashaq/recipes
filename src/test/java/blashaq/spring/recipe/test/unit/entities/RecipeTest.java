package blashaq.spring.recipe.test.unit.entities;

import blashaq.spring.recipe.entities.Ingredient;
import blashaq.spring.recipe.entities.Notes;
import blashaq.spring.recipe.entities.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class RecipeTest {
    private Recipe recipe;
    private Ingredient ing1;
    private Ingredient ing2;
    private Notes notes;

    @Before
    public void setUp() {
        recipe = new Recipe();
        ing1 = new Ingredient();
        ing2 = new Ingredient();
        ing1.setAmount(BigDecimal.TEN);
        ing1.setDescription("ing1");
        ing2.setAmount(BigDecimal.ONE);
        ing2.setDescription("ing2");
        notes = new Notes();
    }


    @Test
    public void addIngredient() {
        recipe.addIngredient(ing1);
        assertEquals(recipe.getIngredients().size(), 1);
        var recipeSet = Set.of(ing1, ing2);
        recipe.setIngredients(recipeSet);
        assertEquals(recipe.getIngredients(), recipeSet);
        assertEquals(ing1.getRecipe(), recipe);
        assertEquals(ing2.getRecipe(), recipe);
    }

    @Test
    public void setNotes() {
        recipe.setNotes(notes);
        assertEquals(recipe.getNotes(), notes);
        assertEquals(notes.getRecipe(), recipe);
    }
}