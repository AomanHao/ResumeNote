package com.hao.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame {

	private String username = null;
	private String fileNames = null;
	private AdaptivePanel panel;
	// 账号
	private JLabel user_account = new JLabel("账号：");
	private JTextField acc_text = new JTextField();
	// 密码
	private JLabel user_password = new JLabel("密码：");
	private JPasswordField pw_text = new JPasswordField();
	// 按钮
	private JButton but_register = new JButton("注册");
	private JButton but_login = new JButton("登陆");

	public static  int pd = 0;
	private String acc_str, pw_str;
	Font font_acc = new Font("楷体", 1, 30);
	Font font_pw = new Font("楷体", 1, 30);
	Font font_but = new Font("黑体", 2, 30);
//	private Container contentPane = this.getContentPane();
	public String getUserName(){
    	return username;
    }
	
	public Login() {
		this.setSize(400, 267);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setIconImage(new ImageIcon("note.png").getImage());// 图标图像
		this.setTitle("用户登陆");
		this.setResizable(false);// 固定窗体大小
		// 注册
		but_register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pd = 1;
				acc_str = acc_text.getText();
				pw_str = pw_text.getText();
				String filename = "user.dat";
				try {
					// 写入用户信息文件 IO流操作
					FileWriter fwrite = new FileWriter(filename, true);
					BufferedWriter bw = new BufferedWriter(fwrite);
					bw.write("ID:" + acc_str + "&&" + "password" + pw_str);
					bw.newLine();
					bw.close();
					fwrite.close();

				} catch (Exception e2) {
				}
				acc_text.setText("");
				pw_text.setText("");
				JOptionPane.showMessageDialog(null, "账户" + acc_str + "注册成功! ");
			}
		});
		// 登陆
		but_login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(pd==1||pd==0) {
				File filename = new File("user.dat");
				try {
					FileReader fread = new FileReader(filename);
					BufferedReader br = new BufferedReader(fread);
					String s = null;
					boolean judge = false;
					// 判断用户名密码是否存在
					while ((s = br.readLine()) != null) {
						StringBuilder sb = new StringBuilder();
						sb.append("ID:").append(acc_text.getText()).append("&&").append("password:")
								.append(pw_text.getText());
						String log_str = sb.toString();
						// 存在
						if (s.equals(log_str)) {
							username = acc_text.getText();
							fileNames = acc_text.getText() + pw_text.getText() + ".txt";
							JOptionPane.showMessageDialog(null, "账户" + username + "登陆成功！");
							acc_text.setText("");
							pw_text.setText("");
							judge = true;

							break;
						}
					}
					fread.close();
					br.close();
					// 判断时候登陆成功
					if (judge == false) {
						JOptionPane.showMessageDialog(null, "登陆失败！检查账号密码或注册后登陆！", null, JOptionPane.ERROR_MESSAGE,
								null);
						acc_text.setText("");
						pw_text.setText("");
					}
					// 登陆成功，数据传到主页面
					if (judge == true) {
						new UserPanel(username, fileNames);
						dispose();
						return;
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "系统错误" + e2, null, JOptionPane.ERROR_MESSAGE, null);
				}
			}
		}});
		init();

		panel = new AdaptivePanel();
		// 将所有的组件添加到panel面板中
		panel.add(user_account);
		panel.add(user_password);
		panel.add(acc_text);
		panel.add(pw_text);
		panel.add(but_register);
		panel.add(but_login);
		panel.setLayout(null);
	}

	private void init() {
		user_account.setSize(200, 100);
		user_account.setLocation(50, 20);
		user_account.setFont(font_acc);
		user_password.setSize(200, 100);
		user_password.setLocation(50, 80);
		user_password.setFont(font_pw);
		acc_text.setSize(190, 30);
		acc_text.setLocation(160, 55);
		pw_text.setSize(190, 30);
		pw_text.setLocation(160, 115);
		but_register.setSize(120, 40);
		but_register.setLocation(60, 180);
		but_login.setSize(120, 40);
		but_login.setLocation(230, 180);
		but_register.setFont(font_but);
		but_login.setFont(font_but);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		new Login().setVisible(true);
	}

}
