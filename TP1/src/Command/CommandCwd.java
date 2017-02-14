package Command;

import java.io.File;

import Request.FtpRequest;

public class CommandCwd extends Command {
	
	public CommandCwd(FtpRequest ftp){
		super(ftp);
	}
	
	public boolean process(String filename){
		switch(filename){
			case ".": @SuppressWarnings("unused")
					  CommandPwd pwd = new CommandPwd(ftp);
				      return true;
					  
			case "..": if(ftp.getCurrentDirectory().toString().substring(ftp.getHomeDirectory().toString().lastIndexOf("/")).equals("/servorFile")){
							send(553, "Repertoire racine, commande refusee");
							return false;
					   }else{
							ftp.setCurrentDirectory(
									new File(ftp.getCurrentDirectory().toString().substring(0, ftp.getCurrentDirectory().toString().lastIndexOf("\\" ))
											));
							ftp.send(212, ftp.getCurrentDirectory().toString());
							return true;
						}
						//System.out.println(ftp.getCurrentDirectory().toString().substring(ftp.getHomeDirectory().toString().lastIndexOf("/")));
			default: for(String temp : ftp.getCurrentDirectory().list()){
						if(temp.equals(filename)){
							ftp.setCurrentDirectory(new File(ftp.getCurrentDirectory().toString() + "\\" + filename));
							ftp.send(212, ftp.getCurrentDirectory().toString());
							return true;
						}	
					 }
					 send(550, "Repertoire inconnu, commande refusee");
					 return false;
				
		}
	}
}