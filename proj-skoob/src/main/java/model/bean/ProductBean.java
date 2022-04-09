package model.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductBean {
	@Id
	private Integer productid;
	private String productname;
	private String productpic;
	private Integer author;
	private String press;
	private Integer price;
	private String introduction;
	private java.util.Date shelf;
	private String Classification;
	private Integer buytime;
	private Integer Inventory;

	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "product")
	private Set<MyFavBean> myFavs;

	public Set<MyFavBean> getMyFavs() {
		return myFavs;
	}

	public void setMyFavs(Set<MyFavBean> myFavs) {
		this.myFavs = myFavs;
	}

	// -------------------------------------------------------------------
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "product")
	private Set<OrderitemBean> orderitems;

	public Set<OrderitemBean> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<OrderitemBean> orderitems) {
		this.orderitems = orderitems;
	}

	// -------------------------------------------------------------------

	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "product")
	private Set<CartBean> carts;

	public Set<CartBean> getCarts() {
		return carts;
	}

	public void setCarts(Set<CartBean> carts) {
		this.carts = carts;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductpic() {
		return productpic;
	}

	public void setProductpic(String productpic) {
		this.productpic = productpic;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public java.util.Date getShelf() {
		return shelf;
	}

	public void setShelf(java.util.Date shelf) {
		this.shelf = shelf;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getClassification() {
		return Classification;
	}

	public void setClassification(String classification) {
		Classification = classification;
	}

	public Integer getBuytime() {
		return buytime;
	}

	public void setBuytime(Integer buytime) {
		this.buytime = buytime;
	}

	public Integer getInventory() {
		return Inventory;
	}

	public void setInventory(Integer inventory) {
		Inventory = inventory;
	}

}
