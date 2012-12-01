package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;
import entity.User;

public class UserDAO {
	private Session session;
	private Transaction transaction;
	private Query query;

	// 添加用户
	public void regUser(User user) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
	}

	// 更新用户
	public void editUser(User user) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
	}

	// 根据user id获取用户对象
	public User getUser(int userid) {
		session = HibernateSessionFactory.getSession();
		User user = (User) session.get(User.class, userid);
		//HibernateSessionFactory.closeSession();
		return user;
	}

	// 根据Email或nickname获取用户对象
	public User getUserByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select userid from User as user where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int userId = Integer.parseInt(query.uniqueResult().toString());
			User user = (User) session.get(User.class, userId);
			//HibernateSessionFactory.closeSession();
			return user;
		} catch (Exception e) {
			System.out.println(e.toString());
			//HibernateSessionFactory.closeSession();
			return null;
		}
	}

	// 检查某字段是否存在某值，一般用来检查email或nickname是否重复
	public boolean checkHave(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select " + key + " from User as user where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			String tmp = (String) query.uniqueResult();
			if (tmp.equals("")) {
				//HibernateSessionFactory.closeSession();
				return false;
			} else {
				//HibernateSessionFactory.closeSession();
				return true;
			}
		} catch (Exception e) {
			//HibernateSessionFactory.closeSession();
			return false;
		}

	}
}
