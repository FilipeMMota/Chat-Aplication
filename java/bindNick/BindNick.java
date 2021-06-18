
package bindNick;

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
@Path("bindnick")
public class BindNick {

   @Context
 private UriInfo context;
 /**
 * Creates a new instance of BindNick
 */
 public BindNick() {
 }
 /**
 * Retrieves representation of an instance of bindNick.BindNick
 * @param nick
 * @return an instance of java.lang.String
 * URL de teste
 * http://localhost:8080/BindNick/webresources/bindnick?nick=batata
 */
    @GET
    @Consumes("text/plain")
    @Produces("text/html")
    public Response getName(
        @QueryParam("nick") String nick){
        String res="0000";
        if(nick.equalsIgnoreCase("batata")){
            res="" + 8080;}
        if(nick.equalsIgnoreCase("cebola")){
            res="" + 8081;}
        if(nick.equalsIgnoreCase("alface")){
            res="" + 8082;}
        if(nick.equalsIgnoreCase("cenoura")){
            res="" + 8083;}
        return Response.status(200).entity(res).build();
    }
}
