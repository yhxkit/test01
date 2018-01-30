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
	// 1������ //

	public GameClient10() {
		super("�ʼ� ����");


		// �⺻ ����
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
		}; // ���� ȭ��


		JPanel p_two = new JPanel(new GridLayout(2, 0)); // ���� ȭ��


		cards = new CardLayout();
		setLayout(cards);



		JLabel introLabel = new JLabel("�뷡 ���� �ʼ� ���߱�");
		introLabel.setFont(new Font("Gothic", Font.BOLD, 20));
		p_one.add(introLabel);
		introLabel.setLocation(10, 215);
		introLabel.setSize(300, 50);
		introLabel.setVisible(true);


		JLabel startroom = new JLabel("���常 ���� ����");
		p_one.add(startroom);
		startroom.setLocation(630, 205);
		startroom.setSize(100, 30);
		startroom.setVisible(true);


		// ���� ���� ��ư
		real_start = new JButton("Start");
		p_one.add(real_start);
		real_start.setLocation(630, 230);
		real_start.setSize(100, 30);
		real_start.setVisible(true);
		// ���� ��ư �� ��Ʈ ��ư �̺�Ʈ
		real_start.addActionListener(new ButtonEvent());


		chat = new JTextArea("", 8, 10); // ���� ä��â
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



		JLabel cmt = new JLabel("���� (���� ���� �� 3��)");
		p_one.add(cmt);
		cmt.setLocation(230, 210);
		cmt.setSize(200, 20);
		cmt.setVisible(true);


		question = new JTextArea("���� �ڸ�");
		p_one.add(question);
		question.setLocation(230, 230);
		question.setSize(390, 30);
		question.setEditable(false);
		question.setVisible(true);



		bt1 = new JButton("��Ʈ1");
		p_one.add(bt1);
		bt1.setEnabled(false);
		bt1.setLocation(70, 280);
		bt1.setSize(100, 30);
		bt1.setVisible(true);
		bt1.addActionListener(new ButtonEvent());


		bt2 = new JButton("��Ʈ2");
		p_one.add(bt2);
		bt2.setEnabled(false);
		bt2.setLocation(70, 320);
		bt2.setSize(100, 30);
		bt2.setVisible(true);
		bt2.addActionListener(new ButtonEvent());


		bt3 = new JButton("����");
		p_one.add(bt3);
		bt3.setEnabled(false);
		bt3.setLocation(70, 360);
		bt3.setSize(100, 30);
		bt3.setVisible(true);
		bt3.addActionListener(new ButtonEvent());



		TitledBorder tb_hint1 = new TitledBorder("2��");
		tb_hint1.setTitleJustification(tb_hint1.TRAILING);

		TitledBorder tb_hint2 = new TitledBorder("1��");
		tb_hint2.setTitleJustification(tb_hint1.TRAILING);

		TitledBorder tb_hint3 = new TitledBorder("0��");
		tb_hint3.setTitleJustification(tb_hint1.TRAILING);


		bt1_hint = new JLabel("��Ʈ 1 ������");
		p_one.add(bt1_hint);
		bt1_hint.setLocation(230, 270);
		bt1_hint.setSize(390, 40);
		bt1_hint.setBorder(tb_hint1);
		bt1_hint.setVisible(false);


		bt2_hint = new JLabel("��Ʈ 2 ������");
		p_one.add(bt2_hint);
		bt2_hint.setLocation(230, 310);
		bt2_hint.setSize(390, 40);
		bt2_hint.setBorder(tb_hint2);
		bt2_hint.setVisible(false);


		bt3_hint = new JLabel("���� ������");
		p_one.add(bt3_hint);
		bt3_hint.setLocation(230, 350);
		bt3_hint.setSize(390, 40);
		bt3_hint.setBorder(tb_hint3);
		bt3_hint.setVisible(false);


		JLabel answer_lb = new JLabel("���� �Է�ĭ �� ");
		p_one.add(answer_lb);
		answer_lb.setLocation(80, 400);
		answer_lb.setSize(100, 30);
		answer_lb.setVisible(true);


		answer = new JTextField("", 10); // ���� �亯 �ϴ� ��
		p_one.add(answer);
		answer.setLocation(230, 400);
		answer.setSize(390, 30);
		answer.setEditable(false);
		answer.setVisible(true);
		// ���ͷ� ���� �Է�
		answer.addKeyListener(new EnterSend());


		submit = new JButton("���� ����");
		p_one.add(submit);
		submit.setLocation(630, 400);
		submit.setSize(100, 30);
		submit.setVisible(true);
		// ���� ���� ��ư > Textarea ����
		submit.addActionListener(new SubmitSend());


		// ������ ����Ʈ
		model = new DefaultListModel();
		JList clist = new JList(model);
		JScrollPane clistpane = new JScrollPane();
		clistpane.setViewportView(clist);

		p_one.add(clistpane);
		clistpane.setLocation(560, 2);
		clistpane.setSize(150, 200);
		clistpane.setVisible(true);


		// ���� ������ ������ ��ȯ
		JButton game_end = new JButton("���� ����");
		p_one.add(game_end);
		game_end.setLocation(713, 2);
		game_end.setSize(90, 30);
		game_end.setVisible(true);
		game_end.addActionListener(this);




		// ///////////////////////////////////////////////////////
		// 2������ //

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



		JLabel score = new JLabel("����");
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
				"�������������������������������������������������������� ���� ��ư �� ������������������������������������������������������");
		p_two_2.add(end, BorderLayout.CENTER);


		exit = new JButton("������");
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
				// ������ ���� ����
				out.println("STARTPRESSED");
				//				real_start.setEnabled(false); // ���� ��ư ������ ��Ȱ��ȭ
				// bt1.setEnabled(true); // �����ؾ� ��Ʈ Ȱ��ȭ
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
				// �׸��� �� ��ư ������ ���� ������ ����
			}

		}

	}


	private String getServerAddress() {
	

				return JOptionPane.showInputDialog(this, "���� IP �Է�:",
							"Welcome to the Chatter", -1);
			// JOptionPane.QUESTION_MESSAGE); //int ������ �����Ÿ޼��� = 3 �Ϲݸ޼���= -1

		
	}

	// �г��� ��û�ϰ� ����..
	private String getName1() {

	
				return JOptionPane.showInputDialog(this, "����Ͻ� �г����� �Է�:", "�г��� ����",
						JOptionPane.PLAIN_MESSAGE); // -1�� ��ü ����
	}


	private void run() throws IOException {

		// �����ϰ� ��Ʈ�� ����
		String serverAddress = getServerAddress();

		Socket socket = new Socket(serverAddress, 9001);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);


		while (true) {

			String line = in.readLine();

			if (line.startsWith("�г����Է�~~~")) {
				out.println(getName1());

			} else if (line.startsWith("�г��Ӽ����Ϸ�~~~")) {
				answer.setEditable(true); // �г��Ӽ����Ǿ�� Ÿ���ΰ���
				chat.setEditable(false);

			} else if (line.startsWith("MESSAGE")) {
				chat.append(line.substring(8) + "\n"); // ä��â�� �ߴ� ��ȭ���� ����
				// MESSAGE �Ⱥ��̰� ó���ϰ� ������
				// ����0

			} else if (line.startsWith("GETSETREADY")) {
				out.println("---������ ������ �����߽��ϴ�---");

			} else if (line.startsWith("QUIZ")) { // ������ ������ ������ �ϳ� ���� ���ϱ� ������
				// Ŭ���̾�Ʈó�� �Է��� �شٸ�...?
				question.setText(line.substring(5)); // "QUIZ "�������� �ٿ���
				out.println("POINTRESET");

			} else if (line.startsWith("CMT")) { // ~~ ���� �� ���� �迭�� ������ �ε��� 0��°��
				// ����, 1��°�� �ڸ�Ʈ, ~
				question.append(line.substring(4)); // "CMT "...���� ���������Ʈ �ٰ� �ƴϸ�
				// �׳� ����õ�� �ٿ�����

			} else if (line.startsWith("HINT1")) { // "HINT1" ~���� �󺧿� ������
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

			} else if (line.startsWith("CONNECTED")) { // "CONNECTED" ������
				// ����Ʈ~���±���
				model.addElement(line.substring(10) + "\n");

			} else if (line.startsWith("RECONNECTED")) { // ������ ����
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
