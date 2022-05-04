package model.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.OrderBean;
import model.bean.OrderitemBean;
import model.bean.ProductBean;

@Repository
public class OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public OrderBean select(String orderid) {
		if (orderid != null && orderid.length() != 0) {
			return this.getSession().get(OrderBean.class, orderid);
		}
		return null;
	}

	public void insertOrder(OrderBean order) {
		this.getSession().save(order);
	}

	public void insertOrderitem(OrderitemBean orderitem) {
		this.getSession().save(orderitem);
	}
	
	public OrderBean update(String id, Byte state) {
		if (id != null) {
			OrderBean bean = this.getSession().get(OrderBean.class, id);
			if (bean != null) {
				bean.setState(state);
				return bean;
			}
		}
		return null;
	}

	// 產生流水號
	public Integer selectSerialNumber(String ordertime) {
		return (Integer) this.getSession().createNativeQuery("SELECT _nextval ( ? )").setParameter(1, ordertime)
				.uniqueResult();
	}

}