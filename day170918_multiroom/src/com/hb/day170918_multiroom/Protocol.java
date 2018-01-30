
package com.hb.day170918_multiroom;

import java.io.Serializable; //cant understand serializable

public class Protocol implements Serializable{
  
	/*
     *  cmd - protocol cmd number;
     *     0. lounge chat
     *     1. lounge enter 
     *     2. lounge out
     *     3. room chat
     *     4. room make
     *     5. room enter
     *     6. roomout
     *     7. game start
     *     8. sending quiz
     *     9. when get correct answer and giving point > interrupt
     *     ~maybe quiz call is needed    
     */
	
    int cmd;
    int index;
    
    String fromUser;
    String msg; // 
    String[] user_name, room_name;  //
    
    Game sendGame;
    String[] quiz_line;
    
    int point;
    int score;
    
    public int getScore(){
    	return score;
    }
    
    public void setScore(int score){
    	this.score = score;
    }

    
    public int getPoint(){
    	return point;
    }
    
    public void setPoint(int point){
    	this.point = point;
    }
    
    public void setGame(Game sendGame) { //when if a Object is set in prtc, it cannot reach to the readObject;
    	 this.sendGame = sendGame;
    }
    
    public void setQuizLine(String[] quiz_line) {
		this.quiz_line = quiz_line;
}
    
    public String[] getQuizLine() {
    		return quiz_line;
    }
    
    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String[] getUser_name() {
        return user_name;
    }

    public void setUser_name(String[] user_name) {
        this.user_name = user_name;
    }

    public String[] getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String[] room_name) {
        this.room_name = room_name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
    
}
