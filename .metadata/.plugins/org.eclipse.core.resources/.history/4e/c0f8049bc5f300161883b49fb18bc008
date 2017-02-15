package Command;

import Request.FtpRequest;

public class CommandPass extends Command{
	

	public CommandPass(FtpRequest ftp, String pass) {
		super(ftp);
		process(pass);
	}
	
	public void process(String pass){
		if(pass.equals("pass") ){
			super.send(230, "Login succesful");
			ftp.setIsConnected(true);
		}else{
			super.send(000, "Mot de passe incorrect");
		}
	}
}
