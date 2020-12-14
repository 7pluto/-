package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable {

	private String number;
	private String name;
	private String sex;
	private String identity;
	private String address;
	private String phone;
	private String classed;
	private String grade;
	private Integer classedInt;
	private Integer gradeInt;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getClassed() {
		return classed;
	}
	public void setClassed(String classed) {
		this.classed = classed;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getClassedInt() {
		return classedInt;
	}
	public void setClassedInt(Integer classedInt) {
		this.classedInt = classedInt;
	}
	public Integer getGradeInt() {
		return gradeInt;
	}
	public void setGradeInt(Integer gradeInt) {
		this.gradeInt = gradeInt;
	}
	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", sex=" + sex + ", identity=" + identity + ", address="
				+ address + ", phone=" + phone + ", classed=" + classed + ", grade=" + grade + ", classedInt="
				+ classedInt + ", gradeInt=" + gradeInt + "]";
	}

	
}
