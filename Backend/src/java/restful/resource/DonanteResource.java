package restful.resource;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import restful.Model.DonanteModel;
import restful.service.DonanteService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

@Path("donantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DonanteResource {

    DonanteService servicio = new DonanteService();

    @GET
    public ArrayList<DonanteModel> getClientes() {

        return servicio.getDonantes();
    }
    
    @Path("/{cedula}")
    @GET
    public DonanteModel getCliente(@PathParam("cedula") String id) {

        return servicio.getDonante(id);
    }
    
    @POST
    public DonanteModel addDonante(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        DonanteModel donante = gson.fromJson(JSON, DonanteModel.class);
        return servicio.addDonante(donante);
    }
    
    @PUT
    public DonanteModel updateDonante(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        DonanteModel donante = gson.fromJson(JSON, DonanteModel.class);
        return servicio.updateDonante(donante);
    }
    
    @DELETE
    @Path("/{cedula}")
    public String delDonante(@PathParam("cedula") String id) {

        return servicio.delDonante(id);

    }
}
