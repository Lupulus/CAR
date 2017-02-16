package Command;

import Request.FtpRequest;

public class CommandUser extends Command{
	
	public CommandUser(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		if(ftp.getUsers().containsKey(arg)){
			send(getAnswer().get("331"));
			ftp.setNameGiven(true);
			ftp.setConnectedUser(arg);
			return true;
		}else{
			send(getAnswer().get("530"));
			return false;
		}
	}

}
