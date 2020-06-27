package com.core;

public class MoneyConst {
	public final static String HEADER_USER = "x-user-id";
	public final static String HEADER_ROOM = "x-room-name";
	
	
	public static enum CODE {
		TEN_MINUTES_EXPIRED, 
		SEVEN_DAYS_EXPIRED,
		TOKEN_INVALID,
		SUCCESS,
		NOT_OWNER,
		NOT_RECIVER,
		ALREADY_RECIVED
	}
	
	
}
