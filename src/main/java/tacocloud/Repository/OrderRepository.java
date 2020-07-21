package tacocloud.Repository;

import org.springframework.data.repository.CrudRepository;
import tacocloud.Data.Ingredient;
import tacocloud.Data.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
