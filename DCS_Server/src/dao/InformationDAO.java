package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;
import entity.Information;

public class InformationDAO {
	private Session session;
	private Transaction transaction;
	private Query query;

	// ����û�
	public void regInfo(Information information) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(information);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
	}

	// �����û�
	public void editUser(Information information) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(information);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
	}

	// ����user id��ȡ�û�����
	public Information getInformation(int informationid) {
		session = HibernateSessionFactory.getSession();
		Information information = (Information) session.get(Information.class, informationid);
		HibernateSessionFactory.closeSession();
		return information;
	}

	// ����Email��nickname��ȡ�û�����
	public Information getInformationByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select informationid from Information as information where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int informationId = Integer.parseInt(query.uniqueResult().toString());
			Information information = (Information) session.get(Information.class, informationId);
			HibernateSessionFactory.closeSession();
			return information;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	// ���ĳ�ֶ��Ƿ����ĳֵ��һ���������email��nickname�Ƿ��ظ�
	public boolean checkHave(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select " + key + " from Information as information where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			String tmp = (String) query.uniqueResult();
			if (tmp.equals("")) {
				HibernateSessionFactory.closeSession();
				return false;
			} else {
				HibernateSessionFactory.closeSession();
				return true;
			}
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return false;
		}

	}
}
