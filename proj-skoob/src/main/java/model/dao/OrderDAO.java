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

	public OrderBean update(String id, String state) {
		if (id != null) {
			OrderBean bean = this.getSession().get(OrderBean.class, id);
			if (bean != null) {
				bean.setState(state);
				return bean;
			}
		}
		return null;
	}

	public MemberBean selectmemberid(Integer id) {
		return this.getSession().get(MemberBean.class, id);
	}

	public Integer selectOrderSrlnum(String dateFormat) {
		return (Integer) this.getSession().createNativeQuery("SELECT _nextval ( ? )").setParameter(1, dateFormat)
				.uniqueResult();
	}

	public Serializable insert(OrderBean order) {
		return this.getSession().save(order);
	}

	public Serializable insertOrderitem(OrderitemBean orderitem) {
		return this.getSession().save(orderitem);
	}
	
	public Set<CartBean> selectAll(Integer membeid) {
		MemberBean isExist = this.getSession().get(MemberBean.class, membeid);
		if (isExist != null) {
			return isExist.getCarts();
		}
		return null;
	}

	public void deleteCart(CartBean cart) {
		this.getSession().delete(cart);
	}
	
}