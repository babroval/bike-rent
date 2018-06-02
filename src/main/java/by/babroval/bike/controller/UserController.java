package by.babroval.bike.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
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

import by.babroval.bike.model.Country;
import by.babroval.bike.model.User;
import by.babroval.bike.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/listUsers" }, method = RequestMethod.GET)
	public String userTable(ModelMap model) {

		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("allUsers", allUsers);

		return "usersList";
	}

	// new
	@RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {

		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);

		List<Country> allCountries = Arrays.asList(Country.values());
		model.addAttribute("allCountries", allCountries);

		return "userRegistration";
	}

	@RequestMapping(value = { "/newUser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		List<Country> allCountries = Arrays.asList(Country.values());
		model.addAttribute("allCountries", allCountries);

		if (result.hasErrors()) {
			return "userRegistration";
		}

		if (!user.getPassword().equals(user.getRetypePassword())) {
			FieldError retPassError = new FieldError("user", "retypePassword", messageSource
					.getMessage("non.retype.password", new String[] { user.getRetypePassword() }, Locale.getDefault()));
			result.addError(retPassError);
			return "userRegistration";
		}

		if (!userService.isLoginUnique(user.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "userRegistration";
		}

		userService.storeUser(user);

		model.addAttribute("success", "user \"" + user.getLogin() + "\" registered successfully, sign-in please");
		return "userSuccess";
	}

	// update
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.GET)
	public String updateUser(ModelMap model) {

		User user = userService.getUserByLogin((String) session.getAttribute("loggedUser"));
		model.addAttribute("user", user);
		model.addAttribute("edit", true);

		List<Country> allCountries = Arrays.asList(Country.values());
		model.addAttribute("allCountries", allCountries);

		return "userRegistration";
	}

	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model) {

		model.addAttribute("edit", true);

		List<Country> allCountries = Arrays.asList(Country.values());
		model.addAttribute("allCountries", allCountries);

		if (result.hasErrors()) {
			return "userRegistration";
		}

		if (!user.getPassword().equals(user.getRetypePassword())) {
			FieldError retPassError = new FieldError("user", "retypePassword", messageSource
					.getMessage("non.retype.password", new String[] { user.getRetypePassword() }, Locale.getDefault()));
			result.addError(retPassError);
			return "userRegistration";
		}

		userService.updateUser(user);

		model.addAttribute("success", "user \"" + user.getLogin() + "\" updated successfully");
		return "userSuccess";
	}

	// sign-in
	@RequestMapping(value = { "/signInUser" }, method = RequestMethod.GET)
	public String signInUser(ModelMap model) {

		User user = new User();
		model.addAttribute("user", user);

		return "userSignIn";
	}

	@RequestMapping(value = { "/signInUser" }, method = RequestMethod.POST)
	public String findUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "userSignIn";
		}

		User logged = userService.getAuthorizeUser(user.getLogin(), user.getPassword());
		if (logged == null) {
			FieldError authorizeError = new FieldError("user", "password",
					messageSource.getMessage("non.authorize.user", null, Locale.getDefault()));
			result.addError(authorizeError);

			return "userSignIn";
		}

		session.setAttribute("loggedUser", logged.getLogin());
		session.setAttribute("role", logged.getRole());

		model.addAttribute("success", "Welcome " + logged.getFirstName());
		return "userSuccess";
	}

	// sign-out
	@RequestMapping(value = { "/signOutUser" }, method = RequestMethod.GET)
	public String signOutUser(ModelMap model) {

		session.removeAttribute("loggedUser");
		session.removeAttribute("role");

		model.addAttribute("success", "you have just logged out");
		return "userSuccess";
	}

	// delete
	@RequestMapping(value = { "/deleteUser-{login}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String login) {

		userService.deleteUserByLogin(login);
		return "redirect:/listUsers";
	}
	
}