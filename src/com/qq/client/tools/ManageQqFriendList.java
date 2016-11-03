/**
 * 功能：管理好友列表，显示谁在线
 */
package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
	static HashMap hm=new HashMap<String,QqFriendList>();
	//添加
	public static void addQqFriendList(String userId,QqFriendList qqList){
		hm.put(userId, qqList);
	}
	//获取
	public static QqFriendList getQqFriendList(String userId){
		return (QqFriendList)hm.get(userId);
	}
}
