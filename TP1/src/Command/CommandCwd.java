package Command;

import java.io.File;
import Command.CommandPwd;
import Request.FtpRequest;

public class CommandCwd extends Command {
	
	public CommandCwd(FtpRequest ftp){
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		switch(arg){
			case ".": CommandPwd pwd = new CommandPwd(ftp);
					  pwd.process(arg);
				      return true;
					  
			case "..": if(ftp.getCurrentDirectory().toString().substring(ftp.getHomeDirectory().toString().lastIndexOf("/")).equals("/servorFile")){
							send(getAnswer().get("553"));
							return false;
					   }else{
							ftp.setCurrentDirectory(
									new File(ftp.getCurrentDirectory().toString().substring(0, ftp.getCurrentDirectory().toString().lastIndexOf("\\" ))
											));
							send(getAnswer().get("212"));
							return true;
						}
			default: for(String temp : ftp.getCurrentDirectory().list()){
						if(temp.equals(arg)){
							ftp.setCurrentDirectory(new File(ftp.getCurrentDirectory().toString() + "\\" + arg));
							send(getAnswer().get("212"));
							return true;
						}	
					 }
					send(getAnswer().get("550"));
					 return false;
				
		}
	}
}
