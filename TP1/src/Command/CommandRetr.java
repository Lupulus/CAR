package Command;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import Main.FtpRequest;

public class CommandRetr extends Command{

	public CommandRetr(FtpRequest ftp, File repertoire, String filename) {
		super(ftp);
		process(repertoire, filename);
	}
	
	public void process(File repertoire, String filename){
		  File downloadFile = new File(repertoire + filename);
          OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
          boolean success = ftp.retrieveFile(filename, outputStream1);
          outputStream1.close();
	    super.send(001, "RETR réussi");
	}
	
}
