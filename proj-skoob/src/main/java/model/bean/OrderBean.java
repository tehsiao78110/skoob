package model.bean;

import java.beans.Transient;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orderlist")
public class OrderBean {
	@ManyToOne
	@JoinColumn(name = "MEMBERID", referencedColumnName = "MEMBERID", insertable = false, updatable = false)
	private MemberBean member;

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "orderlist")
	private Set<OrderitemBean> orderitems;

	public Set<OrderitemBean> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<OrderitemBean> orderitems) {
		this.orderitems = orderitems;
	}

	@Id
	private String orderid;
	private java.util.Date ordertime;
	private Integer memberid;
	private String payment;
	private String delivery;
	private String invoicetype;
	private Integer totalprice;
	private String name;
	private String phone;
	private Byte state;

	@Override
	public String toString() {
		return "orderid=" + orderid + ", ordertime=" + ordertime + ", memberid=" + memberid + ", payment=" + payment
				+ ", delivery=" + delivery + ", totalprice=" + totalprice + ", state=" + state + "]";
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public java.util.Date getOrdertime() {

		return ordertime;
	}

	public void setOrdertime(java.util.Date ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Integer getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
