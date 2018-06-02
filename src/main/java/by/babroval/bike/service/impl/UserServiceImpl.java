package by.babroval.bike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.babroval.bike.dao.UserDao;
import by.babroval.bike.model.User;
import by.babroval.bike.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByLogin(String login) {
		return userDao.loadUserByLogin(login);
	}

	@Override
	public User getAuthorizeUser(String login, String password) {
		return userDao.loadAuthorizeUser(login, password);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.loadAllUsers();
	}

	@Override
	public void storeUser(User user) {
		userDao.storeUser(user);
	}

	@Override
	public void updateUser(User user) {

		User entity = userDao.loadUserById(user.getId());

		if (entity != null) {

			entity.setLogin(user.getLogin());
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
			entity.setRetypePassword(user.getRetypePassword());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setTel(user.getTel());
			entity.setCountry(user.getCountry());
			entity.setGender(user.getGender());
			entity.setBirth(user.getBirth());
			entity.setRole(user.getRole());
		}
	}

	@Override
	public void deleteUserByLogin(String login) {
		userDao.deleteUserByLogin(login);
	}

	@Override
	public boolean isLoginUnique(String login) {
		User user = getUserByLogin(login);
		return (user == null);
	}

}
