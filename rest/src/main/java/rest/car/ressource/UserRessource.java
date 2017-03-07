package main.java.rest.car.ressource;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@Path("/test")
public class UserRessource {
	
	@GET
	@Path("/user/{name}")
	public String user(@PathParam("name") String name){
		if (name.equals("tim")){
			return "give password";
		}
		return "loggin incorrect";
	}
	
	
    @POST
    @Path("/sendemail")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendEmail(@QueryParam("email") String email) {
        System.out.println(email);
        return "test mail";
        //return Response.ok("email=" + email).build();
    }

}
