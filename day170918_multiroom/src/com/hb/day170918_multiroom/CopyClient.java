
package com.hb.day170918_multiroom;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CopyClient extends Thread{


	Socket sock;
	ChatServer server;

	ObjectInputStream in;
	ObjectOutputStream out;

	String nickName;
	int score; //reset when exit room

	ChatRoom currentRoom; 

	public CopyClient(Socket sock, ChatServer server) {
		this.sock = sock;
		this.server = server;
		try {
			in = new ObjectInputStream(sock.getInputStream());
			out = new ObjectOutputStream(sock.getOutputStream());
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		boolean repeat = true;  
		while(repeat){
			try {
				Protocol p = (Protocol) in.readObject();

				switch(p.getCmd()){
				case 0:
					p.setFromUser(nickName);
					server.sendMessage(p);
					break;
				case 1: 
					nickName = p.getMsg();

					p.setRoom_name(server.getRoomName());
					p.setUser_name(server.getUserName());

					server.sendMessage(p);
					break;
				case 2: 

					out.writeObject(p); // 

					server.delUser(this);   

					waitingRoomRenew(); // 

					repeat = false; // 
					break;
				case 3: 
					p.setFromUser(nickName); 
					currentRoom.sendProtocol(p);

					break;

				case 4: 
					String r_name = p.getMsg(); //
					currentRoom = new ChatRoom(r_name, this);

					server.addRoom(currentRoom);
					server.delUser(this);


					currentRoom.joinRoom(this);


					waitingRoomRenew(); 
					break;
				case 5: //
					int index = p.getIndex();
					currentRoom = server.getRoom(index);

					if(currentRoom != null){
						server.delUser(this);

						currentRoom.joinRoom(this);

						waitingRoomRenew();
					}

					break;
				case 6: 
					currentRoom.outRoom(this);

					if(currentRoom.getCount() < 1){
						server.delRoom(currentRoom);
					}

					this.currentRoom = null;
					server.addUser(this);

					waitingRoomRenew();

					break;
				case 7: // 

				if(this == currentRoom.roommaster){ //방장일때만 

				p.setCmd(7);
				p.setFromUser(nickName);
				currentRoom.sendProtocol(p);

				//////////	

				p.setCmd(8);

				currentRoom.newroomgame(this).startQuizThread(this); //make a new game object//get a return of String[] quizline

				System.out.println("유저가 게임 받아서 게임 셋");
				}
				break;

				case 8:

					out.writeObject(p);

					break;

				case 9:



					if(nickName.equals(p.getFromUser())){
						score += p.getPoint();
						p.setScore(score);

						p.setCmd(3);

						p.setMsg(p.getPoint() + "점 획득 (총 " + score + "점)"); 

						
						currentRoom.sendProtocol(p);

						currentRoom.roomGame.newQ.interrupt();
					}
					


					break;
				}
			} catch (Exception e) {
			}
		}

		try {

			if(in != null)
				in.close();
			if(out != null)
				out.close();
			if(sock != null)
				sock.close();

			System.out.println("user sock/stream close");



		} catch (Exception e) {
		}
	}

	public String getNickName() {
		return nickName;
	}

	private void waitingRoomRenew(){
		Protocol p = new Protocol();
		p.setCmd(1);
		p.setRoom_name(server.getRoomName());
		p.setUser_name(server.getUserName());

		server.sendMessage(p);
	}

}
