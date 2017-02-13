package Command;

import java.io.File;

import Main.FtpRequest;

public class CommandList extends Command{

	public CommandList(FtpRequest ftp, File repertoire) {
		super(ftp);
		process(repertoire);
	}
	
	public void process(File repertoire){
		String[] files = repertoire.list();
		for(int i = 0; i<files.length; i++){
			ftp.getOut().println(files[i]);
			ftp.getOut().flush();
		}
	}

}
