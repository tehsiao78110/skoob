package model.dto;

import java.util.List;

import model.bean.CartBean;
import util.CartUtil;

public class CartDTO {

	private Integer totalCost;
	private Integer productNum;
	private Integer productAllNum;

	public CartDTO(Integer totalCost, Integer productNum, Integer productAllNum) {
		this.totalCost = totalCost;
		this.productNum = productNum;
		this.productAllNum = productAllNum;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Integer getProductAllNum() {
		return productAllNum;
	}

	public void setProductAllNum(Integer productAllNum) {
		this.productAllNum = productAllNum;
	}

}
