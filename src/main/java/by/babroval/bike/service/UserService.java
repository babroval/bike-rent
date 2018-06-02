package by.babroval.bike.service;

import java.util.List;

import by.babroval.bike.model.User;

public interface UserService {
	
	User getUserByLogin(String login);
	
	User getAuthorizeUser(String login, String password);
	
	List<User> getAllUsers();

	void storeUser(User user);

	void updateUser(User user);

	void deleteUserByLogin(String login);

	boolean isLoginUnique(String login);

}