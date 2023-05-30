package frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.MainDAO;
import vo.MainVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private MainDAO mainDAO;
    private JFrame postFrame; // "Post" 프레임을 저장하기 위한 멤버 변수 선언

    public MainFrame(MainDAO boardDAO) {
        this.mainDAO = boardDAO;

        setTitle("메인 페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null); // 화면 중앙에 프레임 배치

        model = new DefaultTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 하단에 버튼을 담을 패널 생성
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // "글쓰기" 버튼 추가
        
        JButton writeButton = new JButton("글쓰기");
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWriteFrame();
            }
        });
        buttonPanel.add(writeButton);

        // "마이페이지" 버튼 추가
        
        JButton myPageButton = new JButton("마이페이지");
        myPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMyPageFrame();
            }
        });
        buttonPanel.add(myPageButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        fetchDataFromDatabase();

        setVisible(true);
    }

    private void fetchDataFromDatabase() {
        List<MainVO> boards = mainDAO.getAllBoards();

        // 기존의 테이블 데이터 초기화
        model.setColumnCount(0);
        model.setRowCount(0);

        // 테이블 열 이름 설정
        model.addColumn("상품번호");
        model.addColumn("판매자");
        model.addColumn("상품명");
        model.addColumn("상품설명");
        model.addColumn("가격");
        model.addColumn("등록일");
        model.addColumn("판매여부");

        // 테이블 데이터 가져와서 표시
        for (MainVO board : boards) {
            Object[] rowData = {
                    board.getBOARD_NUM(),
                    board.getACCOUNT_ID(),
                    board.getPRODUCT_NAME(),
                    board.getPRODUCT_COUNT(),
                    board.getPRICE(),
                    board.getBOARD_DATE(),
                    board.getPRODUCT_SELL()
            };
            model.addRow(rowData);
        }

        // 테이블에 대한 액션 리스너 추가
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                openPostFrame(boards.get(selectedRow));
            }
        });
    }

    private void openWriteFrame() {
    	// 글 작성을 위한 새로운 프레임 생성
        JFrame writeFrame = new JFrame();
        writeFrame.setTitle("글쓰기");
        writeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        writeFrame.setSize(400, 300);
        writeFrame.setLocationRelativeTo(this); // BoardFrame을 기준으로 프레임을 중앙에 배치

     // 글 작성을 위한 필요한 컴포넌트 추가, 예: 텍스트 필드, 레이블, 버튼 등

        writeFrame.setVisible(true);
    }

    private void openMyPageFrame() {
    	// 사용자 프로필 또는 "마이페이지"를 위한 새로운 프레임 생성
    	
        JFrame myPageFrame = new JFrame();
        myPageFrame.setTitle("마이 페이지");
        myPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myPageFrame.setSize(400, 300);
        myPageFrame.setLocationRelativeTo(this); // BoardFrame을 기준으로 프레임을 중앙에 배치

     // 사용자 프로필을 위한 필요한 컴포넌트 추가, 예: 레이블, 버튼 등

        myPageFrame.setVisible(true);
    }

    private void openPostFrame(MainVO board) {
        if (postFrame == null) {
            postFrame = new JFrame();
            postFrame.setTitle("글보기");
            postFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            postFrame.setSize(400, 300);
            postFrame.setLocationRelativeTo(this); // BoardFrame을 기준으로 프레임을 중앙에 배치
        }

        //  // 기존 프레임의 컴포넌트 제거
        postFrame.getContentPane().removeAll();

        // // 선택된 게시물을 표시하기 위한 필요한 컴포넌트 추가, 예: 레이블, 버튼 등
        // 'board' 매개변수를 사용하여 선택된 게시물의 데이터로 프레임을 채움

        postFrame.revalidate();
        postFrame.repaint();
        postFrame.setVisible(true);
    }

    public static void main(String[] args) {
        String dbUrl = "jdbc:oracle:thin:@172.30.1.86:1521:XE";
        String dbUsername = "HR";
        String dbPassword = "HR";

        MainDAO boardDAO = new MainDAO(dbUrl, dbUsername, dbPassword);
        SwingUtilities.invokeLater(() -> new MainFrame(boardDAO));
    }
}
