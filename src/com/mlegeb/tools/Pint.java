package com.mlegeb.tools;

import java.util.ArrayList;
import java.util.List;

public class Pint {

	private String value;
	public boolean isAdd = false;
	public List<String> totalUnuse = new ArrayList<String>();


	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public void addTotalUnuse(String value){
		this.totalUnuse.add(value);	
	}
}
