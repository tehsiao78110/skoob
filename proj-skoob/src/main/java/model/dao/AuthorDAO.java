package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.AuthorBean;
import model.bean.MemberBean;

@Repository
public class AuthorDAO {

	@Autowired
	private SessionFactory sessionFactory;		
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//查詢作者
	public AuthorBean selectname(Integer authid) {
		if(authid!=null) {
			Query<AuthorBean> query = sessionFactory.getCurrentSession().createQuery(
					"FROM AuthorBean WHERE authorid = :authid",AuthorBean.class);	
			query.setParameter("authid", authid);
			AuthorBean author = query.uniqueResult();
			return author;
		}else {
			return null;
		}		
	}
}
