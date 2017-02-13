package Command;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import Request.FtpRequest;

public class CommandRetr extends Command{

	public CommandRetr(FtpRequest ftp, File repertoire, String filename) {
		super(ftp);
		process(repertoire, filename);
	}
	
	public void process(File repertoire, String filename){
		File file = new File(repertoire.getPath() + "/" + filename);
		
		try(FileInputStream is = new FileInputStream(file)) {
			this.transmitToClient(is);
		} catch(IOException e) {
			 super.send(001, "RETR réussi");
			return;
		}
	   
	}
	
	
	public void transmitToClient(String data, Charset charset) {
		transmitToClient(data.getBytes(charset));
	}
	
	public void transmitToClient(byte[] data) {
		transmitToClient(new ByteArrayInputStream(data));
	}
	
	public void transmitToClient(InputStream data) {
		OutputStream stream = null;
		try {
			// début d'utilisation de la connexion
			ftp.send(125, "Data connection already open; transfer starting.");
			
			stream = ftp.getSocket().getOutputStream();
			
			int n;
			byte[] buffer = new byte[1024];
			while((n = data.read(buffer)) > -1) {
				stream.write(buffer, 0, n);
			}
			
			stream.close();
			
			ftp.send(226, "Data transfered succesfully. Data connection closed.");
		} catch(IOException e) {
			ftp.send(426, "Connection closed, transfert aborted");
		}
		try { if (stream != null) stream.close(); } catch(IOException e) { }
	}
	
}
