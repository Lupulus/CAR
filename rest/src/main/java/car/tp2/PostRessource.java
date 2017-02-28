package main.java.car.tp2;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Exemple de ressource REST accessible a l'adresse :
 * 
 * 		http://localhost:8080/rest/tp2/helloworld
 * 
 * @author Lionel Seinturier <Lionel.Seinturier@univ-lille1.fr>
 */
@Path("/post")
public class PostRessource {

	@POST
	@Consumes("text/html")
	public String sayHello() {
		return "<h1>Hello World It's a beautiful day</h1>";
	}

	 @POST
	 @Path("/book/{isbn}")
	 public String getBook( @PathParam("isbn") String isbn ) {
		 return "Book: "+isbn;		 
	 }

	 @POST
	 @Path("{var: .*}/stuff")
	 public String getStuff( @PathParam("var") String stuff ) {
		 return "Stuff: "+stuff;
	 }
}
