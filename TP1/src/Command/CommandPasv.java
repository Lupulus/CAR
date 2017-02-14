package Command;

import java.io.IOException;
import java.net.ServerSocket;

import Request.FtpRequest;

public class CommandPasv extends Command {
	
	public CommandPasv(FtpRequest ftp) throws IOException{
		super(ftp);
		ftp.getRequest().setServerSocket(new ServerSocket(0));
		ftp.getRequest().setSocket(ftp.getRequest().getServerSocket().accept());
	}
}
