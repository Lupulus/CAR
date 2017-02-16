package Command;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import Request.FtpRequest;

public class CommandDele extends Command{
	
	public CommandDele(FtpRequest ftp){
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
			
			try {
				if (target.equals(ftp.getHomeDirectory().getCanonicalPath())) {
					send(getAnswer().get("550Remove"));
					return false;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
			    Files.delete(target.toPath());
			    send(getAnswer().get("250Dele"));
			    return true;
			} catch (NoSuchFileException e) {
				send(getAnswer().get("550NotFound"));
				return false;
			} catch (DirectoryNotEmptyException e) {
				send(getAnswer().get("550NotEmpty"));
				return false;
			} catch (IOException e) {
				send(getAnswer().get("550CantRem"));
				return false;
			}
			
	}
}
