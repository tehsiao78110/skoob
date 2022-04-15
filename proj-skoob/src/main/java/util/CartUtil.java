package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.bean.CartBean;
import model.dto.CartDTO;

public class CartUtil {

	// 工具方法
	public static List<Integer> toIntegerList(List<String> list) {
		if (list != null || !list.isEmpty()) {
			List<Integer> result = new ArrayList<Integer>();
			for (String str : list) {
				result.add(Integer.parseInt(str));
			}
			return result;
		}
		return null;
	}

	public static CartDTO toCartDto(List<CartBean> carts) {
		if (carts != null && !carts.isEmpty()) {
			Integer totalCost = CartUtil.getCartTotalCost(carts);
			Integer productNum = CartUtil.getCartProductNum(carts);
			Integer productAllNum = CartUtil.getCartProductAllNum(carts);
			return new CartDTO(totalCost, productNum, productAllNum);
		} else {
			return null;
		}

	}
	
	// 內部的『私有方法』
	private static Integer getCartTotalCost(Collection<CartBean> carts) {
		int total = 0;
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				total += cart.getSubtotal();
			}
		}
		return total;
	}

	private static Integer getCartProductNum(Collection<CartBean> carts) {
		int num = 0;
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				num += 1;
			}
		}
		return num;
	}

	private static Integer getCartProductAllNum(Collection<CartBean> carts) {
		int num = 0;
		if (carts != null && !carts.isEmpty()) {
			for (CartBean cart : carts) {
				num += cart.getNumber();
			}
		}
		return num;
	}

}
