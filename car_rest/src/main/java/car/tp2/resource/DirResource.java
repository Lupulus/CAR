package car.tp2.resource;


import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import car.tp2.ftpclient.FtpExec;

/*
 * Ressource contenantles diff�rentes r�quetes FTP
 */
@Path("/dir")
public class DirResource {
	
	FtpExec ftp = new FtpExec();

	/*
	 * Connexion au serveur ftp 
	 * 
	 * @param login
	 * @param password
	 * @return 
	 */
	 @GET
	 @Path("login")
	 @Produces("text/html")
	 public String Connection(@QueryParam("pseudo") String pseudo,@QueryParam("password") String password) throws  IOException{
		 return ftp.connect(pseudo, password);
	 }
	 /*
	  * Liste le repertoire root
	  * @return le contenu du repertoire parent
	  */
	 @GET
	 @Produces("text/html")
	 @Path("list")
	 public String listFiles() throws  IOException{
		 
		 return this.ftp.ls(this.ftp.getCurrentDirectory());
	 }
	 
	 /*
	  * Liste le repertoire
	  * @param repertoire
	  * @return le contenu du repertoire 
	  */
	 @GET
	 @Produces("text/html")
	 @Path("list/{directory}")
	 public String listFiles(@PathParam("directory") String directory) throws  IOException{
	     return ftp.ls(directory);
	 }
	 /*
	  * Cree un nouveau repertoire
	  * @param nom du repertoire
	  * @return
	  */
	 @GET
	 @Produces("text/html")
	 @Path("mkdir/{directory}")
	 public String mkdir(@PathParam("directory") String directory) throws  IOException{
	     return ftp.mkdir(directory);
	 }
	 
	 /*
	  * Deplace dans un repertoire
	  * @param chemin du repertoire
	  * @return
	  */
	 @GET
	 @Produces("text/html")
	 @Path("cd/{directory}")
	 public String cd(@PathParam("directory") String directory) throws  IOException{
		 
	     return ftp.cd(directory);
	 }
	 /*
	  * Remonte le repertoire parent
	  * @return
	  */
	 @GET
	 @Produces("text/html")
	 @Path("cdup")
	 public String cdup() throws  IOException{
		 
	     return ftp.cdup();
	 }
	 /*
	  * Afficher le chemin absolu du repertoire courant
	  * @return
	  */
	 @GET
	 @Produces("text/html")
	 @Path("pwd")
	 public String pwd() throws  IOException{
	     return ftp.pwd();
	 }
	 
	 /*
	  * Renomme un fichier ou un repertoire
	  * @param oldName le repertoire
	  * @param newName le nouveau nom du repertoire
	  * @return 
	  */
	 @PUT
	 @Produces("text/html")
	 @Path("rename/{oldName}")
	 public String rename(@PathParam("oldName")String oldName, @QueryParam("newName")String newName) throws  IOException{
		
		 if (newName==null)
			 newName=oldName;
		 this.ftp.renameFile(oldName, newName);
		
		 return "ok";
	 }
	 
	 /*
	  * Supprimer un fichier ou un repertoire
	  *@param pathname fichier � supprimer
	  *@return resultat de la r�quete
	  */
	 @DELETE
	 @Produces("text/html")
	 @Path("delete/{pathname}")
	 public String delete(@PathParam("pathname")String pathname) throws IOException{
		
		 return this.ftp.deleteFile(pathname);	
	 }
	 
	 /*
	  * Deconnecte sur le serveur FTP
	  * 
	  */
	 @GET
	 @Produces("text/html")
	 @Path("disconnect")
	 public String disconnect() throws  IOException{
	     return ftp.disconnet();
	 }
	 /*
	  * Recupere un fichier depuis le serveur FTP
	  * @param le fichier
	  * @return
	  */
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("getFile/{pathname}")
	public Response getFile(@PathParam("pathname")String pathname) throws  IOException{
		
		return Response.ok(this.ftp.getFile(pathname),MediaType.APPLICATION_OCTET_STREAM).build();
	}
	/*
	 * upload un fichier dans le serveur FTP
	 * dans le repertoire courant
	 * @param fichier
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("sendFile/{pathname}")
	public Response sendFile(@PathParam("pathname")String pathname) throws  IOException{
		
		return Response.ok(this.ftp.sendFile(pathname),MediaType.APPLICATION_OCTET_STREAM).build();
	}
	
}


	
	