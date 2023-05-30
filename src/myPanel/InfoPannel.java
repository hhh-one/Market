package myPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.MyHomeDAO;
import vo.MyHomeVO;

public class InfoPannel extends JPanel{
	private MyHomeVO vo = new MyHomeDAO().getHome("AAA");
	
	private JButton backBtn = new JButton("<");
	private JButton btnNumber;
	private JButton btnMail;
	
	private JLabel main;
	private JLabel content;
	private JLabel mail;
	private JLabel m_content;
	private JLabel number;
	private JLabel n_content;
	
	public InfoPannel() {
		setLayout(null);
		setBackground(Color.white);
		setSize(400, 600);

		main = new JLabel("계정 / 정보 관리");
		main.setHorizontalAlignment(JLabel.LEFT);
		main.setBounds(70, 20, 300, 40);
		add(main);
		
		content = new JLabel("계정 정보");
		content.setHorizontalAlignment(JLabel.LEFT);
		content.setBounds(40, 100, 70, 30);
		add(content);
		
		mail = new JLabel("이메일");
		mail.setHorizontalAlignment(JLabel.LEFT);
		mail.setBounds(40, 200, 70, 30);
		add(mail);
		
		m_content = new JLabel(vo.getE_mail());
		m_content.setHorizontalAlignment(JLabel.LEFT);
		m_content.setBounds(40, 250, 200, 20);
		add(m_content);
		// 메일 변경 버튼 
		btnMail = new JButton("변경");
		btnMail.setBounds(275, 200, 70, 30);
		btnMail.addActionListener(new ActionListener() {
			// 클릭시, 메일 변경 패널로 이동
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) getParent().getLayout();
				layout.show(getParent(), "Mail");
			}
		});
		add(btnMail);
		
		number = new JLabel("휴대폰 번호");
		number.setHorizontalAlignment(JLabel.LEFT);
		number.setBounds(40, 350, 70, 30);
		add(number);
		
		n_content = new JLabel(vo.getPhone_number());
		n_content.setHorizontalAlignment(JLabel.LEFT);
		n_content.setBounds(40, 400, 200, 20);
		add(n_content);
		// 번호 변경 버튼
		btnNumber = new JButton("변경");
		btnNumber.setBounds(275, 350, 70, 30);
		btnNumber.addActionListener(new ActionListener() {
			// 클릭시, 번호 변경 패널로 이동
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) getParent().getLayout();
				layout.show(getParent(), "Number");
			}
		});
		add(btnNumber);
		// 뒤로가기 버튼 : 클릭 시, 마이페이지 메인으로 이동
		backBtn.setBounds(10, 20, 50, 50);
		setBtn(backBtn);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) getParent().getLayout();
				layout.show(getParent(), "MyMain");
			}
		});
		
		add(backBtn);

		setVisible(false);
	}
	
	private void setBtn(JButton btn) {
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false); 
		btn.setFocusPainted(false);
	}
	
}
