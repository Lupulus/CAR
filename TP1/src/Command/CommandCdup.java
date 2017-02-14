package Command;

import Request.FtpRequest;

public class CommandCdup extends Command{
	
	public CommandCdup(FtpRequest ftp){
		super(ftp);
		new CommandCwd(ftp, "..");
	}
}
