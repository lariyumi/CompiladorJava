package io.compiler.types;

public enum Types {
	NUMBER(1),
	TEXT(2);
	
	private int value;
	
	private Types(int valueNumber) { 
		this.value = valueNumber;
	}
	public Integer getValue() {
		return this.value;
	}
}
