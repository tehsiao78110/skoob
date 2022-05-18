package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.ProductBean;

@Repository
public class ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ProductBean select(Integer productid) {
		if (productid != null) {
			ProductBean product = this.getSession()
					.createQuery("FROM ProductBean WHERE productid = :productId", ProductBean.class)
					.setParameter("productId", productid).uniqueResult();
			return product;
		} else {
			return null;
		}
	}

	public ProductBean insert(ProductBean bean) {
		if (bean != null) {
			ProductBean temp = this.getSession().get(ProductBean.class, bean.getProductid());
			if (temp == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	public ProductBean update(ProductBean bean) {
		if(bean != null && bean.getProductid() != null) {
			ProductBean isExist = this.getSession().get(ProductBean.class, bean.getProductid());
			if(isExist != null) {
				return (ProductBean) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
}
