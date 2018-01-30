package com.hb.day170915;

import java.io.*;
import java.util.*;
import java.net.Socket;



public class Handler extends Thread { 

	public static boolean resetpoint = false;
	int point=3;
	int score=0;

	boolean listen;

	private InGameServer server;

	private String name; //Ŭ���̾�Ʈ �̸�
	private Socket socket; //Ŭ���̾�Ʈ ����

	private BufferedReader in; 
	private PrintWriter out;

	//������. ���� ���ϸ� ����
	public Handler(Socket socket, InGameServer server) { 
		this.socket = socket;
		this.server = server;
	}//Ŭ���̾�Ʈ ���� ����

	//�ߺ����� ���� �г��� ���ö����� ��� â�߰�... 
	//�г��� �ް���> �ƿ�ǲ��Ʈ�� ��� > �Է°��޾Ƽ� �Ѹ��� �ݺ�
	public void run() {
		try {
			System.out.println("Ŭ���̾�Ʈ ��ü ������");

			//���Ͽ��� �� ���� ��Ʈ�� ����
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true); //�����÷���

			//Ŭ���̾�Ʈ���׼� �г��� �ޱ�. �ߺ����� ���� �̸����������� ��� ��
			while (true) {
				out.println("�г����Է�~~~"); //Ŭ���̾�Ʈ�� �г����Է��϶�� �����

				name = in.readLine();

				if(name == null) {return;}//���� �г���ġ�� ���� â�� �������� null���̸� ��������
				if(name.trim().length()<1){continue;}//�г��� ��������� ����

				synchronized (server.names) { //����ȭ => �������� Ŭ���̾�Ʈ�� ���ÿ� ���� ��� names���� �Ӱ迵��=>�� ����ȭ�� ���
					if (!server.names.contains(name)) { //�̸��� �ߺ����� �ʾƾ� names �¿� ����
						server.names.add(name);
						if(server.names.size() == 1 ){server.roommaster = name;}

						System.out.println(server.names.size());

						break;
					}
				}


			}
			//�̸��� ���������� ���� ����Ʈ �����͸� �¿� �־��� => ��ü �޼��� �Ѹ��� �޾ƺ��� �ϴϱ�
			server.writers.add(out); //out = ����Ʈ������> writers �¿� �־���

			/////////////////////////////////////����� ������ ����Ʈ ����(������ �ݺ� ��-�޼���� �Ȼ���..)


			for (PrintWriter writer : server.writers) {
				writer.println("RECONNECTED ");
				writer.println("CONNECTED "+"������ �� > "+server.names.size());
			}
			for(String cntname : server.names){
				for (PrintWriter writer : server.writers) {
					if(cntname == server.roommaster){
						writer.println("CONNECTED " + "<����>" + cntname);
					}else{
						writer.println("CONNECTED " + cntname);
					}
				}
			}
			/////////////////////////////////////////


			out.println("�г��Ӽ����Ϸ�~~~"); //�̸� ������ > �˾�
			System.out.println(name+" ����");
			for (PrintWriter writer : server.writers) {
				writer.println("MESSAGE " + name + "���� �����ϼ̽��ϴ�");
			}

			server.names_scores.put(name, score); //�����ϸ� ��ũ�� ��



			//���� ���� ����
			while(!server.q.isAlive()){

				String input = in.readLine();

				if (input == null) {
					return;
				}else if (input.equals("---������ ������ �����߽��ϴ�---")){
					break;
				}else if(input.equals("STARTPRESSED")){
					if(name == server.roommaster){
						server.q.start();

						for (PrintWriter writer : server.writers){
							writer.println("GETSETREADY");
						}
						break;
					} else {continue;}
				}else{

					for (PrintWriter writer : server.writers){
						writer.println("MESSAGE " + name + ": " + input);
					}
				}

			}
			//���ӽ���!

			listen = server.q.isAlive();
			// �޼��� �޾Ƽ� Ŭ���̾�Ʈ ��ü�� �Ѹ���	

			while (listen) {

				String input = in.readLine();
				String inp = input.replaceAll(" ", "").trim(); 


				// �Է����� ���� �ٸ� Ŭ���̾�Ʈ���� �׳� ������.. �Է��� Ŭ���̾�Ʈ �͸� �Ѹ�
				if (input == null) {return;}



				if (input.equals("STARTPRESSED")){
					continue; //��ŸƮ ��ư ������ �ƹ��ϵ� �Ͼ�� �ʴ´�

				}else if(input.startsWith("MINUSONEP")){ 
					point=2;
					continue;
				}else if(input.startsWith("MINUSTWOP")){
					point=1;
					continue;
				}else if(input.startsWith("MINUSTHREEP")){
					point=0;
					continue;
				} else if(input.equals("POINTRESET")){
					point=3;
				} else if(input.equals("TOTHIRDPAGE")){ 

					if(name == server.roommaster){

						for(String name : server.names){
							for (PrintWriter writer : server.writers){
								writer.println("SCORE " + name +"�� ����("+ server.names_scores.get(name)+"��)");
							}
						}
					}
				} else {

					//�Է¹��� ���� ���Ͽ� ���� �ѷ���... MESSAGE �� ���꽺Ʈ�� �̿��ؼ� Ŭ���̾�Ʈ ������ ó��
					for (PrintWriter writer : server.writers) {
						writer.println("MESSAGE " + name + ": " + input);}

					String ans = server.q.answer.trim().replaceAll(" ", "");


					if(inp.equalsIgnoreCase(ans)){  
						score += point;
						for (PrintWriter writer : server.writers) {
							writer.println("MESSAGE " + name + "�� ���� : " + input + "(" + point +"��)"); //�����ڸ��� �ѷ���
							writer.println("MESSAGE " + name + "�� ���� (" + score +"��)");
						}
						server.names_scores.replace(name, score);
						server.q.interrupt();	//���ͷ�Ʈ�� sleep���¿� �ͼ��� �����Ѽ� �ٷ� ���� ������ �Ѿ
						//							System.out.println("����ó��"+runnum);
						//							runnum++;	
					}	

				}

			}

		} catch (IOException e) {
			System.out.println(e);
			for (PrintWriter writer :server. writers) {
				writer.println("MESSAGE " + name + "���� �����ϼ̽��ϴ�");
			}				
			System.out.println(name+" ����");

		} finally {
			//Ŭ���̾�Ʈ�� �������� �̸�/����Ʈ����Ʈ�� �¿��� ����� ���� ����
			if (name != null) {server.names.remove(name);}
			if (out != null) {server.writers.remove(out);}

			server.names_scores.remove(name); //���� ����� �ƿ� ��ũ���� ������(���� ������ �湮�� ������ �ȵǹǷ�)

			/////////////////////////////////////����� ������ ����Ʈ ����

			if(name == server.roommaster){server.roommaster = server.names.get(0);}

			for (PrintWriter writer : server.writers) {
				writer.println("RECONNECTED ");
				writer.println("CONNECTED "+"������ �� > "+ server.names.size());
			}
			for(String cntname : server.names){
				for (PrintWriter writer :server. writers) {
					if(cntname == server.roommaster){
						writer.println("CONNECTED " + "<����>" + cntname);
					}else{
						writer.println("CONNECTED " + cntname);
					}
				}
			}

			/////////////////////////////////////////	

			try {
				socket.close();
			} catch (IOException e) {
			}
		}

	}
}
