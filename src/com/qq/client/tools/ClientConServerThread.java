/**
 * ���ܣ������ͻ����������ͨѶ���߳�
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
			//��ȡ
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message mess=(Message)ois.readObject();
				System.out.println(mess.getSender()+"��"+mess.getGetter()+"˵��"+mess.getContext());
				
				if(mess.getMesstype().equals(MessageType.message_common)){
					//�Ѵӷ����������Ϣ����ʾ��Ϣ����Ӧ��jta�У�1��2����Ϣ����ô������2��1��������Ĵ�����ʾ��������getter��sender��
					QqChat qqChat=ManageQqChat.getQqChat(mess.getGetter()+" "+mess.getSender());
					//��ʾ
					qqChat.showMessage(mess);
				}else if(mess.getMesstype().equals(MessageType.message_ret_onlineFriend)){
				//	String conn=mess.getContext();
					//��������������Ϊ��3 6 5 11��
			//		String friends[]=conn.split(" ");
					String getter=mess.getGetter();
					//�޸ĺ����б�
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
