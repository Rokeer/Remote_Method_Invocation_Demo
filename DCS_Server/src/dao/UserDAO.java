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

	// ����û�
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

	// �����û�
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

	// ����user id��ȡ�û�����
	public User getUser(int userid) {
		session = HibernateSessionFactory.getSession();
		User user = (User) session.get(User.class, userid);
		//HibernateSessionFactory.closeSession();
		return user;
	}

	// ����Email��nickname��ȡ�û�����
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

	// ���ĳ�ֶ��Ƿ����ĳֵ��һ���������email��nickname�Ƿ��ظ�
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
