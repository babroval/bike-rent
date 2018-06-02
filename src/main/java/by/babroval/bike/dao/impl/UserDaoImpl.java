package by.babroval.bike.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.babroval.bike.dao.UserDao;
import by.babroval.bike.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User loadUserById(Integer id) {
		User user = (User) getSession().load(User.class, id);
		if (user != null) {
			Hibernate.initialize(user);
		}
		return user;
	}

	@Override
	public User loadUserByLogin(String login) {

		Query query = getSession().createQuery("From User WHERE login='" + login + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		if (results.isEmpty()) {
			return null;
		}
		return (User) results.get(0);
	}

	@Override
	public User loadAuthorizeUser(String login, String password) {

		Query query = getSession().createQuery("From User WHERE login='" + login + "' AND password='" + password + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		if (results.isEmpty()) {
			return null;
		}

		return (User) results.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadAllUsers() {

		Criteria criteria = getSession().createCriteria(User.class);

		return criteria.list();
	}

	@Override
	public User storeUser(User user) {

		getSession().save(user);

		return null;
	}

	@Override
	public void deleteUserByLogin(String login) {

		User user = loadUserByLogin(login);

		deleteUser(user);
	}

	public void deleteUser(User user) {
		getSession().delete(user);
	}

	@Override
	public boolean isLoginUnique(String login) {

		Query query = getSession().createQuery("From User WHERE login='" + login + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		return results.isEmpty();
	}

}