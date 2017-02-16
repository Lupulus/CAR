package ftpserveur.car.command;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ftpserveur.car.request.FtpRequest;

public class CommandStor extends Command{

	public CommandStor(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		File file = new File(ftp.getCurrentDirectory().getAbsolutePath() + "/" + arg);
		//System.out.println(file.toString());
		try(FileOutputStream os = new FileOutputStream(file)) {
			this.receiveFromClient(os);
			return true;
		} catch(IOException e) {
			send(getAnswer().get("426"));
			return false;
		}
	}
	
	
	public void receiveFromClient(OutputStream data) {
		InputStream stream = null;
		DataInputStream dataB = null;
		try {
			// début d'utilisation de la connexion
			send(getAnswer().get("125"));
			dataB = new DataInputStream(ftp.getRequest().getSocket().getInputStream());
			
			int n;
			byte[] buffer = new byte[1024];
			while((n = dataB.read(buffer)) > -1) {
				data.write(buffer, 0, n);
			}
			
			dataB.close();
			data.close();

			send(getAnswer().get("226"));
		} catch(IOException  e) {
			send(getAnswer().get("426"));
		}
		try { if (stream != null) stream.close(); } catch(IOException e) { }
	}
}
