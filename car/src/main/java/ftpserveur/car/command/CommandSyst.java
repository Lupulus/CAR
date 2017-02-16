package ftpserveur.car.command;

import ftpserveur.car.request.FtpRequest;

public class CommandSyst extends Command{
	
	public CommandSyst(FtpRequest ftp){
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		send(getAnswer().get("215"));
		return true;
	}
}
