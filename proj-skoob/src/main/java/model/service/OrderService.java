package model.service;

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
import model.dao.CartDAO;
import model.dao.MemberDAO;
import model.dao.OrderDAO;
import util.OrderUtil;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private MemberDAO memberDAO;

	public void CreateOrder(OrderBean order) {
		// 1. 生成 sequence(orderid)

		// 設定「下訂時間」
		Date date = new Date();
		order.setOrdertime(date);

		// sequence(orderid) = 下定時間(只含日期) + 流水號碼
		// -------------------------------------------------
		// 取得下定時間的日期
		String orderdate = OrderUtil.getDateFormat(date);
		// 產生流水號碼
		Integer srlnum = orderDAO.selectSerialNumber(orderdate);
		// 組合成 sequence
		String sequence = OrderUtil.getOrderid(orderdate, srlnum);

		// 2. 建立訂單
		order.setOrderid(sequence);
		orderDAO.insertOrder(order);

		// 3. 將購物車的資料放入訂單項目
		Set<CartBean> carts = cartDAO.selectAll(order.getMemberid());
		ArrayList<Integer> prodIdList = new ArrayList();
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				OrderitemBean orderitem = new OrderitemBean();
				orderitem.setOrderid(sequence);
				orderitem.setProductId(cart.getProductid());
				orderitem.setNum(cart.getNumber());
				orderitem.setPrice(cart.getSubtotal());
				orderDAO.insertOrderitem(orderitem);
				// 將 productid 加入集合 prodIdList
				prodIdList.add(cart.getProductid());
				// 更新商品的資料
			}
		}
		// 4. 購物車清空
		cartDAO.deleteMulti(order.getMemberid(), prodIdList);
	}

	public List<OrderBean> selectOrderList(MemberBean member) {
		if (member != null) {
			List<OrderBean> result = null;
			MemberBean id = memberDAO.selectMemberId(member.getMemberid());
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

	public OrderBean getOrder(MemberBean memeber, String orderid) {
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

}
