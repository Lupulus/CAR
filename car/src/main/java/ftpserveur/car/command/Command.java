package ftpserveur.car.command;

import ftpserveur.car.request.FtpRequest;

public abstract class Command {
	
	FtpRequest ftp = null;
	Answer answer;
	
	public Command(FtpRequest ftp){
		this.ftp = ftp;
		answer = new Answer(ftp);
	}
	
	public void send(String arg){
		ftp.send(arg);
	}
	
	public boolean process(String arg){
		return true;
	}
	
	public Answer getAnswer(){
		return this.answer;
	}
}
