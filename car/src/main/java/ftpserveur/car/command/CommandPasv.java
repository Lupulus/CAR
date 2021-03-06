package ftpserveur.car.command;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import ftpserveur.car.request.FtpRequest;

public class CommandPasv extends Command {
	
	public CommandPasv(FtpRequest ftp) throws IOException{
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		try {
			ftp.getRequest().setServerSocket(new ServerSocket(0,1,ftp.getSocket().getLocalAddress()));
			send("227 Socket address: " + (InetSocketAddress) ftp.getRequest().getServerSocket().getLocalSocketAddress());
			ftp.getRequest().setSocket(ftp.getRequest().getServerSocket().accept());
			send(getAnswer().get("227"));
			return true;
		} catch (IOException e) {
			send(getAnswer().get("425"));
			e.printStackTrace();
			return false;
		}
	}
}
