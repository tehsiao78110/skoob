package model.service;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Session;
import com.sun.xml.bind.v2.runtime.output.StAXExStreamWriterOutput;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.OrderBean;
import model.bean.OrderitemBean;
import model.bean.ProductBean;
import model.dao.OrderDAO;
import util.OrderUtil;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	public List<OrderBean> selectlist(MemberBean bean) {
		if (bean != null) {
			List<OrderBean> result = null;
			MemberBean id = orderDAO.selectmemberid(1);
			Set<OrderBean> set = id.getOrderlists();
			List<OrderBean> lists = new ArrayList<OrderBean>(set);
			Collections.sort(lists, new Comparator<OrderBean>() {
				@Override
				public int compare(OrderBean o1, OrderBean o2) {
					return o1.getOrderid().compareTo(o2.getOrderid());
				}
			});
			return lists;
		}
		return null;
	}

	public OrderBean update(OrderBean bean) {
		OrderBean result = null;
		if (bean != null && bean.getOrderid() != null) {
			result = orderDAO.update(bean.getOrderid(), bean.getState());
			System.out.println(result);
		}
		return result;
	}

	public String insertOrder(OrderBean order) {
		// 時間
		Date date = new Date();
		order.setOrdertime(date);

		// id
		String dateFormat = OrderUtil.getDateFormat(date);
		Integer srlnum = orderDAO.selectOrderSrlnum(dateFormat);
		String id = OrderUtil.getOrderid(dateFormat, srlnum);
		System.out.println("id = " + id);
		order.setOrderid(id);

		// 進行 insert
		Serializable isSuccess = orderDAO.insert(order);

		return id;
	}

	public void insertOrderitem(String orderid, List<CartBean> carts) {

		if (orderid != null && carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				OrderitemBean orderitem = new OrderitemBean();
				orderitem.setOrderid(orderid);
				orderitem.setProductId(cart.getProductid());
				orderitem.setNum(cart.getNumber());
				orderitem.setPrice(cart.getSubtotal());
				orderDAO.insertOrderitem(orderitem);
			}
		}

	}

	public List<CartBean> selectAllCart(Integer memberid) {
		List<CartBean> result = null;
		if (memberid != null && memberid != 0) {
			// 轉換 set 變成 List，並讓它有效排列
			result = new ArrayList<CartBean>(orderDAO.selectAll(memberid));
			Collections.sort(result, new Comparator<CartBean>() {
				@Override
				public int compare(CartBean c1, CartBean c2) {
					return c1.getProductid().compareTo(c2.getProductid());
				}
			});
		}
		return result;
	}

	public boolean deleteCart(List<CartBean> carts) {
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				orderDAO.deleteCart(cart);
			}
			return true;
		}
		return false;
	}
}
