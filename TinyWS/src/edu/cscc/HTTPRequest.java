package edu.cscc;

/**
 * HTTPRequest - parse HTTP Requests (actually parse a small part of a GET
 * Request: GET [filepath])
 * 
 * @author student name
 */
public class HTTPRequest {
	private String request; // request string
	private String path; // path to file
	private boolean validRequest; // is request valid?

	/**
	 * Constructor
	 * 
	 * @param r - HTTP request string to be parsed
	 */
	public HTTPRequest(String r) {

		validRequest = parse(r);
	}

	/**
	 * Is the request valid
	 */
	public boolean isValidRequest() {

		return (validRequest);
	}

	/**
	 * Return file path for request
	 */
	public String getPath() {

		return (path);
	}

	/**
	 * Parse an HTTP request
	 */
	private boolean parse(String r) {
		boolean valid = false;
		if (r != "" && r != null) {
			String[] arrR = r.split("[ \t\n?]");
			if (arrR.length >= 2) {
				request = arrR[0];
				path = arrR[1];
				if ("GET".equals(request) && path != "" && path != null) {
					valid = true;
				}
			}
		}
		return valid;
	}
}