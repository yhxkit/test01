
package com.hb.day170918_multiroom;

import java.net.*;
import java.util.ArrayList;

public class ChatServer extends Thread{
    	
    ServerSocket svsock;
    int port = 9001;
    
    ArrayList<CopyClient> user_list; // 
    ArrayList<ChatRoom> room_list;   // 

    public ChatServer() {
        try {
            svsock = new ServerSocket(port);
            user_list = new ArrayList<CopyClient>();
            room_list = new ArrayList<ChatRoom>();
            System.out.println("serverstart");
        } catch (Exception e) {}
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket sock = svsock.accept();
                CopyClient cc = new CopyClient(sock, this);
                user_list.add(cc);  //
                cc.start();
            } catch (Exception e) { }
        }
    }
    
    public void addRoom(ChatRoom cr){
        room_list.add(cr);
    }
    
    public void delUser(CopyClient cc){
        user_list.remove(cc);       // 
    }
    
    public void sendMessage(Protocol p){
        try{
            System.out.println("Lounge user count: " + user_list.size());
            for(CopyClient cc : user_list){
                cc.out.writeObject(p);
            }
        }catch(Exception e){}
    }
    
    
    public String[] getUserName(){
        String[] ar = new String[user_list.size()];
        
        // 
        int i = 0;
        for(CopyClient cc:user_list){
            ar[i++] = cc.getNickName();
        }
        return ar;
    }
    
    public String[] getRoomName(){
        String[] ar = new String[room_list.size()];
        
        int i = 0;
        for(ChatRoom cr:room_list){
            ar[i++] = cr.getRoom_name();
        }
        return ar;
    }
    public void addUser(CopyClient cc){
        user_list.add(cc);
    }
    
    public ChatRoom getRoom(int index){
        if(index > room_list.size())
            return null;    
        return room_list.get(index);
    }
    
    public CopyClient getUser(int index){
        if(index > user_list.size())
            return null;
        return user_list.get(index);
    }
    public int getUserIndex(CopyClient cc){
        for(int i = 0; i < user_list.size(); i++){
            if(user_list.get(i) == cc)
                return i;   
        }
        return -1;
    }
    
    public void delRoom(ChatRoom cr){

        room_list.remove(cr);
    }
    
    public static void main(String[] args) {
        new ChatServer().start();
    }
}
