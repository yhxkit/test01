
package com.hb.day170918_multiroom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Game implements Serializable{

	transient public Quiz newQ;

	public Game() {
		newQ = new Quiz();
	}

	public void startQuizThread(CopyClient gamestarter){

		newQ.gamestarter = gamestarter;


		synchronized (newQ) {
			newQ.start();
		}

	}
}
///////////////////////////////////////////////////////////////////////////
class Quiz extends Thread { //

	CopyClient gamestarter;
	//Protocol prtc; //if I get the prtc from the user, it doesn't go fifth, but just first time..

	File resource;
	File random;

	FileReader fr;
	FileWriter fw;

	ArrayList<String[]> qzlist = new ArrayList<String[]>();//

	HashSet<String> rndset = new HashSet<String>(); //
	ArrayList<String[]> rndlist = new ArrayList<String[]>(); //



	public Quiz(){ //constructor
		resource = new File("./dbdb.txt"); //
		random = new File("./randb.txt"); //

		if(resource.exists()&&resource.length()>0){ //

			String msg ="";
			char[] cbuf = new char[1024];

			try {
				fr = new FileReader(resource);

				while(true){	//
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
			String[] lines = msg.split("\n"); //

			for(int i=0; i<lines.length; i++){ //
				String[] line =lines[i].split(":");
				String[] text = new String[5];

				for(int j=0; j<text.length; j++){
					text[j] = (String)line[j];
				}
				qzlist.add(text);
			}

			///////////////////////////		
			if(random.exists()){
				while(rndset.size()<5){ //
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
					fw = new FileWriter(random);//

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

				msg ="";//

				try {
					fr = new FileReader(random); //
					while(true){
						int su = fr.read(cbuf);			
						if(su==-1){break;}
						msg+=new String(cbuf,0,su);						
					}
				} catch (FileNotFoundException e1) {e1.printStackTrace();}
				catch (IOException e1) {e1.printStackTrace();} 
				finally {
					try {
						fr.close();
					} catch (IOException e1) {e1.printStackTrace();}
				}

				lines = msg.split("\n"); //

				for(int i=0; i<lines.length; i++){
					String[] line =lines[i].split(":");
					String[] text = new String[5];

					for(int j=0; j<text.length; j++){
						text[j] = (String)line[j];
					}
					rndlist.add(text);
				}

				String[] game_end = new String[]{"게임이 종료되었습니다","","","","게임이 종료되었으니 나가라..."};//
				rndlist.add(game_end);//

			}else{
				try {
					random.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			System.out.println("check the DB");
		}
	}

	@Override
	public void run(){ 

		for(int i=0; i<rndlist.size(); i++){
			String[] rndline = rndlist.get(i);


			String[] quiz_line = {rndline[0], rndline[1], rndline[2],rndline[3], rndline[4]}; 

			Protocol prtc = new Protocol();

			if(i==(rndlist.size()-1)){

				prtc.setCmd(8); 
				prtc.setFromUser(gamestarter.nickName); 
				prtc.setQuizLine(quiz_line);
				gamestarter.currentRoom.sendProtocol(prtc);
				
				try {
					this.sleep(3000);
				} catch (InterruptedException e) {e.printStackTrace();}

			}else {	

				prtc.setCmd(8); 
				prtc.setFromUser(gamestarter.nickName); 
				prtc.setQuizLine(quiz_line);
				gamestarter.currentRoom.sendProtocol(prtc);

				try {
					this.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("Quiz solved!");
				}
			}
		}
		
		


	}


}


