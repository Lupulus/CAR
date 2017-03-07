package main.java.rest.car.ressource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Exemple de ressource REST accessible a l'adresse :
 * 
 * 		http://localhost:8080/rest/tp2/helloworld
 * 
 * @author Lionel Seinturier <Lionel.Seinturier@univ-lille1.fr>
 */
@Path("/try")
public class TryRessource {

	@GET
	@Produces("text/html")
	public String sayHello() {
		return "";
	}

	 @GET
	 @Path("/book/{isbn}")
	 public String getBook( @PathParam("isbn") String isbn ) {
		 return "Book: "+isbn;		 
	 }

	 @GET
	 @Path("{var: .*}/stuff")
	 public String getStuff( @PathParam("var") String stuff ) {
		 return "Stuff: "+stuff;
	 }
}