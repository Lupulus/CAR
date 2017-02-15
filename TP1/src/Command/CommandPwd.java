package Command;


import Request.FtpRequest;

public class CommandPwd extends Command{
	public CommandPwd(FtpRequest ftp) {
		super(ftp);
		
	}
	
	@Override
	public boolean process(String arg){
		ftp.send(257, ftp.getCurrentDirectory().toString().substring(ftp.getHomeDirectory().toString().lastIndexOf("/")));
		return true;
	}
}
