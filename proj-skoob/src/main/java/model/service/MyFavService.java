package model.service;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MyFavBean;
import model.bean.ProductBean;
import model.dao.MyFavDAO;
import model.dao.ProductDAO;


@Service
@Transactional
public class MyFavService {
	@Autowired
	private MyFavDAO myFavDAO; 
	
	@Autowired
	private ProductDAO productDAO;
	
	//加到購物車 
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null && bean.getProductid()!=null) {
			result = productDAO.insert(bean);
		}
		return result;
	}

//	public ProductBean update(ProductBean bean) {
//		ProductBean result = null;
//		if(bean!=null && bean.getId()!=null) {
//			result = productDao.update(bean.getName(), bean.getPrice(),
//					bean.getMake(), bean.getExpire(), bean.getId());
//		}
//		return result;
//	}

// 刪除我的收藏
//	public boolean delete(ProductBean bean) {
//		boolean result = false;
//		if(bean!=null && bean.getProductId()!=null) {
//			result = productDAO.delete(bean.getProductId());
//		}
//		return result;
//	}	
//	

	
//	呼叫 myFavDAO.insert  
	public MyFavBean insertA(MyFavBean bean) {
		MyFavBean result = null;
		if(bean!=null && bean.getProductId()!=null && bean.getMemberId()!=null) {
			result = myFavDAO.insertB(bean);
		}
		return result;
	}

//  傳送MyFavBean
    public MyFavBean selectMyFav(Integer memberId, Integer productId) {
    	MyFavBean result = null;
    	if(memberId!= null && productId!= null) {
    		
    		MyFavBean favor = myFavDAO.selectMyFav(memberId, productId); 
    		
			String result1 = favor.getProduct().getProductname();
			System.out.println("from Service: 書名= " +result1);			
			return favor;
    	}
		return result; 
    }
    
//    傳送Set<MyFavBeans>   轉換成List   
    public List<MyFavBean> selectMyFavs(Integer memberId) {
    	List<MyFavBean> result = null;
    	if (memberId != null) {
    		result = new ArrayList<MyFavBean>(myFavDAO.selectMyFavs(memberId));

    		Collections.sort(result, new Comparator<MyFavBean>() {
    			@Override
    			public int compare(MyFavBean o1, MyFavBean o2) {    				
    				return o1.getProductId().compareTo(o2.getProductId()); //排序 以productId比較大小
    			}
			});
    		
    		
//    		for(MyFavBean myFav : result) {
//    			System.out.printf("from Service: member = %s,  productimg = %s,  product name = %s,  product price= %d%n",
//    					myFav.getMember().getName(), myFav.getProduct().getProductpic(), myFav.getProduct().getProductname(),
//    					myFav.getProduct().getPrice());
//    		}
    	}
		return result;
	}
    
    
//
 	public boolean checkMember(Integer memberId) {
 		// 確認資料庫有抓出東西
 		if (memberId != null && (myFavDAO.selectMember(memberId)) != null) {
 			return true;
 		}
 		return false;
 	}
 	
//	建興的 判斷有無收到FavBean
	public boolean checkMyfav(Integer memberId, Integer productId) {
        MyFavBean result = null;
        if(memberId!= null && productId!= null) {
            result = myFavDAO.selectfavdao(memberId,productId);
            if(result !=null) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
// 刪除一筆收藏
    public void deletefav(Integer memberId, Integer productId) {
        if(memberId!= null && productId!= null) {
            myFavDAO.delete(memberId,productId);
        }
    }   
    
}


	
