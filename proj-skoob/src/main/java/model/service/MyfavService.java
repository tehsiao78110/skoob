package model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MemberBean;
import model.bean.MyFavBean;
import model.bean.ProductBean;
import model.dao.MyfavDAO;
import model.dao.ProductDAO;

@Service
@Transactional
public class MyfavService {
	@Autowired
	private MyfavDAO myfavDAO;

	@Autowired
	private ProductDAO productDAO;

	// 新增「我的收藏」
	public MyFavBean addMyfav(MyFavBean bean) {
		MyFavBean result = null;
		if (bean != null && bean.getProductId() != null && bean.getMemberId() != null) {
			result = myfavDAO.insert(bean);
		}
		return result;
	}

	// 取得「會員」的『所有收藏商品』   
	public List<MyFavBean> selectMyFavs(Integer memberId) {
		List<MyFavBean> result = null;
		if (memberId != null) {
			result = new ArrayList<MyFavBean>(myfavDAO.selectMyFavs(memberId));

			Collections.sort(result, new Comparator<MyFavBean>() {
				@Override
				public int compare(MyFavBean o1, MyFavBean o2) {
					return o1.getProductId().compareTo(o2.getProductId()); // 排序 以productId比較大小
				}
			});

		}
		return result;
	}

	// 確認商品，是否有被收藏
	public boolean checkMyfav(Integer memberId, Integer productId) {
		MyFavBean result = null;
		if (memberId != null && productId != null) {
			result = myfavDAO.select(memberId, productId);
			if (result != null) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// 刪除一筆收藏
	public boolean delete(Integer memberId, Integer productId) {
		boolean isExist = checkMyfav(memberId, productId);
		if (isExist) {
			return myfavDAO.delete(memberId, productId);
		}
		return false;
	}

}
