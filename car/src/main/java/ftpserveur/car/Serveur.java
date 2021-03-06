package ftpserveur.car;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ftpserveur.car.request.FtpRequest;

public class Serveur implements Runnable {

	/**
	 * @param args
	 * @throws IOException 
	 */
	private static Socket s;
	private static ServerSocket serv;
	
	/**
	 * On instancie un objet Information pour obtenir les informations du serveur (port et utilisateur)
	 * On instance une serveurSocket puis on attend la connnexion d'un client
	 * chaque client lance un thread lorsqu'il se connecte -> permet � plusieurs comptes d'�tre connect�s.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		final Information inf = new Information();
		
		try{
			serv = new ServerSocket(inf.getPortServor());
			System.out.println("Connection successful");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		while(true){
			try {
				s = serv.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Socket connectee");
			Thread t = new Thread(){
				public void run(){
					FtpRequest ftp = new FtpRequest(s, inf.getUsers());
					ftp.processRequest();				
				}};
			t.start();
		}
	}

	public void run() {
	
	}

}
