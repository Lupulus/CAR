package Command;

import Request.FtpRequest;

public class CommandUser extends Command{
	
	public CommandUser(FtpRequest ftp, String name) {
		super(ftp);
		process(name);
	}
	
	public void process(String name){
		if(name.equals("laval")){
			super.send(331, "Please specify password");
		}else{
			//envoyer erreur
		}
	}

}
