package com.qq.common;
import java.io.*;
//��֤��Ϣ������  1.��¼�ɹ�  2.��¼ʧ��  3.������ͨ��
public class Message implements java.io.Serializable{
	//��֤�Ƿ�������
	private String messtype;
	//�����ߣ������ߣ��������ݣ�����ʱ��
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
