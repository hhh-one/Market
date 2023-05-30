package myPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.MyHomeDAO;
import vo.MyHomeVO;

public class HomePanel extends JPanel {
	private MyHomeVO vo = new MyHomeDAO().getHome("AAA");
	
	private JLabel Name;
	private JLabel E_Mail;
	private JLabel P_Number;
	private JLabel label;
	
	private JButton btnLike;
	private JButton btnSell;
	private JButton btnBuy;
	private JButton btnInfo;
	
	private JButton LikeBtn = new JButton("❤️ 찜 목록");
	private JButton SellBtn = new JButton("📃 판매 내역");
	private JButton BuyBtn = new JButton("👜 구매 내역");
	private JButton FinancialBtn = new JButton("📕 가계부");
	private JButton InfoBtn = new JButton("⚙️");
	
	private Font f1,f2;
	
	public HomePanel() {
		f1 = new Font("SanSerif", Font.BOLD,25);
		f2 = new Font("Dialog", Font.BOLD, 15);
		// 레이아웃 설정
		setBackground(Color.white);
		setSize(400, 600);
		setLayout(null);
		
		Name = new JLabel(vo.getName());
		E_Mail = new JLabel("e mail: " + vo.getE_mail());
		P_Number = new JLabel("전화번호: " + vo.getPhone_number());
		label = new JLabel("나의 거래");
		
		Name.setFont(f1);
		Name.setHorizontalAlignment(JLabel.LEFT);
		
		label.setFont(f2);
		label.setHorizontalAlignment(JLabel.LEFT);
		
		Name.setBounds(40, 50, 300, 30);
		E_Mail.setBounds(40, 90, 500, 30);
		P_Number.setBounds(40, 120, 500, 30);
		label.setBounds(20, 170, 300, 20);
		
		add(Name);
		add(label);
		add(E_Mail);
		add(P_Number);
		
		btnLike = LikeBtn;
		setBtn(btnLike);
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout Layout = (CardLayout) getParent().getLayout();
				Layout.show(getParent(), "Like");
			}
		});
		btnLike.setBounds(35, 250, 130, 30);
		add(btnLike);
		
		btnSell = SellBtn;
		setBtn(btnSell);
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout Layout = (CardLayout) getParent().getLayout();
				Layout.show(getParent(), "Sell");
			}
		});
		btnSell.setBounds(35, 310, 130, 30);
		add(btnSell);
		
		btnBuy = BuyBtn;
		setBtn(btnBuy);
		btnBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout Layout = (CardLayout) getParent().getLayout();
				Layout.show(getParent(), "Buy");
			}
		});
		btnBuy.setBounds(35, 370, 130, 30);
		add(btnBuy);
		
		/*
		JButton btnCount = FinancialBtn;
		setBtn(btnCount);
		btnCount.setBounds(35, 430, 130, 30);
		add(btnCount);
		*/
		btnInfo = InfoBtn;
		setBtn(btnInfo);
		btnInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout Layout = (CardLayout) getParent().getLayout();
				Layout.show(getParent(), "Info");
			}
		});
		btnInfo.setBounds(322, 10, 70, 70);
		add(btnInfo);

	}
	
	private void setBtn(JButton btn) {
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false); 
		btn.setFocusPainted(false);
	}
	
}
