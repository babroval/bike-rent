package by.babroval.bike.service;

import java.util.List;

import by.babroval.bike.model.Order;

public interface OrderService {

	Order getOrderById(Integer id);

	Order getProssesingOrderByLogin(String login);

	List<Order> getAllOrders();

	void openOrder(Order order, Integer numPoint, String login);

	void closeOrder(Order order, Integer numPoint);

}