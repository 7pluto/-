package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Credit implements Serializable {

	private String Number;
	private String name;
	private double credit;
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Credit [Number=" + Number + ", name=" + name + ", credit=" + credit + "]";
	}
	
}
