package api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import DBUtils.HibernateUtil;

@Path("/userservice/")
@Produces("application/xml")
public class UserService {

	public UserService() {
	}

	@GET
	@Path("/users/")
	@Produces("application/json")
	public Response getUsers() {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession(); 
		
		Query query = session.createQuery("from "+User.class.getName());
		List<User> list = query.list();
		
		session.close();
		final GenericEntity<List<User>> entity = new GenericEntity<List<User>>(list) { };
		return Response.ok().entity(entity).build();
	}

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response getUser(@PathParam("id") String id) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession(); 
		
		User user = (User) session.get(User.class, id);
		
		session.close();
		
		return Response.ok(user).build();
	}

	@PUT
	@Path("/users/{id}")
	@Consumes({"application/json", "text/xml"})
	@Produces("application/json")
	public Response updateUser(@PathParam("id") String id, User user) {
		return Response.ok().build();
	}

	@POST
	@Path("/users")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addUser(User user) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession(); 
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
	
		session.close();
		return Response.ok(user).build();
	}

	@DELETE
	@Path("/users/{id}/")
	public Response deleteUser(@PathParam("id") String id) {
		return Response.ok().build();
	}

}
