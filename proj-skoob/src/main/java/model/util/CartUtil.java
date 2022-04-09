package model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.bean.CartBean;

public class CartUtil {

	public static Integer getCartTotalCost(Collection<CartBean> carts) {
		int total = 0;
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				total += cart.getSubtotal();
			}
		}
		return total;
	}

	public static Integer getCartProductNum(Collection<CartBean> carts) {
		int num = 0;
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				num += 1;
			}
		}
		return num;
	}
	
	public static Integer getCartProductAllNum(Collection<CartBean> carts) {
		int num = 0;
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				num += cart.getNumber();
			}
		}
		return num;
	}

	public static List<Integer> getIntegerList(List<String> list) {
		if(list != null || !list.isEmpty()) {
			List<Integer> result = new ArrayList<Integer>();
			for (String str : list) {
				result.add(Integer.parseInt(str));
			}
			return result;
		}
		return null;
	}

}
