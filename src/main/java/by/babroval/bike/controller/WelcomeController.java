package by.babroval.bike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = { "/news" }, method = RequestMethod.GET)
	public String news() {
		return "newsPage";
	}

}
