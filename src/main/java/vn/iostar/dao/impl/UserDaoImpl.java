package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.UserDao;
import vn.iostar.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
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
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
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
	public void delete(int userId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, userId);
			if (user != null) {
				enma.remove(user);
			} else {
				throw new Exception("Không tìm thấy user");
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
	public User findById(int userId) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			return enma.find(User.class, userId);
		} finally {
			enma.close();
		}
	}

	@Override
	public User findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = enma.createNamedQuery("User.findByUsername", User.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = enma.createNamedQuery("User.findByUsernameAndPassword", User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
			query.setFirstResult(page * pagesize);
			query.setMaxResults(pagesize);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT count(u) FROM User u";
			Query query = enma.createQuery(jpql);
			return ((Long) query.getSingleResult()).intValue();
		} finally {
			enma.close();
		}
	}
}
