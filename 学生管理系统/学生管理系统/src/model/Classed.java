package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Classed implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
