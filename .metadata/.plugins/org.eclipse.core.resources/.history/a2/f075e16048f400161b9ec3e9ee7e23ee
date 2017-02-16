package Command;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import Request.FtpRequest;

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
			ftp.send(550, "File with same name already exists");
			return false;
		}
		
		target.mkdirs();
		
		ftp.send(257,"Command mkdir managed");
		return true;
	}
}
