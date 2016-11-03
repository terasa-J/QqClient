package com.qq.client.model;
import java.net.*;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.*;

import java.io.*;
public class QqClientConServer {
	//为把socket发给服务器，所以把他初步设置为static，用于验证登录Object类型主要是User
	public  Socket s;
	public boolean sendLoginInfoToServer(Object o){
		boolean a=false;
		try {
			s=new Socket("127.0.0.1",4545);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			//把user信息传送给Server验证用户名与密码，转到MyQqServer类
			oos.writeObject(o);
			
			//接收返回的信息
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message m=(Message)ois.readObject();
			if(m.getMesstype().equals("1")){
				//把成功连接到服务端的客户端加到线程,并加入到HashMap中存放，再启动线程
				ClientConServerThread cct=new ClientConServerThread(s);
				ManageClientConServerThread.addClientConServerThread(((User)o).getUesrId(),cct);
				cct.start();
				a=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
