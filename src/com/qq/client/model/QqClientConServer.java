package com.qq.client.model;
import java.net.*;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.*;

import java.io.*;
public class QqClientConServer {
	//Ϊ��socket���������������԰�����������Ϊstatic��������֤��¼Object������Ҫ��User
	public  Socket s;
	public boolean sendLoginInfoToServer(Object o){
		boolean a=false;
		try {
			s=new Socket("127.0.0.1",4545);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			//��user��Ϣ���͸�Server��֤�û��������룬ת��MyQqServer��
			oos.writeObject(o);
			
			//���շ��ص���Ϣ
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message m=(Message)ois.readObject();
			if(m.getMesstype().equals("1")){
				//�ѳɹ����ӵ�����˵Ŀͻ��˼ӵ��߳�,�����뵽HashMap�д�ţ��������߳�
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
