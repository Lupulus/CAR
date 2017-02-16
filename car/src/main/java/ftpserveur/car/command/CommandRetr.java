package ftpserveur.car.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ftpserveur.car.request.FtpRequest;

public class CommandRetr extends Command{

	public CommandRetr(FtpRequest ftp) {
		super(ftp);
	}
	
	@Override
	public boolean process(String arg){
		File file = new File(ftp.getCurrentDirectory().getAbsolutePath() + "/" + arg);
		
		try(FileInputStream is = new FileInputStream(file)) {
			this.transmitToClient(is);
			return true;
		} catch(IOException e) {
			send(getAnswer().get("550NotFound"));
			return false;
		}
	   
	}
	
	public void transmitToClient(InputStream data) {
		OutputStream stream = null;
		try {
			// début d'utilisation de la connexion
			send(getAnswer().get("125"));
			
			stream = ftp.getRequest().getSocket().getOutputStream();
			
			int n;
			byte[] buffer = new byte[1024];
			while((n = data.read(buffer)) > -1) {
				stream.write(buffer, 0, n);
			}
			
			stream.close();

			send(getAnswer().get("226"));
		} catch(IOException e) {
			send(getAnswer().get("426"));
		}
		//try { if (stream != null) stream.close(); } catch(IOException e) { }
	}
	
}
