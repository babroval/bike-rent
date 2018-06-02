package by.babroval.bike.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.babroval.bike.dao.OrderDao;
import by.babroval.bike.model.Order;

@Repository
public class OrderDaoImpl extends AbstractDao implements OrderDao {

	@Override
	public Order loadOrderById(Integer id) {
		Order order = (Order) getSession().load(Order.class, id);
		if (order != null) {
			Hibernate.initialize(order);
		}
		return order;
	}

	@Override
	public Order loadProssesingOrderByUserId(Integer id) {

		Query query = getSession().createQuery("From Order WHERE status='using' AND user_id='" + id + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		if (results.isEmpty()) {
			return null;
		}

		return (Order) results.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> loadAllOrders() {

		Criteria criteria = getSession().createCriteria(Order.class);

		return criteria.list();
	}

	@Override
	public void storeOrder(Order order) {
		getSession().save(order);
	}

}