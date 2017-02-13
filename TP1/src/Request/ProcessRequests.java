package Request;

import java.io.File;

import Command.Command;
import Command.CommandList;
import Command.CommandPass;
import Command.CommandQuit;
import Command.CommandRetr;
import Command.CommandStor;
import Command.CommandUser;

public class ProcessRequests {
	private FtpRequest ftp;
	
	public ProcessRequests(FtpRequest ftp){
		this.ftp = ftp;
	}
	
	public void processUSER(String name){
		@SuppressWarnings("unused")
		Command user = new CommandUser(ftp, name);
		ftp.setNameGiven(true);
	}
	
	public void processPASS(String pass){
		if(ftp.getNameGiven()){
			@SuppressWarnings("unused")
			Command pwd = new CommandPass(ftp, pass);
		}else{
			ftp.send(000, "Erreur: utilisateur non renseigné");
		}
	}
	
	public void processPAVD(){
		Thread t = new Thread(){
			public void runnable(){
				return;
				//DataSocket ds = attendreCX();
			};
		};
		ftp.send(227, "");
		ftp.setIsPassive(true);
	}
	
	public void processPORT(){
		ftp.setIsPassive(false);
	}
	
	public void processRETR(File repertoire, String filename){
		@SuppressWarnings("unused")
		Command retr = new CommandRetr(ftp, repertoire, filename);
	}
	
	public void processSTOR(File repertoire, String filename){
		@SuppressWarnings("unused")
		Command stor = new CommandStor(ftp, repertoire, filename);
	}
	
	public void processLIST(File repertoire){
		@SuppressWarnings("unused")
		Command list = new CommandList(ftp, repertoire);
	}
	
	public void processQUIT(){
		@SuppressWarnings("unused")
		Command quit = new CommandQuit(ftp);
	}
}
