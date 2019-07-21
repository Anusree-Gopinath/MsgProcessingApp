package com.msg.server.model;

public enum MessageType {
	MSG("Message"), DMSG("Detailed Message"), AMSG("Adjustment Message");
	
	private String type;
	
	MessageType(String type){
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}

}
