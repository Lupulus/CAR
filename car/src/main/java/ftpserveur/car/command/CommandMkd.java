package ftpserveur.car.command;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import ftpserveur.car.request.FtpRequest;

public class CommandMkd extends Command{
	
	public CommandMkd(FtpRequest ftp){
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		
		File target;
		
		try {
			if (arg.startsWith("/"))
				target =  new File(ftp.getCurrentDirectory().getAbsolutePath(), arg.substring(1)).getCanonicalFile();
			else 
				target = new File(ftp.getCurrentDirectory().getCanonicalPath(), arg).getCanonicalFile();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		if (target.exists()) {
			send(getAnswer().get("550Exist"));
			return false;
		}
		
		target.mkdirs();
		

		send(getAnswer().get("257Man"));
		return true;
	}
}
