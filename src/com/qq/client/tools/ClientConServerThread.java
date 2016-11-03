/**
 * 功能：获得与客户端与服务器通讯的线程
 */
package com.qq.client.tools;
import java.net.*;

import com.qq.client.view.*;
import com.qq.common.*;


import java.io.*;
public class ClientConServerThread extends Thread {
	Socket s;
	public ClientConServerThread(Socket s){
		this.s=s;
	}
	public void run(){
		while(true){
			//读取
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message mess=(Message)ois.readObject();
				System.out.println(mess.getSender()+"对"+mess.getGetter()+"说："+mess.getContext());
				
				if(mess.getMesstype().equals(MessageType.message_common)){
					//把从服务器获得信息，显示消息在相应的jta中（1给2发信息，那么内容在2和1正在聊天的窗口显示，所以先getter再sender）
					QqChat qqChat=ManageQqChat.getQqChat(mess.getGetter()+" "+mess.getSender());
					//显示
					qqChat.showMessage(mess);
				}else if(mess.getMesstype().equals(MessageType.message_ret_onlineFriend)){
				//	String conn=mess.getContext();
					//服务器返回类型为“3 6 5 11”
			//		String friends[]=conn.split(" ");
					String getter=mess.getGetter();
					//修改好友列表
					QqFriendList qqList=ManageQqFriendList.getQqFriendList(getter);
					if(qqList!=null){
						qqList.updateFriend(mess);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
}
