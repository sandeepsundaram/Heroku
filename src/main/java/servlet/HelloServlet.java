package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OAuthRequest;
import model.Request;
import model.Response;
import model.Verb;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import utils.JSONUtils;
import utils.URLUtils;

@WebServlet(
		name = "MyServlet", 
		urlPatterns = {"/hello"}		
		)
public class HelloServlet extends HttpServlet {

	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code = request.getParameter("code");

		String accesstokenURL = "https://graph.facebook.com/oauth/access_token?" + 
				"client_id=%s&redirect_uri=%s&client_secret=%s&code=%s";
		
		accesstokenURL = String.format(accesstokenURL, "284063838333812", 
				URLUtils.urlEncodeWrapper("http://afternoon-galaxy-4740.herokuapp.com/hello"), 
				"2f00e440583d9fb8384b2a399e22b69e", code);		

		Request req = new Request(Verb.GET, accesstokenURL);
		Response resp = req.send();
		if(resp.getCode() == 200) {
			String body = resp.getBody();
			String[] tokens = body.split("&");
			tokens = tokens[0].split("=");
			String accessToken = tokens[1];

			OAuthRequest oRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			oRequest.addQuerystringParameter("access_token", accessToken);
			Response oResponse = oRequest.send();
			String fbBody = oResponse.getBody();
			try {
				request.getSession().setAttribute("fbBody", fbBody);
				JSONUtils jUtils = new JSONUtils();
				jUtils.initialize(fbBody);
				String name = jUtils.getValue("name").getStringValue();
				request.getSession().setAttribute("name", name);
				String bio = jUtils.getValue("bio").getStringValue();
				request.getSession().setAttribute("bio", bio);	
				String dob = jUtils.getValue("birthday").getStringValue();
				if(dob != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					Date date = formatter.parse(dob); 
					request.getSession().setAttribute("dob", dob);	
				}
			} catch (JSONException e) {
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		} else {
			String body = resp.getBody();
			JSONObject object;
			try {
				ServletOutputStream out = response.getOutputStream();
				object = (JSONObject) new JSONTokener(body).nextValue();
				String error = object.getString("error");
				out.write(error.getBytes());
				out.flush();
				out.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}			
		}     
		
		RequestDispatcher rd = request.getRequestDispatcher("showDetails.jsp");
		rd.forward(request, response);
	}

}
