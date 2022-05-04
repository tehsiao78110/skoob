package model.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.MyFavBean;
import model.bean.ProductBean;
import model.dao.CartDAO;
import model.dao.MyfavDAO;
import model.dto.CartDTO;
import util.CartUtil;

@Service
@Transactional
public class CartService {
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private MyfavDAO myfavDAO;

	public List<CartBean> selectAll(Integer memberid) {
		List<CartBean> result = null;
		if (memberid != null && memberid != 0) {
			// 轉換 set 變成 List，並讓它有效排列
			result = new ArrayList<CartBean>(cartDAO.selectAll(memberid));
			Collections.sort(result, new Comparator<CartBean>() {
				@Override
				public int compare(CartBean c1, CartBean c2) {
					return c1.getProductid().compareTo(c2.getProductid());
				}
			});
		}
		return result;
	}

	public List<CartBean> selectAllHql(Integer memberid) {

		if (memberid != null && memberid != 0) {
			return cartDAO.selectAllHql(memberid);
		}

		return null;
	}

	public List<CartBean> selectAllSql(Integer memberid) {

		if (memberid != null && memberid != 0) {
			return cartDAO.selectAllSql(memberid);
		}

		return null;
	}

	public void deleteMulti(Integer memberid, List<Integer> checkid) {
		boolean isSuccess = cartDAO.deleteMulti(memberid, checkid);
		// 確認資料有沒有刪除成功
		if (!isSuccess) {
			throw new RuntimeException();
		}
	}

	public boolean delete(Integer memberid, Integer productid) {
		boolean isSuccess = cartDAO.delete(memberid, productid);
		if (isSuccess) {
			return true;
		}
		return false;
	}

	public boolean update(CartBean cart) {
		CartBean update = cartDAO.update(cart);
		if (update != null) {
			return true;
		}
		return false;
	}

	// 商品加入購物車
	public boolean addCart(MemberBean member, Integer productid) {
		ProductBean product = cartDAO.selectProduct(productid);
		if (product != null) {
			// 確認資料庫裡，Cart 是否已經存在了
			CartBean cart = cartDAO.select(member, product);
			// 如果購物車裡，該商品已經存在，它的數量就加 1，價格也要更新
			if (cart != null) {
				cart.setNumber(cart.getNumber() + 1);
				cart.setSubtotal(cart.getSubtotal() + product.getPrice());
			} else {
				// 如果購物車裡不存在，才新增一筆新的資料
				CartBean newCart = new CartBean();
				newCart.setMemberid(member.getMemberid());
				newCart.setProductid(product.getProductid());
				newCart.setNumber(1);
				newCart.setSubtotal(product.getPrice());
				cartDAO.insert(newCart);
			}
			return true;
		}
		return false;
	}

	// 將『我的最愛』加到「購物車」
	@Transactional(rollbackFor = Exception.class) 
	public boolean addMyfavToCart(MemberBean member, Integer productid) {
		// 加入購物車
		boolean addSuccess = addCart(member, productid);
		// 刪除掉我的最愛
		boolean deleteSuccess = false;
		
		MyFavBean isExist = myfavDAO.select(member.getMemberid(), productid);
		if (isExist != null) {
			deleteSuccess = myfavDAO.delete(member.getMemberid(), productid);
		}
		// 兩個步驟都必須成功
		if ((addSuccess && deleteSuccess) == false) {
				throw new RuntimeException();
		} else {
			return true;
		}
	}
}
