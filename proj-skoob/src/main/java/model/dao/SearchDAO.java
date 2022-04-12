package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.ProductBean;

@Repository
public class SearchDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public List<ProductBean> search(String keyword) {
		if(keyword!=null) {
			Query<ProductBean> query = sessionFactory.getCurrentSession().createQuery(
					"FROM ProductBean WHERE productname LIKE :keyword",ProductBean.class);	
			query.setParameter("keyword",'%'+ keyword+'%');
			List<ProductBean> product= query.list();
			
			return product;
		}else {
			return null;
		}
				
	}
	
}
