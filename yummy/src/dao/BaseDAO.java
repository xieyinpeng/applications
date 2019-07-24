package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import utils.HibernateUtil;

public class BaseDAO<T> {
	
	public void save(T object) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(object);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public List<T> query(String hql,String[] params){
		Session session=HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		List<T> list = null;
		try {
			String hql_prefix="from "+getClass().getSimpleName().replace("DAO", "").toLowerCase()+" ";
			Query query=session.createQuery(hql_prefix+hql);
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					query.setParameter(i+1,params[i]);
				}
			}
			list=query.getResultList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		return list;
	}
	
	public List<T> query(String hql){
		return query(hql,null);
	}
	
	public void delete(String hql) {
		Session session=HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql_prefix="delete from "+getClass().getSimpleName().replace("DAO", "").toLowerCase()+" ";
			Query query=session.createQuery(hql_prefix+hql);
			query.executeUpdate();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}	
	}

}
