package blashaq.spring.recipe.test.unit.entities;

import blashaq.spring.recipe.entities.Category;
import blashaq.spring.recipe.entities.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CategoryTest {

    Category category;
    Recipe rec1;
    Recipe rec2;

    @Before
    public void setUp() {
        category = new Category();
        category.setDescription("cat");
        var additionalCategory1 = new Category();
        additionalCategory1.setDescription("cat1");

        var additionalCategory2 = new Category();
        additionalCategory2.setDescription("cat2");

        rec1 = new Recipe();
        rec1.setDescription("rec1");
        rec1.getCategories().add(additionalCategory1);
        rec2 = new Recipe();
        rec2.setDescription("rec2");
        rec2.getCategories().add(additionalCategory2);
    }

    @Test
    public void setRecipes() {
        var recipes = Set.of(rec1, rec2);
        category.setRecipes(recipes);
        assertEquals(category.getRecipes(), recipes);
        for (var r : recipes) {
            assertTrue(r.getCategories().contains(category));
            assertTrue(r.getCategories().size() > 1);
        }
    }

}