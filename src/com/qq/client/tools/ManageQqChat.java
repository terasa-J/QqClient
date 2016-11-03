
package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;

/**
 *功能：用于管理QqChat界面（显示内容在jta中）
 */
public class ManageQqChat {
	public static HashMap hm=new HashMap<String,QqChat>();
	//取得
	public static QqChat getQqChat(String userIdAndFriendsId){
		return (QqChat)hm.get(userIdAndFriendsId);
	}
	//加入
	public static void addQqChat(String userIdAndFriendsId,QqChat qqChat){
		hm.put(userIdAndFriendsId, qqChat);
	}
}
