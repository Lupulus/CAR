package Command;

import Request.FtpRequest;

public class CommandCdup extends Command{
	
	public CommandCdup(FtpRequest ftp){
		super(ftp);
		CommandCwd cwd = new CommandCwd(ftp);
		cwd.process("..");
	}
}
