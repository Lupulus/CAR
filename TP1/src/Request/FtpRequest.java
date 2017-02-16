package Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

@SuppressWarnings("unused")
public class FtpRequest {
	
	private String tab[];
	private boolean nameGiven = false, isConnected = false;
	private boolean isPassive;
	private BufferedReader in;
	private PrintWriter out;
	private File currentDirectory, homeDirectory;
	private Socket socket;
	private ProcessRequests request;
	private HashMap<String, String> users;
	private String connectedUser;
	
	/**
	 * Constructeur FtpRequest, instancie un fichier home qui est la racine du serveur
	 * Puis instancie un objet ProcessRequest
	 * @param s
	 * @param users
	 * @throws IOException
	 */
	public FtpRequest(Socket s, HashMap<String, String> users) {
		this.socket = s;
		this.users = users;
		homeDirectory = new File(System.getProperty("user.dir") + "/servorFile");
		currentDirectory = homeDirectory; 
		
		try{
			request = new ProcessRequests(this);
			this.in =  new BufferedReader( new InputStreamReader(s.getInputStream()));
			this.out = new PrintWriter(s.getOutputStream());
			send(220, "Attente d'un nouvel utilisateur");
		}catch(Exception e){
			
		}	
	}
	
	/**
	 * M�thode principale de la classe FtpRequest
	 * Elle fait une boucle infinie o� � chaque it�ration elle attend un envoi du client
	 * Une fois re�u elle divise le message puis fait quelques tests sur la connection de l'utilisateur avant 
	 * d'envoyer � la classe ProcessRequest qui va g�rer les commandes.
	 */
	public void processRequest(){
		
			try {
				
				while(true){
				    String temp = in.readLine();
				    this.tab = temp.split(" ");
				    String arg = (tab.length < 2) ? "" : tab[1];	    
				    
				    
					if(!getNameGiven() && !tab[0].equals("USER")){
						send(250, "Need connection");
					}
					else if(!getIsConnected() && !tab[0].equals("PASS")){
						send(250, "Need password");
					}
					else{
						request.dispatch(tab[0], tab[1]);
					}
			}
	    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param number
	 * @param text
	 * Cette m�thode permet au serveur d'envoyer des messages au client.
	 */
	public void send(int number, String text){
		out.println(number + " " + text + "\r");
		out.flush();
	}
	
	/**
	 * Accesseurs de la classe
	 * 
	 */
	
	public PrintWriter getOut(){
		return this.out;
	}
	
	public BufferedReader getIn(){
		return this.in;
	}
	
	public Socket getSocket(){
		return this.socket;
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
	
	public boolean getIsConnected(){
		return this.isConnected;
	}
	
	public File getCurrentDirectory(){
		return this.currentDirectory;
	}
	
	public void setCurrentDirectory(File repertoire){
		this.currentDirectory = repertoire;
	}
	
	public File getHomeDirectory(){
		return this.homeDirectory;
	}
	
	public ProcessRequests getRequest(){
		return this.request;
	}
	
	public HashMap<String, String> getUsers(){
		return this.users;
	}
	
	public String getConnectedUser(){
		return this.connectedUser;
	}
	
	public void setConnectedUser(String user){
		this.connectedUser = user;
	}
	
}
