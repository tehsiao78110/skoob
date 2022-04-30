package model.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.OrderBean;
import model.bean.OrderitemBean;
import model.dao.OrderDAO;
import util.OrderUtil;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	public List<OrderBean> selectOrderList(MemberBean bean) {
		if (bean != null) {
			List<OrderBean> result = null;
			MemberBean id = orderDAO.selectmemberid(1);
			Set<OrderBean> set = id.getOrderlists();
			List<OrderBean> lists = new ArrayList<OrderBean>(set);
			Collections.sort(lists, new Comparator<OrderBean>() {
				@Override
				public int compare(OrderBean o1, OrderBean o2) {
					return -o1.getOrdertime().compareTo(o2.getOrdertime());
				}
			});
			return lists;
		}
		return null;
	}

	public OrderBean selectOrder(MemberBean memeber, String orderid) {
		OrderBean order = null;
		if (orderid != null && orderid.length() != 0) {
			order = orderDAO.select(orderid);
			// 比對訂單的編號，會員只能查詢「自己的訂單」
			if (order.getMemberid() != memeber.getMemberid()) {
				order = null;
			}
		}
		return order;
	}

	public Set<OrderitemBean> getOrderItem(OrderBean order) {
		Set<OrderitemBean> orderitem = null;
		if (order != null) {
			orderitem = order.getOrderitems();
		}
		return orderitem;
	}

	public OrderBean update(OrderBean bean) {
		OrderBean result = null;
		if (bean != null && bean.getOrderid() != null) {
			result = orderDAO.update(bean.getOrderid(), bean.getState());
			System.out.println(result);
		}
		return result;
	}

	// 建立訂單時，才去生成 sequence
	public String insertOrder(OrderBean order) {
		// 設定「下訂時間」
		Date date = new Date();
		order.setOrdertime(date);

		// sequence id = 下定時間(只含日期) + 流水號碼
		// -------------------------------------------------
		// 取得下定時間的日期
		String orderdate = OrderUtil.getDateFormat(date);
		// 產生流水號碼
		Integer srlnum = orderDAO.selectSerialNumber(orderdate);
		// 組合成 sequence
		String sequence = OrderUtil.getOrderid(orderdate, srlnum);
		System.out.println("id = " + sequence);
		// 設定「訂單 id」
		order.setOrderid(sequence);

		// 進行 insert
		Serializable isSuccess = orderDAO.insert(order);

		return sequence;
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
