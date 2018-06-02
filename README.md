![Alt text](usage.png)

Bike Rent
=========
 Java web dynamic project for finance managing of city automatic bicycle rent. 

[![Build Status](https://travis-ci.org/babroval/bike-rent.svg?branch=master)](https://travis-ci.org/babroval/bike-rent)
[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/babroval/bike-rent/blob/master/LICENSE)
```
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
	public String order(Order order, @PathVariable Integer numPoint,
						BindingResult result, ModelMap model) {
		if (order.getId() == null) {
			String login = (String) session.getAttribute("loggedUser");
			orderService.openOrder(order, numPoint, login);
			Order prossesingOrder = orderService.getProssesingOrderByLogin(login);
			model.addAttribute("success", "Your order \"" 
							  + prossesingOrder.getId() + "\" confirmed successfully.");
			return "orderSuccess";
		}
		orderService.closeOrder(order, numPoint);
		Order completeOrder = orderService.getOrderById(order.getId());
		model.addAttribute("success", "Your order \"" 
						  + completeOrder.getId() + "\" completed successfully."
						  + " Total cost is " + completeOrder.getTotalCost());
		return "orderSuccess";
	}
```

Table of Contents
-----------------
  * [Requirements](#requirements)
  * [Usage](#usage)
  * [Contributing](#contributing)
  * [License](#license)  


Requirements
------------
Bike Rent requires the following to run:
  * [JRE][jre] 8
  * [Apache Tomcat Server][tomcat] 
  * [MySQL Community Server][mysql]  


Usage
-----
Bike Rent is easiest to use with [Eclipse IDE][eclipse]:  
File -> Import -> Git -> Projects From Git > URI


Contributing
------------
To contribute to Bike Rent, clone this repo locally and  
commit your code on a separate branch.
<br/>
<br/>

License
-------
Bike Rent is licensed under the [MIT][mit] license.  

[jre]: http://www.oracle.com/technetwork/java/javase/downloads/
[tomcat]: https://tomcat.apache.org/download-90.cgi
[mysql]: https://dev.mysql.com/downloads/mysql/
[eclipse]: https://www.eclipse.org/downloads/
[mit]: https://github.com/babroval/bike-rent/blob/master/LICENSE/