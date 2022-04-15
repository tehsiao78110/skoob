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

	String keyword1;
	String hql;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
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

	// 查詢產品
	public ProductBean select(String bean) {
		if (bean != null) {
			System.out.println("Productbean: " + bean);
			return this.getSession().get(ProductBean.class, bean);
		} else {
			return null;
		}
	}

	// 查詢產品name
	public ProductBean selectname(Integer productId) {
		if (productId != null) {
			Query<ProductBean> query = sessionFactory.getCurrentSession().createQuery(
					// "FROM ProductBean WHERE productId = :productId",ProductBean.class);
					"FROM ProductBean WHERE productid = :productId", ProductBean.class);
			query.setParameter("productId", productId);
			ProductBean pname = query.uniqueResult();
			System.out.println("ProductBean : " + pname);
			return pname;
		} else {
			return null;
		}
	}

	public List<ProductBean> selectpro(String keyword) {
		System.out.println("inside----selectpro");
		System.out.println("keyword = " + keyword);
		if (keyword.isEmpty() || keyword.length() == 0) {
			keyword1 = "1=1";
			hql = "FROM ProductBean where ?0 and ?1)";

		} else {
			keyword1 = "%" + keyword + "%";
			hql = "FROM ProductBean where (productname like?0 or classification like?1)";
		}
		// if(productId!=null) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, keyword1);
		query.setParameter(1, keyword1);
		List<ProductBean> pname = query.list();
		System.out.println("ProductBean : " + pname);
		for (ProductBean name : pname) {
			System.out.println("ProductBeanname : " + name);
		}
		return pname;
//		}else {
//			return null;
//		}		
	}

}
