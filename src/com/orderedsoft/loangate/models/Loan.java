package com.orderedsoft.loangate.models;

public class Loan {

	private String _title;
	private int _value; 
	
	
	public Loan() {
		// TODO Auto-generated constructor stub
	}

	public void setValue(int value) {
		_value = value;
	}

	public void setTitle(String title) {
		_title = title;
	}
	
	@Override
	public String toString() {
		return _title;
	}

	public CharSequence getTitle() {
		return _title;
	}

}
