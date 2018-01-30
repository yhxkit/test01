
package com.hb.day170918_multiroom;

import java.util.ArrayList;

public class ChatRoom {

	ArrayList<CopyClient> join_list;
	String room_name;
	CopyClient roommaster; //only who made the room can be roomma zzz can't be changed...there is a error
	Game roomGame;


	public ChatRoom(String rn, CopyClient roommaker){
		this.room_name = rn;
		this.roommaster = roommaker;
		this.join_list = new ArrayList<CopyClient>();
	}


	public Game newroomgame(CopyClient starter) {
				if(starter == roommaster) {//
		this.roomGame = new Game();
		return roomGame;
				}else {return null;}//
	}

	public void joinRoom(CopyClient user){
		join_list.add(user);

		System.out.println("lounge member size"+join_list.size());
		Protocol prtc = new Protocol();
		prtc.setCmd(5);
		prtc.setMsg(user.getNickName()+" entered");


		String[] names = new String[join_list.size()];
		for(int i=0; i<names.length; i++){
			CopyClient c = join_list.get(i);			
			if(c == roommaster) {names[i] = "<RoomMa>"+c.getNickName();}
			else{names[i] = c.getNickName();}
		}
		prtc.setUser_name(names);

		sendProtocol(prtc);
	}

	public void outRoom(CopyClient user){
		join_list.remove(user); // 

		Protocol prtc = new Protocol();
		//		prtc.setCmd(6); //just leave it anyway... error occured
		prtc.setCmd(5);

		prtc.setMsg(user.getNickName()+" exit");

		if(user == roommaster) {		
			if(join_list.size()>0){
				roommaster = join_list.get(0); // here is the problem... roomma can't be changed
			}else {roommaster = null;}
		}

		String[] names = new String[join_list.size()];
		for(int i=0; i<names.length; i++){
			CopyClient c = join_list.get(i);			
				if(c == roommaster) {names[i] = "<RoomMa>"+c.getNickName();}
				else{names[i] = c.getNickName();}

		}
		prtc.setUser_name(names);
		sendProtocol(prtc);

	}

	public void sendProtocol(Protocol prtc){
		try{
			for(CopyClient user : join_list)
			{user.out.writeObject(prtc);}
		}catch(Exception e){}
		System.out.println("prtc sent to all room member");
	}

	public int getCount(){
		return join_list.size();
	}

	public String getRoom_name() {
		return room_name;
	}


}
