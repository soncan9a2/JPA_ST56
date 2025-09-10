package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.CategoryDao;
import vn.iostar.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category category = enma.find(Category.class, cateid);
			if (category != null) {
				enma.remove(category);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int categoryId) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, categoryId);
		enma.close();
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c LEFT JOIN FETCH c.user";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		List<Category> list = query.getResultList();
		enma.close();
		return list;
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		List<Category> list = query.getResultList();
		enma.close();
		return list;
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.cateName like :catename";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename", "%" + catname + "%");
		List<Category> list = query.getResultList();
		enma.close();
		return list;
	}

	@Override
	public List<Category> findByUserId(int userId) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c LEFT JOIN FETCH c.user WHERE c.user.userId = :userId";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("userId", userId);
		List<Category> list = query.getResultList();
		enma.close();
		return list;
	}

	@Override
	public List<Category> findByUserIdAndCategoryname(int userId, String catname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.user.userId = :userId AND c.cateName like :catename";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("userId", userId);
		query.setParameter("catename", "%" + catname + "%");
		List<Category> list = query.getResultList();
		enma.close();
		return list;
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Category c";
		Query query = enma.createQuery(jpql);
		int count = ((Long) query.getSingleResult()).intValue();
		enma.close();
		return count;
	}
}
