package com.hb.day170915;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

public class GameClient10 extends JFrame implements ActionListener {

	CardLayout cards;

	JButton exit;
	JButton submit;
	JButton bt1;
	JButton bt2;
	JButton bt3;
	JButton real_start;

	JLabel bt1_hint;
	JLabel bt2_hint;
	JLabel bt3_hint;

	JTextArea question;

	JTextArea chat;
	JTextField answer;

	DefaultListModel model;

	JTextArea score_area;

	BufferedReader in;
	PrintWriter out;




	// ///////////////////////////////////////////////////////
	// UI
	// 1페이지 //

	public GameClient10() {
		super("초성 게임");


		// 기본 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(810, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);


		ImageIcon iconOne = new ImageIcon("han1.jpg");
		ImageIcon iconTwo = new ImageIcon("p2_1.jpg");
		ImageIcon iconThree = new ImageIcon("p2_2.jpg");



		JPanel p_one = new JPanel(null) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(iconOne.getImage(), 0, 0, d.width, d.height, null);
			}
		}; // 메인 화면


		JPanel p_two = new JPanel(new GridLayout(2, 0)); // 종료 화면


		cards = new CardLayout();
		setLayout(cards);



		JLabel introLabel = new JLabel("노래 제목 초성 맞추기");
		introLabel.setFont(new Font("Gothic", Font.BOLD, 20));
		p_one.add(introLabel);
		introLabel.setLocation(10, 215);
		introLabel.setSize(300, 50);
		introLabel.setVisible(true);


		JLabel startroom = new JLabel("방장만 시작 가능");
		p_one.add(startroom);
		startroom.setLocation(630, 205);
		startroom.setSize(100, 30);
		startroom.setVisible(true);


		// 게임 시작 버튼
		real_start = new JButton("Start");
		p_one.add(real_start);
		real_start.setLocation(630, 230);
		real_start.setSize(100, 30);
		real_start.setVisible(true);
		// 시작 버튼 및 힌트 버튼 이벤트
		real_start.addActionListener(new ButtonEvent());


		chat = new JTextArea("", 8, 10); // 정답 채팅창
		p_one.add(chat);
		chat.setLocation(2, 2);
		chat.setSize(550, 200);
		chat.setVisible(true);
		chat.setLineWrap(true);


		JScrollPane sp = new JScrollPane(chat,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p_one.add(sp);
		sp.setLocation(2, 2);
		sp.setSize(550, 200);
		sp.setVisible(true);
		DefaultCaret caret = (DefaultCaret) chat.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);



		JLabel cmt = new JLabel("문제 (정답 맞출 때 3점)");
		p_one.add(cmt);
		cmt.setLocation(230, 210);
		cmt.setSize(200, 20);
		cmt.setVisible(true);


		question = new JTextArea("문제 자리");
		p_one.add(question);
		question.setLocation(230, 230);
		question.setSize(390, 30);
		question.setEditable(false);
		question.setVisible(true);



		bt1 = new JButton("힌트1");
		p_one.add(bt1);
		bt1.setEnabled(false);
		bt1.setLocation(70, 280);
		bt1.setSize(100, 30);
		bt1.setVisible(true);
		bt1.addActionListener(new ButtonEvent());


		bt2 = new JButton("힌트2");
		p_one.add(bt2);
		bt2.setEnabled(false);
		bt2.setLocation(70, 320);
		bt2.setSize(100, 30);
		bt2.setVisible(true);
		bt2.addActionListener(new ButtonEvent());


		bt3 = new JButton("정답");
		p_one.add(bt3);
		bt3.setEnabled(false);
		bt3.setLocation(70, 360);
		bt3.setSize(100, 30);
		bt3.setVisible(true);
		bt3.addActionListener(new ButtonEvent());



		TitledBorder tb_hint1 = new TitledBorder("2점");
		tb_hint1.setTitleJustification(tb_hint1.TRAILING);

		TitledBorder tb_hint2 = new TitledBorder("1점");
		tb_hint2.setTitleJustification(tb_hint1.TRAILING);

		TitledBorder tb_hint3 = new TitledBorder("0점");
		tb_hint3.setTitleJustification(tb_hint1.TRAILING);


		bt1_hint = new JLabel("힌트 1 보여줌");
		p_one.add(bt1_hint);
		bt1_hint.setLocation(230, 270);
		bt1_hint.setSize(390, 40);
		bt1_hint.setBorder(tb_hint1);
		bt1_hint.setVisible(false);


		bt2_hint = new JLabel("힌트 2 보여줌");
		p_one.add(bt2_hint);
		bt2_hint.setLocation(230, 310);
		bt2_hint.setSize(390, 40);
		bt2_hint.setBorder(tb_hint2);
		bt2_hint.setVisible(false);


		bt3_hint = new JLabel("정답 보여줌");
		p_one.add(bt3_hint);
		bt3_hint.setLocation(230, 350);
		bt3_hint.setSize(390, 40);
		bt3_hint.setBorder(tb_hint3);
		bt3_hint.setVisible(false);


		JLabel answer_lb = new JLabel("정답 입력칸 ▶ ");
		p_one.add(answer_lb);
		answer_lb.setLocation(80, 400);
		answer_lb.setSize(100, 30);
		answer_lb.setVisible(true);


		answer = new JTextField("", 10); // 문제 답변 하는 곳
		p_one.add(answer);
		answer.setLocation(230, 400);
		answer.setSize(390, 30);
		answer.setEditable(false);
		answer.setVisible(true);
		// 엔터로 정답 입력
		answer.addKeyListener(new EnterSend());


		submit = new JButton("정답 제출");
		p_one.add(submit);
		submit.setLocation(630, 400);
		submit.setSize(100, 30);
		submit.setVisible(true);
		// 정답 제출 버튼 > Textarea 전송
		submit.addActionListener(new SubmitSend());


		// 접속자 리스트
		model = new DefaultListModel();
		JList clist = new JList(model);
		JScrollPane clistpane = new JScrollPane();
		clistpane.setViewportView(clist);

		p_one.add(clistpane);
		clistpane.setLocation(560, 2);
		clistpane.setSize(150, 200);
		clistpane.setVisible(true);


		// 게임 끝나면 페이지 전환
		JButton game_end = new JButton("게임 종료");
		p_one.add(game_end);
		game_end.setLocation(713, 2);
		game_end.setSize(90, 30);
		game_end.setVisible(true);
		game_end.addActionListener(this);




		// ///////////////////////////////////////////////////////
		// 2페이지 //

		JPanel p_two_1 = new JPanel(null) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(iconTwo.getImage(), 0, 0, d.width, d.height, null);
			}
		};

		JPanel p_two_2 = new JPanel(new BorderLayout()) {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(iconThree.getImage(), 0, 0, d.width, d.height, null);
			}
		};

		p_two.add(p_two_1);
		p_two.add(p_two_2);



		JLabel score = new JLabel("점수");
		p_two_1.add(score);
		score.setFont(new Font("Gothic", Font.BOLD, 20));
		score.setBounds(380, 60, 80, 30);
		score.setVisible(true);


		score_area = new JTextArea("", 8, 10);
		Font font = new Font("Gothic", Font.BOLD, 30);
		score_area.setFont(font);
		p_two_1.add(score_area);
		score_area.setEditable(false);
		score_area.setBounds(210, 100, 400, 130);
		score_area.setVisible(true);


		JScrollPane score_sp = new JScrollPane(score_area,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p_two_1.add(score_sp);
		score_sp.setBounds(210, 100, 400, 130);
		score_sp.setVisible(true);
		DefaultCaret score_caret = (DefaultCaret) score_area.getCaret();
		score_caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		// ALWAYS_UPDATE


		JLabel end = new JLabel(
				"　　　　　　　　　　　　　　　　　　　　　　　　　　　　 종료 버튼 ▼ 　　　　　　　　　　　　　　　　　　　　　　　　　　　");
		p_two_2.add(end, BorderLayout.CENTER);


		exit = new JButton("나가기");
		p_two_2.add(exit, BorderLayout.SOUTH);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exit) {
					System.exit(0);
				}
			}
		});


		// //////////////////////////////////////////////////////


		add(p_one, "p1");
		add(p_two, "p2");


	}


	public void showTotal() {
		cards.show(getContentPane(), "p2");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		showTotal();
	}


	class EnterSend implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyChar() == '\n') {
				if (!answer.getText().isEmpty()) {
					if (chat.getText().isEmpty()) {
						out.println(answer.getText());
					} else {
						out.println(answer.getText());
					}
				}
				answer.setText("");
			}
		}

	}


	class SubmitSend implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit && !(answer.getText().equals(""))) {
				if (chat.getText().equals("")) {
					out.println(answer.getText());
					answer.setText("");
				} else {
					out.println(answer.getText());
					answer.setText("");
				}
			}

		}

	}


	class ButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Start")) {
				// 서버로 문자 전송
				out.println("STARTPRESSED");
				//				real_start.setEnabled(false); // 시작 버튼 누르면 비활성화
				// bt1.setEnabled(true); // 시작해야 힌트 활성화
			}

			if (e.getSource() == bt1) {
				out.println("MINUSONEP");
				bt1_hint.setVisible(true);
				bt2.setEnabled(true);
			}
			if (e.getSource() == bt2) {
				out.println("MINUSTWOP");
				bt2_hint.setVisible(true);
				bt3.setEnabled(true);
			}
			if (e.getSource() == bt3) {
				out.println("MINUSTHREEP");
				bt3_hint.setVisible(true);
				// 그리고 이 버튼 누르면 점수 오르지 않음
			}

		}

	}


	private String getServerAddress() {
	

				return JOptionPane.showInputDialog(this, "서버 IP 입력:",
							"Welcome to the Chatter", -1);
			// JOptionPane.QUESTION_MESSAGE); //int 값으로 퀘스쳔메세지 = 3 일반메세지= -1

		
	}

	// 닉네임 요청하고 리턴..
	private String getName1() {

	
				return JOptionPane.showInputDialog(this, "사용하실 닉네임을 입력:", "닉네임 설정",
						JOptionPane.PLAIN_MESSAGE); // -1로 대체 가능
	}


	private void run() throws IOException {

		// 연결하고 스트림 선언
		String serverAddress = getServerAddress();

		Socket socket = new Socket(serverAddress, 9001);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);


		while (true) {

			String line = in.readLine();

			if (line.startsWith("닉네임입력~~~")) {
				out.println(getName1());

			} else if (line.startsWith("닉네임설정완료~~~")) {
				answer.setEditable(true); // 닉네임설정되어야 타이핑가능
				chat.setEditable(false);

			} else if (line.startsWith("MESSAGE")) {
				chat.append(line.substring(8) + "\n"); // 채팅창에 뜨는 대화라인 위의
				// MESSAGE 안보이게 처리하고 끝나면
				// 엔터0

			} else if (line.startsWith("GETSETREADY")) {
				out.println("---방장이 게임을 시작했습니다---");

			} else if (line.startsWith("QUIZ")) { // 문제는 어차피 스레드 하나 따로 쓰니까 문제도
				// 클라이언트처럼 입력을 준다면...?
				question.setText(line.substring(5)); // "QUIZ "서버에서 붙여서
				out.println("POINTRESET");

			} else if (line.startsWith("CMT")) { // ~~ 문제 한 열을 배열로 나눠서 인덱스 0번째는
				// 퀴즈, 1번째는 코멘트, ~
				question.append(line.substring(4)); // "CMT "...따로 출력컴포넌트 줄거 아니면
				// 그냥 퀘스천에 붙여버려

			} else if (line.startsWith("HINT1")) { // "HINT1" ~각각 라벨에 셋해줌
				bt1_hint.setText(line.substring(5));
				bt1_hint.setVisible(false);
				bt1.setEnabled(true);

			} else if (line.startsWith("HINT2")) { // "HINT2"
				bt2_hint.setText(line.substring(5));
				bt2_hint.setVisible(false);
				bt2.setEnabled(false);

			} else if (line.startsWith("HINT3")) { // "HINT3"
				bt3_hint.setText(line.substring(5));
				bt3_hint.setVisible(false);
				bt3.setEnabled(false);

			} else if (line.startsWith("TOTHIRDPAGE")) {
				out.println("TOTHIRDPAGE");
				showTotal();

			} else if (line.startsWith("SCORE")) {
				score_area.append(line.substring(6) + "\n");

			} else if (line.startsWith("CONNECTED")) { // "CONNECTED" 접속자
				// 리스트~상태까지
				model.addElement(line.substring(10) + "\n");

			} else if (line.startsWith("RECONNECTED")) { // 접속자 갱신
				model.clear();
			}

			bt1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					bt1_hint.setVisible(true);
					bt2.setEnabled(true);
				}
			});

			bt2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					bt2_hint.setVisible(true);
					bt3.setEnabled(true);
				}
			});
			bt3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					bt3_hint.setVisible(true);
				}
			});

		}

	}

	public static void main(String[] args) throws Exception {
		GameClient10 client = new GameClient10();
		client.run();
	}

}
