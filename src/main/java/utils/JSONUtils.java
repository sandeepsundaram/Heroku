package utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtils {
	
	private JSONObject jObj = null;
	private Object value = null;
	
	public JSONUtils initialize(String json) throws JSONException {
		JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
		jObj = object;
		return this;
	}
	
	public JSONUtils getValue(String key) {
		try {
			value = jObj.getString(key);		
		} catch (Exception e) {
			handlException(e);
		}
		
		return this;
	}
	
	public String getStringValue() {
		if(value instanceof String && value != null)
			return value.toString();
		
		return null;
	}
	
	public JSONObject getJSONObject() {
		if(value instanceof JSONObject && value != null)
			return (JSONObject) value;
		
		return null;
	}	
	
	public void handlException(Exception e) {
		e.printStackTrace();
		value = null;
	}

}
