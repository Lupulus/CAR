package Command;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import Request.FtpRequest;

public class CommandStor extends Command{

	public CommandStor(FtpRequest ftp, File repertoire, String filename) {
		super(ftp);
		process(repertoire, filename);
	}
	
	public void process(File repertoire, String filename){
		File file = new File(repertoire.getPath() + "/" + filename);
		//System.out.println(file.toString());
		try(FileOutputStream os = new FileOutputStream(file)) {
			this.receiveFromClient(os);
		} catch(IOException e) {
			ftp.send(426, "Connection closed, transfert aborted");
			return;
		}
	}
	
	public String receiveStringFromClient(Charset charset) {
		return new String(receiveBytesFromClient(), charset);
	}

	public byte[] receiveBytesFromClient() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		receiveFromClient(stream);
		return stream.toByteArray();
	}
	
	public void receiveFromClient(OutputStream data) {
		InputStream stream = null;
		try {
			// début d'utilisation de la connexion
			ftp.send(125, "Data connection already open; transfer starting.");

			stream = ftp.getSocket().getInputStream();
			
			int n;
			byte[] buffer = new byte[1024];
			while((n = stream.read(buffer)) > -1) {
				data.write(buffer, 0, n);
			}
			
			
			
			ftp.send(226,"Data transfered succesfully. Data connection closed.");
		} catch(IOException  e) {
			ftp.send(426, "Connection closed, transfert aborted");
		}
		try { if (stream != null) stream.close(); } catch(IOException e) { }
	}
}
