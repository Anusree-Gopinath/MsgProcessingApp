package com.msg.server.model;

public enum OperationType {
	ADD("ADD"),SUBTRACT("SUBTRACT"),MULTIPLY("MULTIPLY");
	
	private String type;
	
	OperationType(String type){
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
}
