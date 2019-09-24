package kr.co.ehr.chart.service;

import kr.co.ehr.cmn.DTO;

public class LineVO extends DTO {
	private int day;
	private double earning01;
	private double earning02;
	private double earning03;

	public LineVO() {
		// TODO Auto-generated constructor stub
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public double getEarning01() {
		return earning01;
	}

	public void setEarning01(double earning01) {
		this.earning01 = earning01;
	}

	public double getEarning02() {
		return earning02;
	}

	public void setEarning02(double earning02) {
		this.earning02 = earning02;
	}

	public double getEarning03() {
		return earning03;
	}

	public void setEarning03(double earning03) {
		this.earning03 = earning03;
	}

	public LineVO(int day, double earning01, double earning02, double earning03) {
		super();
		this.day = day;
		this.earning01 = earning01;
		this.earning02 = earning02;
		this.earning03 = earning03;
	}

	@Override
	public String toString() {
		return "LineVO [day=" + day + ", earning01=" + earning01 + ", earning02=" + earning02 + ", earning03="
				+ earning03 + "]";
	}

}