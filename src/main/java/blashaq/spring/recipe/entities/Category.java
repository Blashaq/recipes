package blashaq.spring.recipe.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Ingredient> ingredients;
}
