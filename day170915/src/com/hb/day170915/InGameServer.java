package com.hb.day170915;
//���� 

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.Array;



public class InGameServer{

	//9001 ��Ʈ �ֽ�
	private static final int PORT = 9001; 
	private static ServerSocket listener;

	//Ŭ���̾�Ʈ �г��� �ؽü� => �� Ŭ���̾�Ʈ ���ö� �г��� �ߺ����� �ʰ�
	public static ArrayList<String> names = new ArrayList<String>();

	//Ŭ���̾�Ʈ���� ����Ʈ������ �ؽü� => �ѹ��� ������ �޼��� �Ѹ��� 
	public static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	public static HashMap<String, Integer> names_scores = new HashMap<String, Integer>();

	public static String roommaster = null;
	public static InQuiz q;


	InGameServer(){

		System.out.println("���� ��ŸƮ");

		try {
			listener = new ServerSocket(PORT); //�������� ����
			while (true) {
				new Handler(listener.accept(), this).start(); //���� ������ Ŭ���̾�Ʈ ��ü...������ ���� > ������ ����
				System.out.println("Ŭ���̾�Ʈ ���ӵ�");

				q = new InQuiz();
				System.out.println("���� ������ ����");
			}
		} catch (IOException e) {e.printStackTrace();} 
		finally {
			try {
				listener.close();
			} catch (IOException e) {e.printStackTrace();} //�������� ����
		}

	}

	public static void main(String[] args) {

		new InGameServer(); 
		
	
	}


}

