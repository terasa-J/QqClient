
package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;

/**
 *���ܣ����ڹ���QqChat���棨��ʾ������jta�У�
 */
public class ManageQqChat {
	public static HashMap hm=new HashMap<String,QqChat>();
	//ȡ��
	public static QqChat getQqChat(String userIdAndFriendsId){
		return (QqChat)hm.get(userIdAndFriendsId);
	}
	//����
	public static void addQqChat(String userIdAndFriendsId,QqChat qqChat){
		hm.put(userIdAndFriendsId, qqChat);
	}
}
