
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
 *������棬Ҫ�в�����������б���Ӧ
 *����Ҫһֱ���ڶ�ȡ״̬������Ҫʹ���߳�
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
		btn=new JButton("����");
		btn.addActionListener(this);
		pan=new JPanel();
		pan.add(jtf);
		pan.add(btn);
		this.add(pan, "South");
		this.add(jta,"Center");
		
		this.setTitle(sender+"��"+getter+"������");
		this.setSize(300,200);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(QqChat.class.getResource("/images/qq.gif")));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//��ʾ����
	public void showMessage(Message mes){
		String info=mes.getSender()+"��"+mes.getGetter()+"˵��"+mes.getContext()+"ʱ�䣺"+mes.getSendTime()+"\r\n";
		this.jta.append(info);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// ������Ϣ
		if(e.getSource().equals(btn)){
			//��ȡscoket�ͻ���
			Message m=new Message();
			m.setSender(sender);
			m.setGetter(getter);
			m.setContext(jtf.getText());
			m.setMesstype(MessageType.message_common);
			m.setSendTime(new Date().toString());
			try {
				//��������������QqClientConServer��Socket����Ϊstatic����ȡ�����ϵ�socket
				ObjectOutputStream oos=new ObjectOutputStream((ManageClientConServerThread.getClientConServerThread(sender)).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	//��ȡ��Ϣ
//	public void run() {
//		while(true){
//			try {
//				ObjectInputStream ois=new ObjectInputStream(ManageClientConServerThread.getClientConServerThread(sender).s.getInputStream());
//				Message mes=(Message)ois.readObject();
//				String info=mes.getSender()+"��"+mes.getGetter()+"˵��"+mes.getContext()+"ʱ�䣺"+mes.getSendTime()+"\r\n";
//				this.jta.append(info);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}

}
