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

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import DBUtils.DBUtil;
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

		Session session = null;
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession(); 

			Query query = session.createQuery("from "+User.class.getName());
			List<User> list = query.list();

			final GenericEntity<List<User>> entity = new GenericEntity<List<User>>(list) { };
			return Response.ok().entity(entity).build();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if(session != null)
				session.close();
			if(factory != null)
				factory.close();
		}

		return Response.serverError().build();
	}

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response getUser(@PathParam("id") String id) {
		SessionFactory factory = null;
		Session session = null;
		User user = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession(); 

			user = (User) session.get(User.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session != null)
				session.close();
			if(factory != null)
				factory.close();
		}

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

		Session session = null;
		Transaction tx = null;
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession(); 
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if(session != null)
				session.close();
			if(factory != null)
				factory.close();
		}
		return Response.ok(user).build();
	}

	@DELETE
	@Path("/users/{id}/")
	public Response deleteUser(@PathParam("id") String id) {
		return Response.ok().build();
	}

	@DELETE
	@Path("/users/DBCleanup/")
	public Response dbCleanUp() {
		try {
			DBUtil.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}

}
