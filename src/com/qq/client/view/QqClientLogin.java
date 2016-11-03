package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import javax.swing.border.SoftBevelBorder;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

import javax.swing.border.BevelBorder;

/**
 * 功能：完成登录界面
 * new：JTabbedPane选项卡的功能，里面可以用于切换JPanel
 *
 */
public class QqClientLogin extends JFrame implements ActionListener {

	private JPanel contentPane,pan_middle,pan_buttom,panTel,panEmail;
	private JTextField jtf_num;
	private JPasswordField jpf_pass;
	JLabel jlb_top,pan_m_labNum,pan_m_labPass,jlb_forget,jlb_protect;
	JButton btn_login,btn_cancel,btn_resgit,btn_clear ;
	JCheckBox jck_hide,jck_remember ;
	JTabbedPane jtp ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QqClientLogin frame = new QqClientLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QqClientLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(QqClientLogin.class.getResource("/images/qq.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 251);

		//顶部控件
		jlb_top = new JLabel();
		jlb_top.setIcon(new ImageIcon(QqClientLogin.class.getResource("/images/tou.gif")));
		
		//底部控件
		pan_buttom = new JPanel();

		btn_login= new JButton();
		btn_login.setIcon(new ImageIcon(QqClientLogin.class.getResource("/images/denglu.gif")));
		//登录验证监听
		btn_login.addActionListener(this);
		pan_buttom.add(btn_login);
		
		btn_cancel = new JButton();
		btn_cancel.setIcon(new ImageIcon(QqClientLogin.class.getResource("/images/quxiao.gif")));
		pan_buttom.add(btn_cancel);
		
		btn_resgit = new JButton();
		btn_resgit.setIcon(new ImageIcon(QqClientLogin.class.getResource("/images/xiangdao.gif")));
		pan_buttom.add(btn_resgit);
		
		
		//中部控件
		pan_middle = new JPanel(new GridLayout(3,3));
		pan_m_labNum = new JLabel("QQ\u53F7\u7801\uFF1A");
		pan_m_labNum.setHorizontalAlignment(SwingConstants.CENTER);
		pan_middle.add(pan_m_labNum);
		//输入号码
		jtf_num = new JTextField(10);
		pan_middle.add(jtf_num);
		//消除号码
		btn_clear = new JButton("");
		btn_clear.setIcon(new ImageIcon(QqClientLogin.class.getResource("/images/clear.gif")));
		pan_middle.add(btn_clear);
		
		pan_m_labPass = new JLabel("QQ\u5BC6\u7801\uFF1A");
		pan_m_labPass.setHorizontalAlignment(SwingConstants.CENTER);
		pan_middle.add(pan_m_labPass);
		//输入密码
		jpf_pass = new JPasswordField(10);
		pan_middle.add(jpf_pass);
	
		jlb_forget = new JLabel("\u5FD8\u8BB0\u5BC6\u7801");
		jlb_forget.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_forget.setForeground(Color.BLUE);
		pan_middle.add(jlb_forget);
		
		jck_hide= new JCheckBox("\u9690\u8EAB\u767B\u5F55");
		pan_middle.add(jck_hide);
		
		jck_remember = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		pan_middle.add(jck_remember);
		
		jlb_protect = new JLabel("\u7533\u8BF7\u5BC6\u7801\u4FDD\u62A4");
		jlb_protect.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_protect.setForeground(Color.BLUE);
		
		pan_middle.add(jlb_protect);
		
		//选项卡面板
		jtp= new JTabbedPane();
		jtp.add("QQ号码",pan_middle);
		panTel=new JPanel();
		jtp.add("手机号码",panTel);
		panEmail=new JPanel();
		jtp.add("电子邮件",panEmail);
		
		this.add(jlb_top,BorderLayout.NORTH);
		this.add(jtp,"Center");
		this.add(pan_buttom,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btn_login)){
			User user=new User();
			user.setUesrId(jtf_num.getText().trim());
			user.setPassword(new String(jpf_pass.getPassword()).trim());
			if(new QqClientUser().checkUser(user)){
				try {
					QqFriendList qqList=new QqFriendList(user.getUesrId());
					//先创建QqList再放入HashMap中管理
					ManageQqFriendList.addQqFriendList(user.getUesrId(), qqList);
					//向服务器发送获得好友列表的包
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(user.getUesrId()).getS().getOutputStream());
					Message m=new Message();
					m.setMesstype(MessageType.message_get_onlineFriend);
					//向服务器指明我要的好友是自己帐号的列表
					m.setSender(user.getUesrId());
					oos.writeObject(m);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"用户名或密码错误");
			}
			
		}
		
	}
}
