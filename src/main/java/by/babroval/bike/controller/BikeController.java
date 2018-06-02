package by.babroval.bike.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.babroval.bike.model.Bike;
import by.babroval.bike.model.Point;
import by.babroval.bike.model.Price;
import by.babroval.bike.service.BikeService;
import by.babroval.bike.service.PointService;
import by.babroval.bike.service.PriceService;

@Controller
public class BikeController {

	@Resource
	Map<String, String> bikesImages;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PointService pointService;

	@Autowired
	PriceService priceService;

	@Autowired
	BikeService bikeService;

	@RequestMapping(value = { "/listBikes" }, method = RequestMethod.GET)
	public String bikesTable(ModelMap model) {

		List<Bike> allBikes = bikeService.getAllBikes();
		model.addAttribute("allBikes", allBikes);

		return "bikesList";
	}

	// new
	@RequestMapping(value = { "/newBike" }, method = RequestMethod.GET)
	public String newBike(ModelMap model) {

		Bike bike = new Bike();
		model.addAttribute("bike", bike);
		model.addAttribute("edit", false);
		model.addAttribute("bikesImages", bikesImages);

		List<Point> allPoints = pointService.getAllPoints();
		model.addAttribute("allPoints", allPoints);

		List<Price> allPrices = priceService.getAllPrices();
		model.addAttribute("allPrices", allPrices);

		return "bikeRegistration";
	}

	@RequestMapping(value = { "/newBike" }, method = RequestMethod.POST)
	public String saveBike(@Valid Bike bike, BindingResult result, ModelMap model) {

		model.addAttribute("bikesImages", bikesImages);

		List<Point> allPoints = pointService.getAllPoints();
		model.addAttribute("allPoints", allPoints);

		List<Price> allPrices = priceService.getAllPrices();
		model.addAttribute("allPrices", allPrices);

		if (result.hasErrors()) {
			return "bikeRegistration";
		}

		if (!bikeService.isVinUnique(bike.getId(), bike.getVin())) {
			FieldError numError = new FieldError("bike", "vin",
					messageSource.getMessage("non.unique.vin", new String[] { bike.getVin() }, Locale.getDefault()));
			result.addError(numError);
			return "bikeRegistration";
		}

		Point point = pointService.getPointById(bike.getPoint().getId());

		if (pointService.isPointFull(point)) {
			FieldError pointError = new FieldError("bike", "point.id",
					messageSource.getMessage("full.point", new Integer[] { point.getNumPoint() }, Locale.getDefault()));
			result.addError(pointError);
			return "bikeRegistration";
		}

		bikeService.storeBike(bike);

		model.addAttribute("success", "Bike number \"" + bike.getVin() + "\" registered successfully");
		return "bikeSuccess";
	}

	// update
	@RequestMapping(value = { "/updateBike-{vin}-bike" }, method = RequestMethod.GET)
	public String editBike(@PathVariable String vin, ModelMap model) {

		Bike bike = bikeService.getBikeByVin(vin);
		model.addAttribute("bike", bike);
		model.addAttribute("edit", true);
		model.addAttribute("bikesImages", bikesImages);

		List<Price> allPrices = priceService.getAllPrices();
		model.addAttribute("allPrices", allPrices);

		return "bikeRegistration";
	}

	@RequestMapping(value = { "/updateBike-{vin}-bike" }, method = RequestMethod.POST)
	public String updateBike(@Valid Bike bike, BindingResult result, ModelMap model) {

		model.addAttribute("edit", true);
		model.addAttribute("bikesImages", bikesImages);

		List<Price> allPrices = priceService.getAllPrices();
		model.addAttribute("allPrices", allPrices);

		if (result.hasErrors()) {
			return "bikeRegistration";
		}

		if (!bikeService.isVinUnique(bike.getId(), bike.getVin())) {
			FieldError numError = new FieldError("bike", "vin",
					messageSource.getMessage("non.unique.vin", new String[] { bike.getVin() }, Locale.getDefault()));
			result.addError(numError);
			return "bikeRegistration";
		}

		bikeService.updateBike(bike);

		model.addAttribute("success", "Bike number \"" + bike.getVin() + "\" updated successfully");
		return "bikeSuccess";
	}

	// delete
	@RequestMapping(value = { "/deleteBike-{vin}-bike" }, method = RequestMethod.GET)
	public String deleteBike(@PathVariable String vin) {

		bikeService.deleteBikeByVine(vin);
		return "redirect:/listBikes";
	}
}