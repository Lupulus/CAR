package Command;

import Request.FtpRequest;

public class CommandCdup extends Command{
	
	public CommandCdup(FtpRequest ftp){
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		CommandCwd cwd = new CommandCwd(ftp);
		return cwd.process("..");
	}
}
