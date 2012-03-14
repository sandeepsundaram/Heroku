package servlet;

import java.io.IOException;

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
		ServletOutputStream out = response.getOutputStream();
		String code = request.getParameter("code");
		out.println();

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
				JSONObject object = (JSONObject) new JSONTokener(fbBody).nextValue();
				String name = object.getString("name");
				String bio = object.getString("bio");
				out.println("<b>name</b> : "+name);
				out.println("<b>bio</b> : "+bio);
			} catch (JSONException e) {
				e.printStackTrace();
			}				
		} else {
			String body = resp.getBody();
			JSONObject object;
			try {
				object = (JSONObject) new JSONTokener(body).nextValue();
				String error = object.getString("error");
				out.println(error);
			} catch (JSONException e) {
				e.printStackTrace();
			}			
		}        
		out.flush();
		out.close();
	}

}
