package Command;


import Request.FtpRequest;

public class CommandPwd extends Command{
	public CommandPwd(FtpRequest ftp) {
		super(ftp);
		
	}
	
	@Override
	public boolean process(String arg){
		send(getAnswer().get("257Pwd"));
		return true;
	}
}
