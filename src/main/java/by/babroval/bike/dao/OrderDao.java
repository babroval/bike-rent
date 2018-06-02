package by.babroval.bike.dao;

import java.util.List;

import by.babroval.bike.model.Order;

public interface OrderDao {

	Order loadOrderById(Integer id);

	Order loadProssesingOrderByUserId(Integer id);

	void storeOrder(Order order);

	List<Order> loadAllOrders();

}