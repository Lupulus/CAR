package car.tp2.ressource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/*
 * 
 * Ressource  de connexion au serveur FTP
 * @Path le chemin 
 */
@Path("/")
public class WebRessource {

	/*
	 *	Crée le formulaire permettant à l'utilisateur de se connecter serveur FTP.
	 *	Le formulaire est composé de 2 champs:
	 *		login
	 *		password
	 * 
	 *  @return form , formulaire
	 */
	@GET
	@Produces("text/html")
	public String connection(){
		
		String form ="<form method='get' action='http://localhost:8080/rest/ftprest/login'>";
		form+="<label for='pseudo'>Pseudo :</label><input name='pseudo' type='text' id='pseudo'  /><br />";
		form+="<label for='password'>Mot de Passe :</label><input type='password' name='password' id='password'/>";
		form+= "<input type='submit' value='Connexion'/></p></form>";
	
		return form;
	}
}