package blashaq.spring.recipe.services;

import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepo;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepo.findAll().forEach(recipes::add);
        return recipes;
    }
}
