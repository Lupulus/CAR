package Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Command.Command;
import Command.CommandList;
import Command.CommandPass;
import Command.CommandQuit;
import Command.CommandRetr;
import Command.CommandStor;
import Command.CommandUser;

public class FtpRequest {
	
	private String tab[];
	private boolean nameGiven = false, isConnected = false;
	private boolean isPassive;
	private BufferedReader in;
	private PrintWriter out;
	private File repertoire;
	private Socket s;
	private ProcessRequests request;
	
	public FtpRequest(Socket s) {
		this.s = s;
		repertoire = new File(System.getProperty("user.dir") + "/servorFile");
		request = new ProcessRequests(this);
		try{
			this.in =  new BufferedReader( new InputStreamReader(s.getInputStream()));
			this.out = new PrintWriter(s.getOutputStream());
			send(220, "Attente d'un nouvel utilisateur");
		}catch(Exception e){
			
		}
		//System.out.println(repertoire.getAbsolutePath());		
	}
	
	public void processRequest(){
		
			try {
				send(220, "connection");
				while(true){
				    String temp = in.readLine();
				    this.tab = temp.split(" ");
			    		    	
					if(isConnected){
						switch(tab[0].toUpperCase()){
							case "USER" : 
							case "PASS" : send(2, "Erreur déjà connecté");
							              break;
							case "PAVD" : request.processPAVD();
							              break;
							case "PORT" : request.processPORT();
							              break;
							case "RETR" : request.processRETR(repertoire, tab[1]);
							              break;
							case "LIST" : request.processLIST(repertoire);
							              break;
							case "STOR" : request.processSTOR(repertoire, tab[1]);
							              break;
							case "QUIT" : request.processQUIT();
							              break;
							default     : //renvoyer une erreur
						}
					}else{
						switch(tab[0].toUpperCase()){
							case "USER" : request.processUSER(tab[1]); 
										  break;
							case "PASS" : request.processPASS(tab[1]);
							              break;
							case "PAVD" : 
							case "PORT" : 
							case "RETR" : 
							case "LIST" : 
							case "STOR" : 
							case "QUIT" : send(1, "Erreur Non connecté");
							              break;
							default     : //renvoyer une erreur
						}
					}
			}
	    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	public void send(int number, String text){
		out.println(number + " " + text + "\r");
		out.flush();
	}
	
	
	public PrintWriter getOut(){
		return this.out;
	}
	
	public BufferedReader getIn(){
		return this.in;
	}
	
	public Socket getSocket(){
		return this.s;
	}
	
	public void setIsPassive(boolean setter){
		isPassive = setter;
	}
	
	public boolean getNameGiven(){
		return this.nameGiven;
	}
	
	public void setNameGiven(boolean setter){
		this.nameGiven = setter;
	}
	
	public void setIsConnected(boolean setter){
		this.isConnected = setter;
	}
	
}
