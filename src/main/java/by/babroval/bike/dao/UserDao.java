package by.babroval.bike.dao;

import java.util.List;

import by.babroval.bike.model.User;

public interface UserDao {

	User loadUserById(Integer id);

	User loadUserByLogin(String login);

	User loadAuthorizeUser(String login, String password);

	List<User> loadAllUsers();

	User storeUser(User user);

	void deleteUserByLogin(String login);

	boolean isLoginUnique(String login);

}