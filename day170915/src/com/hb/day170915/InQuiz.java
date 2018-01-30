package com.hb.day170915;

import java.io.*;
import java.util.*;

public class InQuiz extends Thread { //퀴즈 랜덤 문제로 만들어서 뿌리는 스레드 클래스

	
	File resource;
	File random;

	FileReader fr;
	FileWriter fw;

	ArrayList<String[]> qzlist = new ArrayList<String[]>();//문제 리소스 파일

	HashSet<String> rndset = new HashSet<String>(); //랜덤 해쉬
	ArrayList<String[]> rndlist = new ArrayList<String[]>(); //랜덤 리스트

	static String answer;

	public InQuiz(){ //생성자
		resource = new File("./dbdb.txt"); //문제 리소스 파일 경로
		random = new File("./randb.txt"); //랜덤 파일 경로

		if(resource.exists()&&resource.length()>0){ //db 존재하는지 확인 

			String msg ="";
			char[] cbuf = new char[1024];

			try {
				fr = new FileReader(resource);

				while(true){	//문제 리소스 읽어와서
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
			String[] lines = msg.split("\n"); //문제 리소스 한줄단위로 

			for(int i=0; i<lines.length; i++){ //문제 리소스 배열로 나누기
				String[] line =lines[i].split(":");
				String[] text = new String[5];

				for(int j=0; j<text.length; j++){
					text[j] = (String)line[j];
				}
				qzlist.add(text);
			}

			///////////////////////////		문제 랜덤으로 만들기
			if(random.exists()){
				while(rndset.size()<5){ //문제는 5개까지
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
					fw = new FileWriter(random);//랜덤 문제 파일에 쓰기

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

				msg ="";//메세지 초기화~

				try {
					fr = new FileReader(random); //랜덤 문제 읽어오기
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

				lines = msg.split("\n"); //문제 리소스 한줄단위로

				for(int i=0; i<lines.length; i++){
					String[] line =lines[i].split(":");
					String[] text = new String[5];

					for(int j=0; j<text.length; j++){
						text[j] = (String)line[j];
					}
					
					rndlist.add(text);
			
				}

				String[] game_end = new String[]{"게임이 종료되었습니다","","","",""};//
				rndlist.add(game_end);//
				
			}else{
				try {
					random.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			System.out.println("db확인");
		}
	}

	@Override
	public void run(){ 

		//쓰레드 실행되면 문제 한줄씩 읽어와서~ 그 줄의 배열 5개를 뿌려버림

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

