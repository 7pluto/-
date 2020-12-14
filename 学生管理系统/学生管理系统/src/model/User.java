package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {

	private String number;
	private String password;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [number=" + number + ", password=" + password + "]";
	}
	
}
