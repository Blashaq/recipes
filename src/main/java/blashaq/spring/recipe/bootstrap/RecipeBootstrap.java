package blashaq.spring.recipe.bootstrap;

import blashaq.spring.recipe.entities.Difficulty;
import blashaq.spring.recipe.entities.Ingredient;
import blashaq.spring.recipe.entities.Notes;
import blashaq.spring.recipe.entities.Recipe;
import blashaq.spring.recipe.repositories.CategoryRepository;
import blashaq.spring.recipe.repositories.RecipeRepository;
import blashaq.spring.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    RecipeRepository recipeRepo;
    UnitOfMeasureRepository uomRepo;
    CategoryRepository categoryRepo;

    @Autowired
    public RecipeBootstrap(RecipeRepository recipeRepo, UnitOfMeasureRepository uomRepo, CategoryRepository categoryRepo) {
        this.recipeRepo = recipeRepo;
        this.uomRepo = uomRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Recipe chickenRecipe = buildChickenRecipe();
        recipeRepo.save(chickenRecipe);
        Recipe guacRecipe = buildGuacRecipe();
        recipeRepo.save(guacRecipe);
    }

    private Recipe buildGuacRecipe() {
        Recipe guacRecipe = new Recipe();
        guacRecipe.setIngredients(buildGuacIngredients());
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setCookTime(0);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setPrepTime(10);
        guacRecipe.setServings(4);
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        var guacNotes = buildGuacNotes();
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);
        guacRecipe.setCategories(Set.of(categoryRepo.findByDescription("american").get(),
                categoryRepo.findByDescription("mexican").get()));
        return guacRecipe;
    }

    private Notes buildGuacNotes() {
        var notes = new Notes();
        notes.setRecipeNotes("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "EASY PEASY GUACAMOLE\n" +
                "Guacamole is so easy. All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity— will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "\n" +
                "Once you have basic guacamole down, you may experiment with variations including strawberries, pineapple, mangoes, even watermelon. You can get creative with homemade guacamole!\n" +
                "\n" +
                "GUACAMOLE TIP: USE RIPE AVOCADOS\n" +
                "The trick to making perfect guacamole is using good, ripe avocados.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.");
        return notes;
    }

    private Set<Ingredient> buildGuacIngredients() {
        var tablespoon = uomRepo.findByUnit("tablespoon").get();
        var dash = uomRepo.findByUnit("dash").get();
        var avocados = new Ingredient("avocados", BigDecimal.valueOf(2), null);
        var salt = new Ingredient("Kosher salt", BigDecimal.ONE, tablespoon);
        var lemon = new Ingredient("fresh lime or lemon juice", BigDecimal.ONE, tablespoon);
        var onion = new Ingredient("Minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), tablespoon);
        var cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.ONE, null);
        var pepper = new Ingredient("freshly grated black pepper", BigDecimal.ONE, dash);
        var tomato = new Ingredient("ripe tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(0.5), null);
        return Set.of(avocados, salt, lemon, onion, cilantro, pepper, tomato);

    }

    private Recipe buildChickenRecipe() {
        Recipe chickenRecipe = new Recipe();
        chickenRecipe.setIngredients(buildChickenIngredients());
        chickenRecipe.setDescription("Spicy Grilled Chicken Tacos ");
        chickenRecipe.setCookTime(15);
        chickenRecipe.setDifficulty(Difficulty.EASY);
        chickenRecipe.setPrepTime(20);
        chickenRecipe.setServings(4);
        chickenRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        var chickenNotes = buildChickenNotes();
        chickenNotes.setRecipe(chickenRecipe);
        chickenRecipe.setNotes(chickenNotes);
        return chickenRecipe;
    }

    private Set<Ingredient> buildChickenIngredients() throws RuntimeException {
        var tablespoon = uomRepo.findByUnit("tablespoon").get();
        var teaspoon = uomRepo.findByUnit("teaspoon").get();
        var clove = uomRepo.findByUnit("clove").get();
        var pound = uomRepo.findByUnit("pound").get();
        var anchoChili = new Ingredient("ancho chili powder", BigDecimal.valueOf(2), tablespoon);
        var driedOregano = new Ingredient("dried oregano", BigDecimal.ONE, teaspoon);
        var cumin = new Ingredient("dried cumin", BigDecimal.ONE, teaspoon);
        var sugar = new Ingredient("sugar", BigDecimal.ONE, teaspoon);
        var salt = new Ingredient("salt", BigDecimal.valueOf(0.5), teaspoon);
        var garlic = new Ingredient("garlic, finely chopped", BigDecimal.ONE, clove);
        var orange = new Ingredient("finely grated orange zest", BigDecimal.ONE, tablespoon);
        var olive = new Ingredient("olive oil", BigDecimal.valueOf(2), tablespoon);
        var chicken = new Ingredient("skinless, boneless chicken thighs", BigDecimal.valueOf(1, 25), pound);
        return Set.of(anchoChili, driedOregano, cumin, sugar, salt, garlic, orange, olive, chicken);
    }

    private Notes buildChickenNotes() {
        var notes = new Notes();
        notes.setRecipeNotes("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula." +
                " Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream." +
                " Serve with lime wedges.");
        return notes;
    }
}
