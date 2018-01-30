package com.hb.day170915;

import java.io.*;
import java.util.*;

public class InQuiz extends Thread { //���� ���� ������ ���� �Ѹ��� ������ Ŭ����

	
	File resource;
	File random;

	FileReader fr;
	FileWriter fw;

	ArrayList<String[]> qzlist = new ArrayList<String[]>();//���� ���ҽ� ����

	HashSet<String> rndset = new HashSet<String>(); //���� �ؽ�
	ArrayList<String[]> rndlist = new ArrayList<String[]>(); //���� ����Ʈ

	static String answer;

	public InQuiz(){ //������
		resource = new File("./dbdb.txt"); //���� ���ҽ� ���� ���
		random = new File("./randb.txt"); //���� ���� ���

		if(resource.exists()&&resource.length()>0){ //db �����ϴ��� Ȯ�� 

			String msg ="";
			char[] cbuf = new char[1024];

			try {
				fr = new FileReader(resource);

				while(true){	//���� ���ҽ� �о�ͼ�
					int su = fr.read(cbuf);
					if(su==-1){break;}
					msg+=new String(cbuf,0,su);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String[] lines = msg.split("\n"); //���� ���ҽ� ���ٴ����� 

			for(int i=0; i<lines.length; i++){ //���� ���ҽ� �迭�� ������
				String[] line =lines[i].split(":");
				String[] text = new String[5];

				for(int j=0; j<text.length; j++){
					text[j] = (String)line[j];
				}
				qzlist.add(text);
			}

			///////////////////////////		���� �������� �����
			if(random.exists()){
				while(rndset.size()<5){ //������ 5������
					int su = (int)(Math.random()*qzlist.size());
					for(int i=0; i<qzlist.size(); i++){
						if(rndset.size()<5){
							rndset.add((String)lines[su]);
						}else{
							break;
						}
					}
				}

				try {
					fw = new FileWriter(random);//���� ���� ���Ͽ� ����

					Iterator<String> ite = rndset.iterator();
					while(ite.hasNext()){
						String tmp = ite.next(); 
						fw.write(tmp+"\n");
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				} finally {
					try {
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				msg ="";//�޼��� �ʱ�ȭ~

				try {
					fr = new FileReader(random); //���� ���� �о����
					while(true){
						int su = fr.read(cbuf);			
						if(su==-1){break;}
						msg+=new String(cbuf,0,su);						
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						fr.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				lines = msg.split("\n"); //���� ���ҽ� ���ٴ�����

				for(int i=0; i<lines.length; i++){
					String[] line =lines[i].split(":");
					String[] text = new String[5];

					for(int j=0; j<text.length; j++){
						text[j] = (String)line[j];
					}
					
					rndlist.add(text);
			
				}

				String[] game_end = new String[]{"������ ����Ǿ����ϴ�","","","",""};//
				rndlist.add(game_end);//
				
			}else{
				try {
					random.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			System.out.println("dbȮ��");
		}
	}

	@Override
	public void run(){ 

		//������ ����Ǹ� ���� ���پ� �о�ͼ�~ �� ���� �迭 5���� �ѷ�����

		for(int i=0; i<rndlist.size(); i++){
			String[] rndline = rndlist.get(i);
	
			if(i==(rndlist.size()-1)){//
				for (PrintWriter writer : InGameServer.writers) {
					
					writer.println("QUIZ " + rndline[0]);
					writer.println("CMT " + rndline[1]);
					writer.println("HINT1 " + rndline[2]);
					writer.println("HINT2 " + rndline[3]);
					writer.println("HINT3 " + rndline[4]);
					answer = (String)(rndline[4]);
				}
				try {this.sleep(3000);} catch (InterruptedException e) {}
			}//
			else{
			for (PrintWriter writer : InGameServer.writers) {
				
				writer.println("QUIZ " + rndline[0]);
				writer.println("CMT " + rndline[1]);
				writer.println("HINT1 " + rndline[2]);
				writer.println("HINT2 " + rndline[3]);
				writer.println("HINT3 " + rndline[4]);
				answer = (String)(rndline[4]);

			}
		
			try {this.sleep(12000);} catch (InterruptedException e) {}
			}
		}

//		tf = true;
		for (PrintWriter writer : InGameServer.writers) {writer.println("TOTHIRDPAGE");}

	}


}

