package car.tp2.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Exemple de ressource REST accessible a l'adresse :
 * 
 * 		http://localhost:8080/rest/tp2/helloworld
 * 
 * @author Lionel Seinturier <Lionel.Seinturier@univ-lille1.fr>
 */
@Path("/web")
public class WebResource {

	@GET
	@Produces("text/html")
	public String login() {
		String form ="<form method='get' action='http://localhost:8080/rest/tp2/dir/login'>";
		form+="<label for='pseudo'>Pseudo :</label><input name='pseudo' type='text' id='pseudo'  /><br />";
		form+="<label for='password'>Mot de Passe :</label><input type='password' name='password' id='password'/>";
		form+= "<input type='submit' value='Connexion'/></p></form>";
	
		return form;
	}

	
//	 @GET
//	 @Path("/book/{isbn}")
//	 public String getBook( @PathParam("isbn") String isbn ) {
//		 return "Book: "+isbn;		 
//	 }
//
//	 @GET
//	 @Path("{var: .*}/stuff")
//	 public String getStuff( @PathParam("var") String stuff ) {
//		 return "Stuff: "+stuff;
//	 }
}

