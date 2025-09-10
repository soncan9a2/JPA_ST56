package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.Category;

public interface CategoryService {
	void insert(Category category);
	void update(Category category);
	void delete(int cateid) throws Exception;
	Category findById(int cateid);
	Category findByName(String catename);
	List<Category> findAll();
	List<Category> findByCategoryname(String catname);
	List<Category> findByUserId(int userId);
	List<Category> findByUserIdAndCategoryname(int userId, String catname);
	List<Category> findAll(int page, int pagesize);
	int count();
}
