
package com.qq.client.view;
import java.awt.*;
import javax.swing.*;

import com.qq.client.model.QqClientConServer;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
/**
 *聊天界面，要有参数，与好友列表相应
 *由于要一直处于读取状态，所以要使用线程
 */
public class QqChat extends JFrame implements ActionListener{
	JTextArea jta;
	JTextField jtf;
	JButton btn;
	JPanel pan;
	String sender;
	String getter;
	public QqChat(String sender,String getter){
		this.sender=sender;
		this.getter=getter;
		
		jta=new JTextArea();
		jtf=new JTextField(15);
		btn=new JButton("发送");
		btn.addActionListener(this);
		pan=new JPanel();
		pan.add(jtf);
		pan.add(btn);
		this.add(pan, "South");
		this.add(jta,"Center");
		
		this.setTitle(sender+"与"+getter+"聊天中");
		this.setSize(300,200);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(QqChat.class.getResource("/images/qq.gif")));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//显示内容
	public void showMessage(Message mes){
		String info=mes.getSender()+"对"+mes.getGetter()+"说："+mes.getContext()+"时间："+mes.getSendTime()+"\r\n";
		this.jta.append(info);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 发送信息
		if(e.getSource().equals(btn)){
			//获取scoket客户端
			Message m=new Message();
			m.setSender(sender);
			m.setGetter(getter);
			m.setContext(jtf.getText());
			m.setMesstype(MessageType.message_common);
			m.setSendTime(new Date().toString());
			try {
				//发给服务器，把QqClientConServer中Socket定义为static，获取连接上的socket
				ObjectOutputStream oos=new ObjectOutputStream((ManageClientConServerThread.getClientConServerThread(sender)).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	//读取信息
//	public void run() {
//		while(true){
//			try {
//				ObjectInputStream ois=new ObjectInputStream(ManageClientConServerThread.getClientConServerThread(sender).s.getInputStream());
//				Message mes=(Message)ois.readObject();
//				String info=mes.getSender()+"对"+mes.getGetter()+"说："+mes.getContext()+"时间："+mes.getSendTime()+"\r\n";
//				this.jta.append(info);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}

}
