package by.babroval.bike.controller;

import java.util.List;
import java.util.Locale;

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

import by.babroval.bike.model.Point;
import by.babroval.bike.service.PointService;

@Controller
public class PointController {

	@Autowired
	PointService pointService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/listPoints" }, method = RequestMethod.GET)
	public String pointTable(ModelMap model) {

		List<Point> allPoints = pointService.getAllPoints();
		model.addAttribute("allPoints", allPoints);

		return "pointsList";
	}

	// new
	@RequestMapping(value = { "/newPoint" }, method = RequestMethod.GET)
	public String newPoint(ModelMap model) {

		Point point = new Point();
		model.addAttribute("point", point);
		model.addAttribute("edit", false);

		return "pointRegistration";
	}

	@RequestMapping(value = { "/newPoint" }, method = RequestMethod.POST)
	public String savePoint(@Valid Point point, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "pointRegistration";
		}

		if (!pointService.isPointNumberUnique(point.getId(), point.getNumPoint())) {
			FieldError numError = new FieldError("point", "numPoint", messageSource.getMessage("non.unique.numPoint",
					new Integer[] { point.getNumPoint() }, Locale.getDefault()));
			result.addError(numError);
			return "pointRegistration";
		}

		pointService.storePoint(point);

		model.addAttribute("success", "Point number \"" + point.getNumPoint() + "\" registered successfully");
		return "pointSuccess";
	}

	// update
	@RequestMapping(value = { "/updatePoint-{numPoint}-point" }, method = RequestMethod.GET)
	public String editPoint(@PathVariable Integer numPoint, ModelMap model) {

		Point point = pointService.getPointByNum(numPoint);
		model.addAttribute("point", point);
		model.addAttribute("edit", true);

		return "pointRegistration";
	}

	@RequestMapping(value = { "/updatePoint-{numPoint}-point" }, method = RequestMethod.POST)
	public String updatePoint(@Valid Point point, BindingResult result, ModelMap model) {

		model.addAttribute("edit", true);

		if (result.hasErrors()) {
			return "pointRegistration";
		}

		if (!pointService.isPointNumberUnique(point.getId(), point.getNumPoint())) {
			FieldError pointError = new FieldError("point", "numPoint", messageSource.getMessage("non.unique.numPoint",
					new Integer[] { point.getNumPoint() }, Locale.getDefault()));
			result.addError(pointError);
			return "pointRegistration";
		}

		pointService.updatePoint(point);

		model.addAttribute("success", "Point number \"" + point.getNumPoint() + "\" updated successfully");
		return "pointSuccess";
	}

	// delete
	@RequestMapping(value = { "/deletePoint-{numPoint}-point" }, method = RequestMethod.GET)
	public String deletePoint(@PathVariable Integer numPoint) {

		pointService.deletePointByNumPoint(numPoint);
		return "redirect:/listPoints";
	}

}
