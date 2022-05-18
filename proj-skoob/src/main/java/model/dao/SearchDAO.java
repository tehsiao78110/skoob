package model.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		
		if (keyword != null) {
			Query<ProductBean> query = sessionFactory.getCurrentSession()
					.createQuery("FROM ProductBean WHERE productname LIKE :keyword", ProductBean.class);
			query.setParameter("keyword", '%' + keyword + '%');
			List<ProductBean> product = query.list();

			return product;
		} else {
			return null;
		}

	}

	public List<ProductBean> getQqueryResult(String keyword) {
		// FROM ProductBean
		// WHERE productname LIKE :keyword
		
		// 進行「字串拼接」，關鍵字前後加上"%"	
		StringBuffer like = new StringBuffer(keyword);
		like.insert(0, "%");
		like.append("%");
		
		// 建立 CriteriaQuery
		CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<ProductBean> CriteriaQuery = builder.createQuery(ProductBean.class);

		Root<ProductBean> root = CriteriaQuery.from(ProductBean.class);

		CriteriaQuery.where(builder.like(root.get("productname"), like.toString()));

		TypedQuery<ProductBean> typedQuery = sessionFactory.getCurrentSession().createQuery(CriteriaQuery);
//		typedQuery.setFirstResult(0);
//		typedQuery.setMaxResults(5);
		List<ProductBean> result = typedQuery.getResultList();
		
		return result;
	}

}
