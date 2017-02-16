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
					ftp.send(550, "Can't remove root directory");
					return false;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
			    Files.delete(target.toPath());
			    ftp.send(250, "File deleted successfully");
			    return true;
			} catch (NoSuchFileException e) {
				ftp.send(550, "File not found");
				return false;
			} catch (DirectoryNotEmptyException e) {
				ftp.send(550, "Directory not empty");
				return false;
			} catch (IOException e) {
				ftp.send(550, "Can't remove file: "+e.getMessage());
				return false;
			}
			
	}
}