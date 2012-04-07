package api;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

import DBUtils.HibernateUtil;

@Path("/userservice/")
@Produces("application/xml")
public class UserService {

	public UserService() {
	}

	@GET
	public User getUsers() {
		return null;
	}

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public String getUser(@PathParam("id") String id) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		User user = new User();
		user.setDob(new Date(2011, 4, 8));
		user.setId(id);
		user.setName("Sandeep");
		
		session.save(user);
		
		tx.commit();
		
		JSONObject obj = new JSONObject(user);
		return obj.toString();
	}

	@PUT
	@Path("/users/{id}")
	@Consumes("application/xml")
	public Response updateUser(@PathParam("id") Long id, User User) {
		return Response.ok().build();
	}

	@POST
	@Path("/users")
	public Response addUser(User User) {
		return Response.ok().build();
	}

	@DELETE
	@Path("/users/{id}/")
	public Response deleteUser(@PathParam("id") String id) {
		return Response.ok().build();
	}

}
