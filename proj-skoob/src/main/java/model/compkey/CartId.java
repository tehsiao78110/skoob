package model.compkey;

import java.io.Serializable;

public class CartId implements Serializable {

	public Integer memberid;
	public Integer productid;

	// 建構式
	public CartId() {
	}

	public CartId(Integer memberid, Integer productid) {
		this.memberid = memberid;
		this.productid = productid;
	}

	// 覆寫方法
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberid == null) ? 0 : memberid.hashCode());
		result = prime * result + ((productid == null) ? 0 : productid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartId other = (CartId) obj;
		if (memberid == null) {
			if (other.memberid != null)
				return false;
		} else if (!memberid.equals(other.memberid))
			return false;
		if (productid == null) {
			if (other.productid != null)
				return false;
		} else if (!productid.equals(other.productid))
			return false;
		return true;
	}

}
