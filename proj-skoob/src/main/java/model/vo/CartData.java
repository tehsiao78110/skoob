package model.vo;

public class CartData {

	private Integer totalCost;
	private Integer productNum;
	private Integer productAllNum;

	public CartData() {

	}

	public CartData(Integer totalCost, Integer productNum, Integer productAllNum) {
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
