package model.compkey;

import java.io.Serializable;


public class FavId implements Serializable {

	public Integer memberId;
	public Integer productId;

	// 建構式
	public FavId() {
	}

	public FavId(Integer memberId, Integer productId) {
		this.memberId = memberId;
		this.productId = productId;
	}

	// 覆寫方法  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		FavId other = (FavId) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

}
