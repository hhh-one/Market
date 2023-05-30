package frame;

import java.awt.CardLayout;

import javax.swing.*;

import dao.MyHomeDAO;
import vo.MyHomeVO;
import myPanel.*;

public class MyFrame extends JFrame {
	
	public MyHomeVO vo = new MyHomeDAO().getHome("AAA");
	
	public MyFrame() {
		setSize(400, 600);
		setLocationRelativeTo(null); // 화면 가운데에서 뜨도록 설정
		setResizable(false); // 사이즈 조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 각 패널을 추가
		HomePanel home = new HomePanel();
		InfoPannel info = new InfoPannel();
		MailChangePanel mail = new MailChangePanel();
		NumberChangePanel number = new NumberChangePanel();
		
		// ContentPane을 카드 레이아웃으로 설정하고, 각 패널을 추가
		getContentPane().setLayout(new CardLayout());
		getContentPane().add(home, "MyMain");
		getContentPane().add(info, "Info");
		getContentPane().add(mail, "Mail");
		getContentPane().add(number, "Number");
		
		// 처음 보여줄 패널을 MyMain으로 설정
		CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
		cardLayout.show(getContentPane(), "MyMain");
	}
	
}
