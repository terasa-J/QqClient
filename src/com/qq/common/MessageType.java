/**
 * 判断信息类型，显示好友在线
 */
package com.qq.common;

public interface MessageType {
	//登录成功
	String message_succeed="1";
	//登录失败
	String message_failed="2";
	//普通信息包
	String message_common="3";
	//获取好友在线包
	String message_get_onlineFriend="4";
	//返回好友在线包
	String message_ret_onlineFriend="5";
}
