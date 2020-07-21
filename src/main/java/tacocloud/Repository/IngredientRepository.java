package tacocloud.Repository;


import org.springframework.data.repository.CrudRepository;
import tacocloud.Data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}

