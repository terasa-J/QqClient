package com.qq.common;
import java.io.*;
/**
 * �û���Ϣ��
 */
public class User implements java.io.Serializable{
	private String uesrId;
	private String password;
	public String getUesrId() {
		return uesrId;
	}
	public void setUesrId(String uesrId) {
		this.uesrId = uesrId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
