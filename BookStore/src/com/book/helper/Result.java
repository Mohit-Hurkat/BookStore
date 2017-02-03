package com.book.helper;

public class Result {
	private boolean truthValue;
	private String message;
	
	public Result(boolean truthValue, String message) {
		this.truthValue = truthValue;
		this.message = message;
	}
	
	public boolean isTruthValue() {
		return truthValue;
	}
	public void setTruthValue(boolean truthValue) {
		this.truthValue = truthValue;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
