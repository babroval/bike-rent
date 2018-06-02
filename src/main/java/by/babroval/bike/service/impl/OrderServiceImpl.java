package by.babroval.bike.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.babroval.bike.dao.BikeDao;
import by.babroval.bike.dao.OrderDao;
import by.babroval.bike.dao.PointDao;
import by.babroval.bike.dao.UserDao;
import by.babroval.bike.model.Bike;
import by.babroval.bike.model.Order;
import by.babroval.bike.model.Point;
import by.babroval.bike.model.Price;
import by.babroval.bike.model.User;
import by.babroval.bike.service.OrderService;
import by.babroval.bike.utils.CurrencyUtils;
import by.babroval.bike.utils.DateUtils;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private BikeDao bikeDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PointDao pointDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Order getOrderById(Integer id) {
		return orderDao.loadOrderById(id);
	}

	@Override
	public Order getProssesingOrderByLogin(String login) {

		User user = userDao.loadUserByLogin(login);

		return orderDao.loadProssesingOrderByUserId(user.getId());
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.loadAllOrders();
	}

	@Override
	public void openOrder(Order order, Integer numPoint, String login) {

		User user = userDao.loadUserByLogin(login);
		Point point = pointDao.loadPointByNum(numPoint);
		Bike bike = bikeDao.loadBikeByVin(order.getBike().getVin());

		order.setUser(user);
		order.setBike(bike);
		order.setStartPoint(point);
		order.setStartTime(DateUtils.getCurrentDateTime());
		order.setStatus("using");

		orderDao.storeOrder(order);

		point.setFreeBikes(point.getFreeBikes() - 1);
		bike.setPoint(null);
	}

	@Override
	public void closeOrder(Order order, Integer numPoint) {

		Order entity = getOrderById(order.getId());
		Point point = pointDao.loadPointByNum(numPoint);

		if (entity != null && point != null) {
			entity.setFinishPoint(point);
			entity.setFinishTime(DateUtils.getCurrentDateTime());

			Bike bike = bikeDao.loadBikeByVin(entity.getBike().getVin());
			bike.setPoint(point);

			Long time = entity.getFinishTime().getTime() - entity.getStartTime().getTime();

			BigDecimal totalTime = new BigDecimal(time.toString());
			BigDecimal convertTimeValue = new BigDecimal(DateUtils.CONVERT_TIME_MINUTE_VALUE.toString());
			BigDecimal totalCost = totalTime.divide(convertTimeValue, 10, BigDecimal.ROUND_HALF_UP);

			Price price = bike.getPrice();
			totalCost = totalCost.multiply(price.getTarif());

			totalCost = CurrencyUtils.roundNumber(totalCost, true);
			entity.setTotalCost(totalCost);

			entity.setStatus("complete");

			point.setFreeBikes(point.getFreeBikes() + 1);
		}
	}

}
