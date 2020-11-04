package edu.cscc;

import java.io.*;
import java.net.Socket;

/**
 * RequestHandler - handle HTTP GET Requests
 * (ignore anything else)
 * @author student name
 */
public class RequestHandler {
    private Socket connection;

    /**
     * Constructor
     */
    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    /**
     * Process an HTTP request
     * @throws IOException 
     */
    public void processRequest() throws IOException {
        try {
			String requestString = "GET / HTTP/1.1";
			HTTPRequest httpRequest = new HTTPRequest(requestString);
			ResponseHandler responseHandler = new ResponseHandler(httpRequest);
			responseHandler.sendResponse(connection);
		} catch (IOException e) {
			TinyWS.fatalError(e);
		} finally {
			connection.close();
		}
    }

    // Read an HTTP Request
    private String readRequest() throws IOException {
        // Set socket timeout to 500 milliseconds
        connection.setSoTimeout(500);
        int recbufsize = connection.getReceiveBufferSize();
        InputStream in = connection.getInputStream();
        InputStreamReader rdr = new InputStreamReader(in);
        BufferedReader brdr = new BufferedReader(rdr);
        StringBuilder reqBuf = new StringBuilder();
        char[] cbuf = new char[recbufsize];
        
        String line = brdr.readLine();
        System.out.println(line);
        while (line != "\n" && line != null) {
        	reqBuf.append(line);
        	line = brdr.readLine();
        }
        
        TinyWS.log("Loop Complete.");

        return reqBuf.toString();
    }
}