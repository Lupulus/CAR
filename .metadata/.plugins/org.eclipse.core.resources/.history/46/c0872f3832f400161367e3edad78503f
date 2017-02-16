package Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import Command.Command;
import Command.CommandCdup;
import Command.CommandCwd;
import Command.CommandDele;
import Command.CommandList;
import Command.CommandMkd;
import Command.CommandPass;
import Command.CommandPasv;
import Command.CommandPort;
import Command.CommandPwd;
import Command.CommandQuit;
import Command.CommandRetr;
import Command.CommandStor;
import Command.CommandUser;

public class ProcessRequests {
	private FtpRequest ftp;
	private ServerSocket servSoc = null;
	private Socket soc = null;
	protected Map<String, Command> commands = new HashMap<>();
	
	/**
	 * Constructeur ProcessRequests
	 * @param ftp
	 * @throws IOException
	 */
	public ProcessRequests(FtpRequest ftp) throws IOException{
		this.ftp = ftp;
		this.putAllCommands();
	}
	
	/**
	 * Cette m�thode cr�e la HashMap qui servira au dispatch des commandes
	 * @throws IOException
	 */
	public void putAllCommands() throws IOException{
		commands.put("CDUP", new CommandCdup(ftp));
		commands.put("CWD",  new CommandCwd(ftp));
		commands.put("DELE", new CommandDele(ftp));
		commands.put("LIST", new CommandList(ftp));
		commands.put("MKD",  new CommandMkd(ftp));
		commands.put("PASS", new CommandPass(ftp));
		commands.put("PASV", new CommandPasv(ftp));
		commands.put("PORT", new CommandPort(ftp));
		commands.put("PWD",  new CommandPwd(ftp));
		commands.put("QUIT", new CommandQuit(ftp));
		commands.put("RETR", new CommandRetr(ftp));
		//commands.put("RMD",  new CommandRmd(ftp));
		commands.put("STOR", new CommandStor(ftp));
		commands.put("USER", new CommandUser(ftp));
	}
	
	/**
	 * Les v�rifications de connexion ont �t� faites avant appel � dispatch
	 * dispatch v�rifie si la commande existe puis fait un appel � la classe qui l'executera
	 * @param command
	 * @param arg
	 */
	public void dispatch(String command, String arg){
		if (commands.containsKey(command)) {
			Command executeCommand = commands.get(command);
			AtomicReference<String> argRef = new AtomicReference<>(arg);
			executeCommand.process(argRef.get());		
		}else
			ftp.send(120, "Erreur de syntaxe");
	}

	
	/**
	 * 
	 * Accesseurs de la classe
	 * 
	 */
	public ServerSocket getServerSocket(){
		return this.servSoc;
	}
	
	public void setServerSocket(ServerSocket servSoc){
		this.servSoc = servSoc;
	}
	
	public Socket getSocket(){
		return this.soc;
	}
	
	public void setSocket(Socket soc){
		this.soc = soc;
	}
}


