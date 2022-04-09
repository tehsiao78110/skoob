package model.bean;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.compkey.FavId;

@Entity
@IdClass(FavId.class)
@Table(name = "favorites")
public class MyFavBean {
	@Id
	private Integer memberId;
	@Id
	private Integer productId;
//	JSP看的是什麼?  屬性

// 這張表對應的是 FAVORITE那張 OK   
//	account 跟address?
	@ManyToOne
	@JoinColumn(
			name = "memberId",
			referencedColumnName = "memberId",
			insertable = false, updatable = false
	)
	private MemberBean member;
	
	@ManyToOne
	@JoinColumn(
			name = "productId",
			referencedColumnName = "productId", 
			insertable = false, updatable = false
	)
	private ProductBean product;
/* 為什麼需要MEMBER?  收藏是登入才有的功能 資料庫才會知道這本書是誰藏的 
	JSP中看屬性所以是favorite.product.productname就可以取到產品名   */
	


// 製造一個JavaBean
 	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}
	
// 製造一個JavaBean   
	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

//  --------------------------------------------
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "FavoritesBean [memberId=" + memberId + ", productId=" + productId + "]";
	}
}
