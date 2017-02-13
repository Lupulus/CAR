package Command;

import Request.FtpRequest;

public abstract class Command {
	
	FtpRequest ftp = null;
	
	public Command(FtpRequest ftp){
		this.ftp = ftp;
	}
	
	public void send(int number, String text){
		ftp.send(number, text);
	}

}
