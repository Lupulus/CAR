package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur implements Runnable{

	/**
	 * @param args
	 * @throws IOException 
	 */
	private static Socket s;
	private static ServerSocket serv;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		try{
			serv = new ServerSocket(2250);
			System.out.println("Connection successful");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		while(true){
			s = serv.accept();
			System.out.println("Socket connectée");
			Thread t = new Thread(){
				public void run(){
					FtpRequest ftp = new FtpRequest(s);
					ftp.processRequest();
				}};
			t.start();
		}
	}

	@Override
	public void run() {
	}

}
