package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/customerservice/")
@Produces("application/xml")
public class CustomerService {

	public CustomerService() {
	}

	@GET
	public Customer getCustomers() {
		return null;
	}

	@GET
	@Path("/customers/{id}")
	@Produces("application/json")
	public String getCustomer(@PathParam("id") String id) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("customer", id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString(); 
	}

	@PUT
	@Path("/customers/{id}")
	@Consumes("application/xml")
	public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
		return Response.ok().build();
	}

	@POST
	@Path("/customers")
	public Response addCustomer(Customer customer) {
		return Response.ok().build();
	}

	@DELETE
	@Path("/customers/{id}/")
	public Response deleteCustomer(@PathParam("id") String id) {
		return Response.ok().build();
	}

}
