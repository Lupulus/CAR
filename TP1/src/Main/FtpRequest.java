package Main;

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
	private boolean getName = false, connected = false;
	private boolean passive;
	private BufferedReader in;
	private PrintWriter out;
	private File repertoire;
	private Socket s;
	
	public FtpRequest(Socket s) {
		this.s = s;
		repertoire = new File(System.getProperty("user.dir") + "/servorFile");
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
			    		    	
					if(connected){
						switch(tab[0].toUpperCase()){
							case "USER" : 
							case "PASS" : send(2, "Erreur déjà connecté");
							              break;
							case "PAVD" : this.processPAVD();
							              break;
							case "PORT" : this.processPORT();
							              break;
							case "RETR" : this.processRETR(tab[1]);
							              break;
							case "LIST" : this.processLIST();
							              break;
							case "STOR" : this.processSTOR(tab[1]);
							              break;
							case "QUIT" : this.processQUIT();
							              break;
							default     : //renvoyer une erreur
						}
					}else{
						switch(tab[0].toUpperCase()){
							case "USER" : this.processUSER(tab[1]); 
										  break;
							case "PASS" : this.processPASS(tab[1]);
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
	
	public void processUSER(String name){
		@SuppressWarnings("unused")
		Command user = new CommandUser(this, name);
		getName = true;
	}
	
	public void processPASS(String pass){
		if(getName){
			@SuppressWarnings("unused")
			Command pwd = new CommandPass(this, pass);
			connected = true;
		}else{
			send(000, "Erreur: utilisateur non renseigné");
		}
	}
	
	public void processPAVD(){
		Thread t = new Thread(){
			public void runnable(){
				return;
				//DataSocket ds = attendreCX();
			};
		};
		send(227, "");
		passive = true;
	}
	
	public void processPORT(){
		passive = false;
	}
	
	public void processRETR(String filename){
		@SuppressWarnings("unused")
		Command retr = new CommandRetr(this, repertoire, filename);
	}
	
	public void processSTOR(String filename){
		@SuppressWarnings("unused")
		Command stor = new CommandStor(this, repertoire, filename);
	}
	
	public void processLIST(){
		@SuppressWarnings("unused")
		Command list = new CommandList(this, repertoire);
	}
	
	public void processQUIT(){
		@SuppressWarnings("unused")
		Command quit = new CommandQuit(this);
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
}