package Command;

import java.io.File;

import Request.FtpRequest;

public class CommandPwd extends Command{
	public CommandPwd(FtpRequest ftp, File repertoire) {
		super(ftp);
		process(repertoire);
	}
	
	public void process(File repertoire){

	}
}
