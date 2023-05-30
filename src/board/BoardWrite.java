package board;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import vo.BoardVO;
import dao.BoardDAO;

public class BoardWrite extends JFrame implements ActionListener {
	private JLabel name;
	private JTextField productName;
	private JLabel price;
	private JTextField productPrice;
	private JTextArea content;
	private JLabel sell;
	private JComboBox comboBox;
	private JButton btn;
	private BoardVO vo;

	public BoardVO getVo() {
		return vo;
	}
	
	public void setVo(BoardVO vo) {
		this.vo = vo;
	}
	
	public BoardWrite() {
		vo = new BoardVO();
		setTitle("Market");
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Frame을 화면 가운데에 정렬
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		//컨텐트팬 - 컴포넌트 어디에 부착할지 지정
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); //전체 레이아웃

		//Panel - 제목
		JPanel namePanel = new JPanel();
		name = new JLabel("제품명");
		//		name.setPreferredSize(new Dimension(5, 5));
		productName = new JTextField(20);
		//		productName.setPreferredSize(new Dimension(5, 20));
		namePanel.add(name);
		namePanel.add(productName);

		//Panel - 가격
		JPanel pricePanel = new JPanel();
		price = new JLabel("가격");
		//		price.setPreferredSize(new Dimension(10, 20));
		productPrice = new JTextField(15);
		//		productPrice.setPreferredSize(new Dimension(10, 10));
		pricePanel.add(price);
		pricePanel.add(productPrice);

		//Panel - 내용
		JPanel contentPanel = new JPanel();
		content = new JTextArea(15, 30);
		contentPanel.add(content);

		//Panel - 판매 여부, 등록
		JPanel endPanel = new JPanel();
		sell = new JLabel("판매 여부");
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"N", "Y"}));
		btn = new JButton("등록");
		btn.setPreferredSize(new Dimension(200, 40));
		btn.addActionListener(this);
		endPanel.add(sell);
		endPanel.add(comboBox);
		endPanel.add(btn);

		c.add(namePanel);
		c.add(pricePanel);
		c.add(contentPanel);
		c.add(endPanel);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BoardDAO dao = new BoardDAO();

		if (e.getSource() == btn) {
			String product_name = productName.getText();
			vo.setProduct_name(product_name);

			String product_price = productPrice.getText();
			vo.setPrice(product_price);

			String product_sell = comboBox.getSelectedItem().toString();
			vo.setProduct_sell(product_sell);

			String product_content = content.getText();
			vo.setProduct_content(product_content);

			dao.getConnection();
			dao.writeBoard(vo);
			dao.disConnection();
		}
	}
}