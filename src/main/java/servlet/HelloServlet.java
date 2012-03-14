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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    	    
    	    // Now let's go and ask for a protected resource!
    	    out.println("Now we're going to access a protected resource...");
    	    OAuthRequest oRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    	    oRequest.addQuerystringParameter("access_token", accessToken);
    	    Response oResponse = oRequest.send();
    	    out.println();
    	    out.println(oResponse.getCode());
    	    out.println(oResponse.getBody());
        } else {
        	String body = resp.getBody();
        	JSONParser parser = new JSONParser();
        	JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(body);
	        	JSONObject error= (JSONObject)obj.get("error");
	        	out.println(error.get("message").toString());				
			} catch (ParseException e) {
				e.printStackTrace();
			}   
        }        
        out.flush();
        out.close();
    }
    
}
