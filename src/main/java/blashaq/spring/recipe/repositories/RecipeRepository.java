package blashaq.spring.recipe.repositories;

import blashaq.spring.recipe.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
