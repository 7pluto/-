package model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ScoreCount implements Serializable {

	private List<String> number;
	private double max;
	private double min;
	private double avg;

	public List<String> getNumber() {
		return number;
	}
	public void setNumber(List<String> number) {
		this.number = number;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
}
