package si.fri.rso.escooterental.escootercatalog.api.v1.resources;
import si.fri.escooterental.escootercatalog.models.entities.EscooterCatalogEntity;
import si.fri.rso.escooterental.escootercatalog.lib.EscooterCatalog;
import si.fri.rso.escooterental.escootercatalog.services.beans.EscooterCatalogBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/escooters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EscooterCatalogResource {

    @Inject
    private EscooterCatalogBean escooterBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getEscooterCatalog(){
        List<EscooterCatalog> escooters = escooterBean.getEscooterCatalog();
        return Response.status(Response.Status.OK).entity(escooters).build();
    }

    @GET
    @Path("/{EscooterId}")
    public Response getEscooterCatalog(@PathParam("EscooterId") Integer EscooterId){

        EscooterCatalog escooter = escooterBean.getEscooter(EscooterId);
        if (escooter == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(escooter).build();
//        return Response.status(Response.Status.OK).entity().build();
    }

    @POST
    public Response createEscooterCatalog(EscooterCatalog escoot){
        if (escoot.getScooterName() == null || escoot.getScooterPrice() == null || escoot.getScooterName() == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            escoot = escooterBean.createEscooterCatalog(escoot);
        }

        return Response.status(Response.Status.CONFLICT).entity(escoot).build();
    }

    @PUT
    @Path("{escooterId}")
    public Response changeEscooterCatalog(@PathParam("escooterId") Integer Id, EscooterCatalog escooter){
        escooter = escooterBean.putEscooterCatalog(Id,escooter);

        if (escooter == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    @DELETE
    @Path("{escooterId}")
    public Response deleteEscooterCatalog(@PathParam("escooterId") Integer escooterId) {

        boolean deleted = escooterBean.deleteEscooterCatalog(escooterId);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



}
