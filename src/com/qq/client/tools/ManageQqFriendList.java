/**
 * ���ܣ���������б���ʾ˭����
 */
package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
	static HashMap hm=new HashMap<String,QqFriendList>();
	//���
	public static void addQqFriendList(String userId,QqFriendList qqList){
		hm.put(userId, qqList);
	}
	//��ȡ
	public static QqFriendList getQqFriendList(String userId){
		return (QqFriendList)hm.get(userId);
	}
}
