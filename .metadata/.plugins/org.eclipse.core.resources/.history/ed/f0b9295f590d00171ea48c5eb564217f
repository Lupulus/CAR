package main.java.rest.car.ftpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTPClient;

import main.java.rest.car.config.Configuration;

public  class FtpExec {
	
	private FTPClient ftpClient = null;
	private boolean closeFTPClientAfterExecute = true;
	
	
	private List<FtpRequest> fTPRequests = new ArrayList<>();
	
	/**
	 * Instancie une nouvelle {@link FTPRequestExecutor}.
	 * Une {@link FTPRequestAction} est ajoutée en premier de la liste des fonctions à
	 * exécuter lors de l'appel à {@link #execute()}, qui se cherge de la connexion
	 * au serveur FTP.
	 */
	public FtpExec() {
		addAction((exec) -> {
			FTPClient cl = new FTPClient();
			cl.connect(Configuration.getConfig().getFTPHost(),
					Configuration.getConfig().getFTPPort());
			ftpClient = cl;
			return null;
		});
	}
	
	
	public FtpExec addAction(FtpRequest execPart) {
		fTPRequests.add(execPart);
		return this;
	}
	
	public Response execute() {
		try {
			
			Response rep = null;
			for (FtpRequest part : fTPRequests) {
				rep = part.exec(this);
				if (rep != null)
					break;
			}
			
			if (rep == null)
				throw new IllegalStateException("No Response returned after executing all execution parts.");
			
			return rep;
			
		} catch(Exception e) {
			return ResponseFactory.jsonExceptionResponse(Status.INTERNAL_SERVER_ERROR, e);
		} finally {
			if (ftpClient != null && closeFTPClientAfterExecute)
				try {
					ftpClient.disconnect();
				} catch (IOException e) { }
		}
	}
	
	public FTPClient getFTPClient(){
		return ftpClient;
	}
	
}
