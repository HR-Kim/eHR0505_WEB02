package kr.co.ehr.chart.service;

import kr.co.ehr.cmn.DTO;

public class PieVO extends DTO {
	private String topping;
	private int slices;
	
	
	public PieVO() {}
	
	
	public PieVO(String topping, int slices) {
		super();
		this.topping = topping;
		this.slices = slices;
	}


	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	public int getSlices() {
		return slices;
	}
	public void setSlices(int slices) {
		this.slices = slices;
	}


	@Override
	public String toString() {
		return "PieVO [topping=" + topping + ", slices=" + slices + ", toString()=" + super.toString() + "]";
	}
	
	
}
