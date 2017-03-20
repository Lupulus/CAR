package car.tp2.ressource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import car.tp2.ftpclient.FtpExec;
import car.tp2.ftpclient.ResponseFactory;

@Path("/dir")
public class DirRessource extends AbstractRessource {
	
	/**
	 * Liste le contenu du r�pertoire path
	 * @param path Chemin du r�pertoire
	 * @param auth (Authentification n�cessaire)
	 * @param transfertMode Mode de transfert (active/passive)
	 * @return Retourne la r�ponse au format JSON contenant la liste des fichiers avec 
	 * leurs informations. Sinon une erreur. 
	 * @throws Exception
	 */
	@GET
	@Path("{path:.*}")
	public Response getDir(@PathParam("path") String path, @HeaderParam("Authorization") String auth,
				@QueryParam("transfertMode") String transfertMode) throws Exception {
		String absPath = "/" + path;
		return new FtpExec()
				.addAction(getBasicLoginHandler(auth))
				.addAction(getTransfertModeHandler(transfertMode))
				.addAction((exec) -> {
					FTPClient cl = exec.getFTPClient();
								
					FTPFile[] files = cl.listFiles(absPath); // r�cup�ration du contenu du dossier depuis le serveur
					
					// si ce n'est pas un dossier
					if (files == null || !FTPReply.isPositiveCompletion(cl.getReplyCode())) {
						return ResponseFactory.jsonFTPResponse(Status.NOT_FOUND,
								cl.getReplyCode(),
								cl.getReplyStrings());
					}
					
					// construction de la r�ponse
					JsonArray jsonFiles = new JsonArray();
					for (FTPFile file : files) {
						JsonObject jsonFile = new JsonObject();
						jsonFile.addProperty("name", file.getName());
						jsonFile.addProperty("type",
								(file.getType() == FTPFile.DIRECTORY_TYPE) ? "DIR" : "FILE");
						jsonFile.addProperty("lastModified", file.getTimestamp().getTimeInMillis()/1000);
						jsonFile.addProperty("size", file.getSize());
						jsonFile.addProperty("owner", file.getUser());
						jsonFile.addProperty("group", file.getGroup());
						jsonFiles.add(jsonFile);
					}
					JsonObject root = new JsonObject();
					root.addProperty("directoryPath", absPath);
					root.add("directoryContent", jsonFiles);
					return ResponseFactory.jsonResponse(Status.OK, root);
					
				})
				.addAction(getFallbackHandler())
				.execute();
	}
	
	

	/**
	 * Permet de supprimer un repertoire.
	 * @param path Chemin du r�pertoire
	 * @param auth (Authentification n�cessaire)
	 * @param transfertMode mode de transfert (active/passive)
	 * @return Retourne une reponse dans un objet JSON : Si l'action c'est mal pass�e, un code d'erreur est renvoy�. 
	 */
	@DELETE
	@Path("{path:.+}")
	public Response deleteDir(@PathParam("path") String path, @HeaderParam("Authorization") String auth,
			@QueryParam("transfertMode") String transfertMode) {
		String absPath = "/" + path;
		return new FtpExec()
				.addAction(getBasicLoginHandler(auth))
				.addAction(getTransfertModeHandler(transfertMode))
				.addAction((exec) -> {
					FTPClient cl = exec.getFTPClient();
					if (cl.removeDirectory(absPath)) {
						return ResponseFactory.jsonFTPResponse(Status.OK, cl.getReplyCode(), cl.getReplyStrings());
					}
					return ResponseFactory.jsonFTPResponse(Status.NOT_FOUND, cl.getReplyCode(), cl.getReplyStrings());
				})
				.addAction(getFallbackHandler())
				.execute();
		
	}
	
	
	/**
	 * Permet de cr�er un r�pertoire
	 * @param path Chemin du r�pertoire
	 * @param auth Authentification
	 * @return Retourne une reponse dans un Objet JSON. Si l'action c'est mal pass�e, un code d'erreur HTTP 
	 * ainsi qu'un code d'erreur FTP est renvoy�. 
	 */
	@POST
	@Path("{path:.+}")
	public Response postDir(@PathParam("path") String path, @HeaderParam("Authorization") String auth) {
		String absPath = "/" + path;
		return new FtpExec()
				.addAction(getBasicLoginHandler(auth))
				.addAction((exec) -> {
					FTPClient cl = exec.getFTPClient();
					if(cl.makeDirectory(absPath)) {
						return ResponseFactory.jsonFTPResponse(Status.CREATED,
								cl.getReplyCode(), cl.getReplyStrings());
					} else if (cl.getReplyCode() == 550){
						return ResponseFactory.jsonFTPResponse(Status.CONFLICT,
								cl.getReplyCode(), cl.getReplyStrings());
					}
					return null;
				})
				.addAction(getFallbackHandler())
				.execute();
	}
}

	
	
