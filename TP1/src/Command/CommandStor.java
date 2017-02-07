package Command;

import java.io.File;

import Main.FtpRequest;

public class CommandStor extends Command{

	public CommandStor(FtpRequest ftp, File repertoire, String filename) {
		super(ftp);
		process(repertoire, filename);
	}
	
	public void process(File repertoire, String filename){
		File source = new File("" + "/" + filename);
		File destination = new File(repertoire.getAbsolutePath() + "/" + filename);

	    source.renameTo(destination);
	    super.send(226, "STOR réussi");
	}

}
