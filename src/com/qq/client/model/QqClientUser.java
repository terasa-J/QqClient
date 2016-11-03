package com.qq.client.model;

public class QqClientUser {
	
	public boolean checkUser(Object o){
		return new QqClientConServer().sendLoginInfoToServer(o);
	}
}
