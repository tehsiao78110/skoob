package model.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class MemberBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberid;
	private String account;
	private String password;
	private String name;
	private String county;
	private String district;
	private String address;
	private String email;
	private Date birth;
	private String tel;
	private String phone;

	//	33-42 DAO呼叫這個方法拿到MyFavBean集合
	// ---------------------------------------------------------
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "member")
	private Set<MyFavBean> Favorites;

	public Set<MyFavBean> getMyFavs() {
		return Favorites;
	}

	public void setMyFavs(Set<MyFavBean> favorites) {
		Favorites = favorites;
	}
	
	// ---------------------------------------------------------
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "member")
	private Set<CartBean> carts;

	public Set<CartBean> getCarts() {
		return carts;
	}

	public void setCarts(Set<CartBean> carts) {
		this.carts = carts;
	}

	// ---------------------------------------------------------
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "member")
	private Set<OrderBean> orderlists;
	
	public Set<OrderBean> getOrderlists() {
		return orderlists;
	}

	public void setOrderlists(Set<OrderBean> orderlists) {
		this.orderlists = orderlists;
	}

	// ---------------------------------------------------------
	
	@Override
	public String toString() {
		return "MemberBean [account=" + account + ", password=" + password + ", name=" + name + ", county=" + county
				+ ", district=" + district + ", address=" + address + ", email=" + email + ", birth=" + birth + ", tel="
				+ tel + ", phone=" + phone + "]";
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	// 新增開頭---------------------------------------------
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	// 結尾--------------------------------------------------
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
