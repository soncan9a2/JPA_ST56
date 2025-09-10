package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.CategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.entity.Category;
import vn.iostar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(int cateid) throws Exception {
		categoryDao.delete(cateid);
	}

	@Override
	public Category findById(int cateid) {
		return categoryDao.findById(cateid);
	}

	@Override
	public Category findByName(String catename) {
		List<Category> categories = categoryDao.findByCategoryname(catename);
		if (categories != null && !categories.isEmpty()) {
			return categories.get(0);
		}
		return null;
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		return categoryDao.findByCategoryname(catname);
	}

	@Override
	public List<Category> findByUserId(int userId) {
		return categoryDao.findByUserId(userId);
	}

	@Override
	public List<Category> findByUserIdAndCategoryname(int userId, String catname) {
		return categoryDao.findByUserIdAndCategoryname(userId, catname);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return categoryDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return categoryDao.count();
	}
}
