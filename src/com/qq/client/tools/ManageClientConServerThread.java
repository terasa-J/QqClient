package com.qq.client.tools;
//����ͻ�������������ӵ��߳�
import java.util.HashMap;

public class ManageClientConServerThread {
	public static HashMap hm=new HashMap<String,ClientConServerThread>();
	public static void addClientConServerThread(String id,ClientConServerThread cct){
		hm.put(id, cct);
	}
	public static ClientConServerThread getClientConServerThread(String id){
		return (ClientConServerThread)hm.get(id);
	}
}
