package by.babroval.bike.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.babroval.bike.model.Bike;
import by.babroval.bike.model.Order;
import by.babroval.bike.model.Point;
import by.babroval.bike.service.BikeService;
import by.babroval.bike.service.OrderService;
import by.babroval.bike.service.PointService;
import by.babroval.bike.service.PriceService;
import by.babroval.bike.service.UserService;

@Controller
public class OrderController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	HttpSession session;

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	PointService pointService;

	@Autowired
	BikeService bikeService;

	@Autowired
	PriceService priceService;

	@RequestMapping(value = { "/listOrders" }, method = RequestMethod.GET)
	public String ordersTable(ModelMap model) {

		List<Order> allOrders = orderService.getAllOrders();
		model.addAttribute("allOrders", allOrders);

		return "ordersList";
	}

	// map
	@RequestMapping(value = { "/mapView" }, method = RequestMethod.GET)
	public String mapView(ModelMap model) {

		List<Point> allPoints = pointService.getAllPoints();
		model.addAttribute("allPoints", allPoints);

		return "map";
	}

	// new
	@RequestMapping(value = { "/order-{numPoint}" }, method = RequestMethod.GET)
	public String order(@PathVariable Integer numPoint, ModelMap model) {

		Point point = pointService.getPointByNum(numPoint);
		model.addAttribute("point", point);

		Integer vacant = point.getSlots() - point.getFreeBikes();
		if (vacant < 0) {
			vacant = 0;
		}
		List<String> vacantSlots = new ArrayList<String>();
		for (int i = 0; i < vacant; i++) {
			vacantSlots.add("VACANT SLOT");
		}
		model.addAttribute("vacantSlots", vacantSlots);

		List<Bike> bikes = bikeService.getBikesByPoint(numPoint);
		model.addAttribute("bikes", bikes);

		String login = (String) session.getAttribute("loggedUser");

		Order prossesingOrder = orderService.getProssesingOrderByLogin(login);
		if (prossesingOrder == null) {

			Order order = new Order();
			model.addAttribute("order", order);
			return "order";

		}

		model.addAttribute("order", prossesingOrder);
		return "order";
	}

	@RequestMapping(value = { "/order-{numPoint}" }, method = RequestMethod.POST)
	public String order(Order order, @PathVariable Integer numPoint, BindingResult result, ModelMap model) {

		if (order.getId() == null) {
			String login = (String) session.getAttribute("loggedUser");
			orderService.openOrder(order, numPoint, login);

			Order prossesingOrder = orderService.getProssesingOrderByLogin(login);

			model.addAttribute("success", "Your order \"" + prossesingOrder.getId() + "\" confirmed successfully.");

			return "orderSuccess";
		}

		orderService.closeOrder(order, numPoint);

		Order completeOrder = orderService.getOrderById(order.getId());

		model.addAttribute("success", "Your order \"" + completeOrder.getId() + "\" completed successfully."
				+ " Total cost is " + completeOrder.getTotalCost());

		return "orderSuccess";
	}

}