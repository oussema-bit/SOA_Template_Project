package ressources;


import entities.Logement;
import metiers.LogementBusiness;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("logement")
public class LogementRessources {
    private static LogementBusiness logementBusiness=new LogementBusiness();

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        if(logementBusiness.getLogements()!=null)
            return Response.status(Response.Status.OK).entity(logementBusiness.getLogements()).build();
        else return Response.status(Response.Status.NOT_FOUND).entity("Liste vide !!").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam(value= "id")int id){
        Logement logement=logementBusiness.getLogementsByReference(id);
        return Response.status(Response.Status.OK).entity(logement).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteById(@PathParam(value = "id") int id){
        if(logementBusiness.deleteLogement(id))
            return Response.status(Response.Status.OK).entity("Suppression avec Success !!").build();
        else return Response.status(Response.Status.NOT_FOUND).entity("Suppression échouée !!").build();
    }

    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateById(@PathParam(value="id") int id,Logement logement){
        if(logementBusiness.updateLogement(id,logement))
            return Response.status(Response.Status.OK).entity("Modification effectuée aves success !!").build();
        else return Response.status(Response.Status.NOT_FOUND).entity("Modification échouée !!").build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Logement logement){
        if(logementBusiness.addLogement(logement))
            return Response.status(Response.Status.OK).entity("Ajout effectuée aves success !!").header("Access-Control-Allow-Origin","*").build();
        else return Response.status(Response.Status.NOT_FOUND).entity("Ajout échouée !!").build();
    }
}
