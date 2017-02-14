package Command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Request.FtpRequest;

public class CommandPort extends Command{
	
	public CommandPort(FtpRequest ftp, String ip, String port){
		super(ftp);
		try {
			process(ip, port);
		} catch (NumberFormatException | UnknownHostException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void process(String ip, String port) throws NumberFormatException, UnknownHostException, IOException{
		Socket s = new Socket(InetAddress.getByName(ip),Integer.parseInt(port));
	}
}
//serverSocket sur port 0 new ServerSocket(0) --> le crée aléatoirement

//ici on récup IP et port puis on crée une socket pour