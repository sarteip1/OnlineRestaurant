package tacocloud.Repository;

import org.springframework.data.repository.CrudRepository;
import tacocloud.Data.Ingredient;
import tacocloud.Data.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
