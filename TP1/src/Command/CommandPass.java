package Command;

import Request.FtpRequest;

public class CommandPass extends Command{
	

	public CommandPass(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		if(arg.equals(ftp.getUsers().get(ftp.getConnectedUser())) ){
			send(getAnswer().get("230"));
			ftp.setIsConnected(true);
			return true;
		}else{
			send(getAnswer().get("503"));
			return false;
		}
	}
}
