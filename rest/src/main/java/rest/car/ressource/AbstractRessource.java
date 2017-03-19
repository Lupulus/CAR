package main.java.rest.car.ressource;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.net.ftp.FTPClient;

import main.java.rest.car.config.Configuration;
import main.java.rest.car.ftpclient.FtpRequest;
import main.java.rest.car.ftpclient.ResponseFactory;

public abstract class AbstractRessource {

	/**
	 * Retourne une fonction de type {@link FTPRequestAction} qui doit prendre en charge
	 * le login aupr�s du serveur FTP.<br>
	 * Cette fonction, lors de son ex�cution, retourne null si le login a r�ussi (permet aux fonctions suivantes
	 * de s'ex�cuter), ou une {@link Response} qui sera envoy� au client dans le cas contraire.
	 * @param authorizationHeaderValue la valeur du header "Authorization" dont la fonction retourn�e va
	 * se servir lors de son ex�cution.
	 * @return une fonction se chargeant de traiter authorizationHeaderValue et de se connecter au
	 */
	protected static FtpRequest getBasicLoginHandler(String authorizationHeaderValue) {
		return (exec) -> {
			if (authorizationHeaderValue != null && authorizationHeaderValue.toLowerCase().startsWith("basic ")) {
				String token = authorizationHeaderValue.substring("basic ".length());
				token = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
				String[] split = token.split(":", 2);
				if (split.length >= 2) {
					if (exec.getFTPClient().login(split[0], split[1])) {
						return null; // le login s'est bien pass�
					}
				}
			}
			return ResponseFactory.jsonFTPResponse(
					Response.status(Status.UNAUTHORIZED)
							.header("WWW-Authenticate",
									"Basic realm=\"FTP "+Configuration.getConfig().getFTPHost()
									+":"+Configuration.getConfig().getFTPPort()+"\""),
					exec.getFTPClient().getReplyCode(),
					exec.getFTPClient().getReplyStrings());
		};
	}
	
	/**
	 * Retourne une fonction de type {@link FTPRequestAction} qui doit prendre en charge tout ce qui n'a
	 * pas �t� prise en charge par les fonctions appel�s pr�c�demment (erreurs FTP communes, par exemples)
	 * par un {@link FTPRequestExecutor}.
	 * Il doit donc �tre le dernier pass� � la m�thode {@link FTPRequestExecutor#then(FTPRequestAction)}.
	 * La fonction retourn�e ne renvoit jamais null lors de son ex�cution.
	 * @return une fonction devant �tre pass� en param�tre de {@link FTPRequestExecutor#then(FTPRequestAction)}.
	 */
	protected static FtpRequest getFallbackHandler() {
		return (exec) -> {
			int code = exec.getFTPClient().getReplyCode();
			return ResponseFactory.jsonFTPResponse(
					(code >= 500 && code < 600) ? Status.BAD_REQUEST : Status.INTERNAL_SERVER_ERROR,
					code,
					exec.getFTPClient().getReplyStrings());
		};
	}
	
	
	/**
	 * Retourne une fonction de type {@link FTPRequestAction} qui definit le mode de transfert (active/passive) pour la connection des donn�es FTP. 
	 * @param transfertMode Le mode de transfert (active/passive)
	 * @return une fonction devant �tre pass� en param�tre de {@link FTPRequestExecutor#then(FTPRequestAction)}.
	 */
	protected static FtpRequest getTransfertModeHandler(String transfertMode) {
		return (exec) -> {
			if (transfertMode != null) {
				if (transfertMode.equalsIgnoreCase("active")) {
					exec.getFTPClient().enterLocalActiveMode();
				} else if (transfertMode.equalsIgnoreCase("passive")) {
					exec.getFTPClient().enterLocalPassiveMode();
				}
			}
			return null;
		};
	}
	
	
	/**
	 * Retourne une fonction de type {@link FTPRequestAction} qui definit le type du fichier (binaire/texte). 
	 * @param dataType type du fichier ("binaire" ou "texte"). 
	 * @return une fonction devant �tre pass� en param�tre de {@link FTPRequestExecutor#then(FTPRequestAction)}.
	 */
	protected static FtpRequest getDataTypeHandler(String dataType) {
		return (exec) -> {
			if (dataType != null && dataType.equalsIgnoreCase("ascii")) {
				exec.getFTPClient().setFileType(FTPClient.ASCII_FILE_TYPE);
			} else {
				exec.getFTPClient().setFileType(FTPClient.BINARY_FILE_TYPE);
			}
			return null;
		};
	}
	
	

}
