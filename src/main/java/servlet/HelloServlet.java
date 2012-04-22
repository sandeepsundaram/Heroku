package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OAuthRequest;
import model.Request;
import model.Response;
import model.Verb;
import model.Zodiac;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import utils.JSONUtils;
import utils.URLUtils;
import utils.ZodiacUtil;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = -930386857509367419L;
	private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 

		try { 

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
				if(body != null) {
					String[] tokens = body.split("&");
					tokens = tokens[0].split("=");

					String accessToken = null;
					if (tokens[0].equals("access_token")) {
						accessToken = tokens[1];
					}

					OAuthRequest oRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
					oRequest.addQuerystringParameter("access_token", accessToken);
					Response oResponse = oRequest.send();
					if(oResponse != null && oResponse.getCode() == 200) {
						String fbBody = oResponse.getBody();
						try {			
							request.getSession().setAttribute("fbBody", fbBody);	
							JSONUtils jUtils = new JSONUtils();
							jUtils.initialize(fbBody);
							String name = jUtils.getValue("name").getStringValue();
							String bio = jUtils.getValue("bio").getStringValue();				
							String dob = jUtils.getValue("birthday").getStringValue();
							String id = jUtils.getValue("id").getStringValue();

							if(dob != null) {
								SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
								Date date = formatter.parse(dob); 
								request.getSession().setAttribute("dob", dob);	
								Zodiac zod = ZodiacUtil.getZodiac(date);
								zod.setBio(bio);
								zod.setName(name);
								request.getSession().setAttribute("zodiac", zod);
							} else {
								request.getSession().setAttribute("zodiac", new Zodiac());
							}
						} catch (JSONException e) {
							handleError(e);
						} catch (ParseException e) {
							handleError(e);
						}		
					}
				} 
				RequestDispatcher rd = request.getRequestDispatcher("zodiac.jsp");
				rd.forward(request, response);
			} else if(resp != null) {			
				String body = resp.getBody();
				JSONObject object;
				try {
					ServletOutputStream out = response.getOutputStream();
					object = (JSONObject) new JSONTokener(body).nextValue();
					String error = object.getString("error");
					out.write(error.getBytes());
					out.flush();
					out.close();
				} catch (Exception e) {
					handleError(e);	
				}		
			}
		}
		catch (Throwable e) {	
			handleError(e);
		}

	}

	private void handleError(Throwable e) throws ServletException {
		e.printStackTrace();
		throw new ServletException(e);
	}


}
