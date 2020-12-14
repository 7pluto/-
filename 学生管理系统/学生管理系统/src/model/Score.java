package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Score implements Serializable {

	private String number;
	private String name;
	private String teacher;
	private double credit;
	private double score;
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
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Score [number=" + number + ", name=" + name + ", teacher=" + teacher + ", credit=" + credit + ", score="
				+ score + "]";
	}
	
}
