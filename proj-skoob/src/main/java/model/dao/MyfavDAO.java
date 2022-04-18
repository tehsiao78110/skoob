package model.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MemberBean;
import model.bean.MyFavBean;
import model.bean.ProductBean;
import model.compkey.FavId;

@Repository
public class MyfavDAO {
	@Autowired
	private SessionFactory sessionFactory;
//	private Session session; //不能直接創

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

//	public MemberBean test() {		
//		MemberBean test= this.getSession().get(MemberBean.class, 1);
//		return test;
//		
//	}

	public MemberBean selectMember(Integer memberId) {
		return this.getSession().get(MemberBean.class, memberId);
	}

	public Set<MyFavBean> selectAll(Integer id) {
		MemberBean isExist = this.getSession().get(MemberBean.class, id);
		if (isExist != null) {
			return isExist.getMyFavs();
		}
		return null;
	}

	public void selectProduct() {
		ProductBean product = this.getSession().get(ProductBean.class, 1);
		Set<MyFavBean> myFavs = product.getMyFavs();
		for (MyFavBean myFav : myFavs) {
			System.out.println("ID : " + myFav.getMemberId() + "productName : " + myFav.getProduct().getProductname());
		}
	}

	// 一個方法寫進資料庫 iNSERT
	// 新增memberID request:JSP->servlet->service->dao
	// 塞myFavBean 寫進資料表favorite
	// 被service呼叫，呼叫myFavDAO.insert 要傳MyFavBean進去 bean的型別是MyFavBean
	public MyFavBean insertB(MyFavBean bean) {
		this.getSession().save(bean);
		return bean;
	}
	
//	select favorite表，將資料MyFavBean回傳   沒有耶  ctl+shif+f   開的
	public MyFavBean selectMyFav(Integer memberId, Integer productId) {
		if (memberId != null & productId != null) {
			MyFavBean favbean1 = this.getSession().get(MyFavBean.class, new FavId(memberId, productId)); // 要寫方法?
//			MyFavBean favbean2 = session.get(MyFavBean.class, new FavId(2,3));  不可以用session，改用this
//			System.out.println("from DAO: 書名: " +favbean1.getProduct().getProductname());
			return favbean1;
		} else {
			return null;
//			System.out.println("DAO,抓到空的資料表");   對

		}
	}
	
//	透過MemberBean拿到Set<MyFavBean>
	public Set<MyFavBean> selectMyFavs(Integer id){
		MemberBean memberExist = this.getSession().get(MemberBean.class, id);
		if(memberExist !=null) {
			return memberExist.getMyFavs();
		}
		return null;
	}
	
	
	//建興的API	 撈FavBean
	public MyFavBean selectfavdao(Integer memberId, Integer productId) {
		if (memberId != null && productId != null) {
			Query<MyFavBean> query = sessionFactory.getCurrentSession().createQuery(
					"FROM MyFavBean WHERE memberid = :memberid and productid = :productid", MyFavBean.class);
			query.setParameter("memberid", memberId);
			query.setParameter("productid", productId);
			MyFavBean count = query.uniqueResult();
			return count;
		} else {
			return null;
		}
	}

// 刪除一筆收藏
	public boolean delete(Integer memberId, Integer productId) {
		if (memberId != null && productId != null) {
			String hql = "delete from MyFavBean where memberId=?0 and productId=?1";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter(0, memberId);
			query.setParameter(1, productId);
			query.executeUpdate();
			 return true;
		} else {
			 return false;
		}
	}
}