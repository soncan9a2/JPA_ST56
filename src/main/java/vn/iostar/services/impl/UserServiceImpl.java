package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.UserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.entity.User;
import vn.iostar.services.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(int userId) throws Exception {
		userDao.delete(userId);
	}

	@Override
	public User findById(int userId) {
		return userDao.findById(userId);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.findByUsernameAndPassword(username, password);
		if (user != null && user.isActive()) {
			return user;
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return userDao.count();
	}
}
