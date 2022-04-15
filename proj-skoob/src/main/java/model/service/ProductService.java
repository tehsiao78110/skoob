package model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.AuthorBean;
import model.bean.ProductBean;
import model.dao.AuthorDAO;
import model.dao.ProductDAO;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	
	private Integer proid;
	
	//insert
//	public ProductBean insert(ProductBean bean) {
//		ProductBean result = null;
//		if(bean!=null && bean.getProductid()!=null) {
//			result = productDAO.insert(bean);
//		}
//		return result;
//	}
	public ProductBean select(Integer proid) {
		//proid = 2;
		System.out.println("before----selectname");
		ProductBean bean = productDAO.selectname(proid);
		System.out.println("after----selectname");
		if(bean!=null) {
			String productname = bean.getProductname();
			String productpic = bean.getProductpic();
			Integer author = bean.getAuthor();
			String press = bean.getPress();
			Integer price = bean.getPrice();
			String introduction = bean.getIntroduction();
			Date shelf = bean.getShelf();
			String classification = bean.getClassification();
			Integer buytime = bean.getBuytime();
			Integer inventory = bean.getInventory();
			//if(account.equals(username)&&password.equals(userpassword)) {
				return bean;
			//}
		}
		return null;
	}
	
	public List<ProductBean> selectallpro(String keyword) {
		//proid = 2;
		System.out.println("before----selectallpro");
		List<ProductBean> bean = productDAO.selectpro(keyword);
		System.out.println("after----selectallpro");
		if(bean!=null) {
			System.out.println("List<ProductBean>: "+ bean);
			//if(account.equals(username)&&password.equals(userpassword)) {
				return bean;
			//}
		}
		return null;
	}
	
}
