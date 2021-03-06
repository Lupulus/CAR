package ftpserveur.car.request;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

import ftpserveur.car.command.Answer;


public class FtpRequest {
	 
	private String tab[];
	private boolean nameGiven = false, isConnected = false, isClosed = false;
	private boolean isPassive;
	private BufferedReader in;
	private PrintWriter out;
	private File currentDirectory, homeDirectory;
	private Socket socket;
	private ProcessRequests request;
	private HashMap<String, String> users;
	private String connectedUser;
	private Answer answer;
	
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
		this.answer = new Answer(this);
		
		//System.out.println(answer.get("220"));
		send(answer.get("220"));
		try{
			this.in =  new BufferedReader( new InputStreamReader(s.getInputStream()));
			this.out = new PrintWriter(s.getOutputStream());
			this.request = new ProcessRequests(this);
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
	    String temp;
	    
		
		while(!getNameGiven()){
			try {
				temp = in.readLine();
			    this.tab = temp.split(" ");
			    String arg = (tab.length < 2) ? "" : tab[1];	 
				if(tab[0].equals("USER"))
					request.dispatch(tab[0], arg);
				else
					send(answer.get("220"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		while(!getIsConnected()){
			try{
				temp = in.readLine();
			    this.tab = temp.split(" ");
			    String arg = (tab.length < 2) ? "" : tab[1];	 
				if(tab[0].equals("PASS"))
					request.dispatch(tab[0], arg);
				else
					send(answer.get("250"));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		while(!getIsClosed()){
			try {
					
				    temp = in.readLine();
				    this.tab = temp.split(" ");
				    String arg = (tab.length < 2) ? "" : tab[1];	    
				    
					request.dispatch(tab[0], arg);
								
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
	}
	
	/**
	 * 
	 * @param number
	 * @param text
	 * Cette m�thode permet au serveur d'envoyer des messages au client.
	 */
	public void send(String arg){
		try {
			socket.getOutputStream().write(arg.getBytes());
			socket.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
}
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
		setPassive(setter);
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

	public boolean isPassive() {
		return isPassive;
	}

	public void setPassive(boolean isPassive) {
		this.isPassive = isPassive;
	}
	
	public boolean getIsClosed(){
		return this.isClosed;
	}
	
	public void setIsClosed(boolean setter){
		this.isClosed = setter;
	}
	
	public Answer getAnswer(){
		return this.answer;
	}
	
	public void setInetAddress(InetSocketAddress setter){
		this.answer.setInetAddress(setter) ;
	}
	
}
