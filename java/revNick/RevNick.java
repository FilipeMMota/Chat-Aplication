package revNick;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author filip
 */
@Path("revnick")
public class RevNick {

   @Context
 private UriInfo context;
 /**
 * Creates a new instance of RevNick
 */
 public RevNick() {
 }
 /**
 * Retrieves representation of an instance of revNick.RevNick
 * @param port
 * @return an instance of java.lang.String
 * URL de teste
 * http://localhost:8080/RevNick/webresources/revnick?port=8080
 */
    @GET
    @Consumes("text/plain")
    @Produces("text/html")
    public Response getName(
        @QueryParam("port") int port){
        String res="0000";
        if(port == 8080){
            res="Batata";}
        if(port == 8081){
            res="Cebola";}
        if(port == 8082){
            res="Alface";}
        if(port == 8083){
            res="Cenoura";}
        return Response.status(200).entity(res).build();
    }
}