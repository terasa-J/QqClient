
package com.qq.client.view;
import java.awt.*;
import javax.swing.*;

import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;

import java.awt.event.*;
/**
 * 功能：好友列表的显示
 * 运用卡片布局
 */
public class QqFriendList extends JFrame implements ActionListener,MouseListener {
	//第一个卡片
	JPanel Car1_pan1,Car1_pan2,Car1_pan3;
	JButton Car1_btnF,Car1_btnM,Car1_btnH;
	JScrollPane Car1_jsp;
	JLabel[] friend;
	//第二个卡片
	JPanel Car2_pan1,Car2_pan2,Car2_pan3;
	JButton Car2_btnF,Car2_btnM,Car2_btnH;
	JScrollPane Car2_jsp;
	//第三个卡片
	JPanel Car3_pan1,Car3_pan2,Car3_pan3;
	JButton Car3_btnF,Car3_btnM,Car3_btnH;
	JScrollPane Car3_jsp;
	//卡片布局
	CardLayout cl;
	String userId;
	public QqFriendList(String userId){
//第一个卡片
		Car1_btnF=new JButton("我的好友");
		Car1_btnM=new JButton("陌生人");
		Car1_btnM.addActionListener(this);
		Car1_btnH=new JButton("黑名单");
		Car1_btnH.addActionListener(this);
		Car1_pan1=new JPanel(new BorderLayout());
		//显示好友
		Car1_pan2=new JPanel(new GridLayout(50,1,4,4));
		Car1_jsp=new JScrollPane(Car1_pan2);
		friend=new JLabel[50];
		for(int i=0;i<friend.length;i++){
			friend[i]=new JLabel((i+1)+"",new ImageIcon((QqClientLogin.class.getResource("/images/mm.jpg"))),JLabel.LEFT);
			//只有当自己ID相等时才显示自己在线的信息
			friend[i].setEnabled(false);
			if(friend[i].getText().equals(userId)){
				friend[i].setEnabled(true);
			}
			Car1_pan2.add(friend[i]);
			friend[i].addMouseListener(this);
			
		}
		Car1_pan3=new JPanel(new GridLayout(2,1));
		Car1_pan3.add(Car1_btnM);
		Car1_pan3.add(Car1_btnH);
		
		Car1_pan1.add(Car1_btnF, "North");
		Car1_pan1.add(Car1_jsp, "Center");
		Car1_pan1.add(Car1_pan3,"South");

//第二个卡片
		Car2_btnF=new JButton("我的好友");
		Car2_btnF.addActionListener(this);
		Car2_btnM=new JButton("陌生人");
		Car2_btnH=new JButton("黑名单");
		Car2_btnH.addActionListener(this);
		Car2_pan1=new JPanel(new BorderLayout());
		//显示好友
		Car2_pan2=new JPanel(new GridLayout(20,1,4,4));
		Car2_jsp=new JScrollPane(Car2_pan2);
		JLabel[] stranges=new JLabel[20];
		for(int i=0;i<stranges.length;i++){
			stranges[i]=new JLabel((i+1)+"",new ImageIcon((QqClientLogin.class.getResource("/images/mm.jpg"))),JLabel.LEFT);
			//只有当自己ID相等时才显示自己在线的信息
			stranges[i].setEnabled(false);
			if(stranges[i].getText().equals(userId)){
				stranges[i].setEnabled(true);
			}
			Car2_pan2.add(stranges[i]);
			stranges[i].addMouseListener(this);
		}
		Car2_pan3=new JPanel(new GridLayout(2,1));
		Car2_pan3.add(Car2_btnF);
		Car2_pan3.add(Car2_btnM);
				
		Car2_pan1.add(Car2_btnH, "South");
		Car2_pan1.add(Car2_jsp, "Center");
		Car2_pan1.add(Car2_pan3,"North");
		
//第三个卡片
		Car3_btnF=new JButton("我的好友");
		Car3_btnF.addActionListener(this);
		Car3_btnM=new JButton("陌生人");
		Car3_btnM.addActionListener(this);
		Car3_btnH=new JButton("黑名单");
		Car3_pan1=new JPanel(new BorderLayout());
		//显示好友
		Car3_pan2=new JPanel(new GridLayout(25,1,4,4));
		Car3_jsp=new JScrollPane(Car3_pan2);
		JLabel[] blackList=new JLabel[25];
		for(int i=0;i<blackList.length;i++){
			blackList[i]=new JLabel((i+1)+"",new ImageIcon((QqClientLogin.class.getResource("/images/mm.jpg"))),JLabel.LEFT);
			//只有当自己ID相等时才显示自己在线的信息
			blackList[i].setEnabled(false);
			if(blackList[i].getText().equals(userId)){
				blackList[i].setEnabled(true);
			}
			Car3_pan2.add(blackList[i]);
			blackList[i].addMouseListener(this);
		}
		Car3_pan3=new JPanel(new GridLayout(3,1));
		Car3_pan3.add(Car3_btnF);
		Car3_pan3.add(Car3_btnM);
		Car3_pan3.add(Car3_btnH);
						
		Car3_pan1.add(Car3_jsp, "Center");
		Car3_pan1.add(Car3_pan3,"North");
		
		
//总体布局		
		this.userId=userId;
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(Car1_pan1,"Friend");
		this.add(Car2_pan1,"Strange");
		this.add(Car3_pan1,"BlackList");
		//在标题上显示自己的编号
		this.setTitle(userId);
		this.setBounds(400,50,210,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//更新好友列表方法
	public void updateFriend(Message mess){
		String con=mess.getContext();
		String onLineFriends[]=con.split(" ");
		for(int i=0;i<onLineFriends.length;i++){
			friend[Integer.parseInt(onLineFriends[i])-1].setEnabled(true);
		}
				
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(Car1_btnM)||e.getSource().equals(Car3_btnM)){
			cl.show(this.getContentPane(),"Strange");
		}else if(e.getSource().equals(Car2_btnF)||e.getSource().equals(Car3_btnF)){
			cl.show(this.getContentPane(),"Friend");
		}else{
			cl.show(this.getContentPane(),"BlackList");
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			//获得你与朋友聊天的Id
			String friendId=((JLabel)e.getSource()).getText();
			//启动线程
			QqChat qqChat=new QqChat(this.userId,friendId);
			//把QqChat加入到HashMap中保存
			ManageQqChat.addQqChat((userId+" "+friendId),qqChat);
			
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.black);
		
	}

}
