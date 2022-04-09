package model.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;

import model.compkey.OrderitemId;

@Entity
@IdClass(OrderitemId.class)
@Table(name = "Orderitem")
public class OrderitemBean implements Serializable {

	@ManyToOne
	@JoinColumn(name = "orderid", referencedColumnName = "orderid", insertable = false, updatable = false)
	private OrderBean orderlist;

	public OrderBean getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(OrderBean orderlist) {
		this.orderlist = orderlist;
	}

	@ManyToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID", insertable = false, updatable = false)
	private ProductBean product;

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	@Id
	private String orderid;
	@Id
	private Integer productId;
	private Integer num;
	private Integer price;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderitemBean [orderid=" + orderid + ", productId=" + productId + ", num=" + num + ", price=" + price
				+ "]";
	}

}