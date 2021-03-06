package ftpserveur.car.command;

import java.net.InetSocketAddress;
import java.util.HashMap;

import ftpserveur.car.request.FtpRequest;

public class Answer {
	
	private HashMap <String, String> answers;
	private String message = "";
	private FtpRequest ftp; 
	private InetSocketAddress inetAddress;
	
	public Answer(FtpRequest ftp){
		this.ftp = ftp;
		this.answers = new HashMap<String, String>();
		putAllAnswers();
	}
	
	/**
	 * Crée une HashMap qui contient en clé la valeur de la réponse et en valeur le message renvoyé
	 * Cette méthohde crée toutes les clés nécessaires au TP.
	 */
	public void putAllAnswers(){
		answers.put("120", "120 Syntax error");
		answers.put("125", "125 Data connection already open; transfer starting.");
		answers.put("212", "212 Enter directory");
		answers.put("215", "215 UNIX Type: L8");
		answers.put("220", "220 Wait for user to connect");
		answers.put("221", "221 Deconnection");
		answers.put("226", "226 Data transfered succesfully. Data connection closed.");
		answers.put("227", "227 Entering passive mode address: " + inetAddress);
		answers.put("230", "230 Login succesful");
		
		answers.put("250", "250 Need password to log");
		answers.put("250Dele", "250 File deleted successfully");
		
		answers.put("257Dir", "257 Directory created");
		answers.put("257Pwd", "257 " + ftp.getCurrentDirectory().toString() );
		
		answers.put("331", "331 Please specify password");
		answers.put("425", "425 Can't open data connection");
		answers.put("426", "426 Connection closed, transfert aborted");
		
		answers.put("503", "503 Incorrect password");
		
		answers.put("530", "530 Bad loggin");
		answers.put("550Remove", "550 Can't remove root directory");
		answers.put("550NotFound", "550 File not found");
		answers.put("550NotEmpty", "550 Directory not empty");
		answers.put("550CantRem", "550 Can't remove file");
		answers.put("550Exist", "550 File with same name already exists");
		
		
		answers.put("2000", "2000 Port sucessful");
		
	}
	
	
	
	/**
	 * Accesseurs
	 */
	
	public HashMap<String, String> getAnswers(){
		return this.answers;
	}
	
	public String get(String message){
		if(!message.equals("553"))
			return answers.get(message) + "\r\n";
		else 
			return  "120 Wait for password \r\n";
	}
	
	public void setInetAddress(InetSocketAddress setter){
		this.inetAddress = setter;
	}
	
}
