package vn.iostar.services;

import java.util.List;
import vn.iostar.entity.User;

public interface UserService {
	void insert(User user);
	void update(User user);
	void delete(int userId) throws Exception;
	User findById(int userId);
	User findByUsername(String username);
	User login(String username, String password);
	List<User> findAll();
	List<User> findAll(int page, int pagesize);
	int count();
}
