package tacocloud.Repository;

import tacocloud.Data.Order;

public interface OrderRepository {
    Order save(Order order);
}
