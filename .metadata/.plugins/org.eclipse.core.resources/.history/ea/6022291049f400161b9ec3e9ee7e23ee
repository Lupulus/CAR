package Command;

import Request.FtpRequest;

public class CommandPass extends Command{
	

	public CommandPass(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		if(arg.equals(ftp.getUsers().get(ftp.getConnectedUser())) ){
			super.send(230, "Login succesful");
			ftp.setIsConnected(true);
			return true;
		}else{
			super.send(000, "Mot de passe incorrect");
			return false;
		}
	}
}
