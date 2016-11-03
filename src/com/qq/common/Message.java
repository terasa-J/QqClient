package com.qq.common;
import java.io.*;
//验证消息的类型  1.登录成功  2.登录失败  3.其他普通包
public class Message implements java.io.Serializable{
	//验证是否连接上
	private String messtype;
	//发送者，接受者，发送内容，发送时间
	private String sender;
	private String getter;
	private String context;
	private String sendTime;
	public String getMesstype() {
		return messtype;
	}

	public void setMesstype(String messtype) {
		this.messtype = messtype;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String con) {
		this.context = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}
