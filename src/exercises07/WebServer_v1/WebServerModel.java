package exercises07.WebServer_v1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import javafx.concurrent.Task;

public class WebServerModel {
	private Integer port;
	private final Logger logger = Logger.getLogger("");
	
	final Task<Void> serverTask = new Task<Void>(){
		
		protected Void call() throws Exception{
			try{
				ServerSocket listener = new ServerSocket(port, 10, null);
				logger.info("Listening on port " +port);
				
				while (true){
					// The "accept" method waits for a request, the creates a socket
					// connected to the requesting client
					Socket client = listener.accept();
					
					logger.info("Request from client "
							+ client.getInetAddress().toString()+ " for server"
							+ client.getLocalAddress().toString());
					
					// Create input and output streams to talk to the client
					BufferedReader inClient = new BufferedReader (new InputStreamReader(client.getInputStream()));
					OutputStream outClientBinary = client.getOutputStream();
					PrintWriter outClientText = new PrintWriter(outClientBinary);
					
					// Read request from client and send it straight back
					// An empty string (length 0) ist the end of an HTTP request
					StringBuilder received = new StringBuilder();
					String inString;
					while ((inString = inClient.readLine()) != null && inString.length() != 0){
						received.append(inString + "\n");
					}
					
					// Parse the fie name, and place it within our web contet directory
					String fileName = parseGetRequest(received.toString());
					logger.info("Requested file. " + fileName + "\n");
					
					// Determine wether or not the file exists, and is readable
					File file = null;
					if (fileName != null) file = new File(fileName);
					if (file == null || !file.exists() || file.isDirectory() || !file.canRead()){
					// No good, status 404
					// Send our reply using HTTP 1.0 - we could also use the "write" method
					logger.info("Status 400\n");
					outClientText.print("HTTP/1.0 404 \r\n"); //Version and Status
					outClientText.print("Content-Type: text/plan\r\n");
					outClientText.print("\r\n");
					outClientText.print("File not found\n");
				}	else{
					// All ok, status 200
					// Send our reply usting HTTP 1.0 - we could als use the "write" method
					logger.info("Status 200\n");
					outClientText.print("HTTP/1.0 404 \r\n"); //Version and Status
					String mediaType = getMediaType(fileName);
					outClientText.print("Content-Type: " +mediaType + "\r\n");
					outClientText.print("\r\n");
					
					if (mediaType.startsWith("text")){
						sendTextFile(outClientText, file);
					} else {
						sendBinaryFile(outClientBinary,file);
					}
					logger.info("File sent");	
					
				}
				
				outClientText.flush(); // Be safe, always "flush";
				outClientText.close();
				inClient.close();
				client.close();
			}
			
			
		} catch (Exception e){
			System.err.println(e);
		}
		
		return null;
	}
	
	};
	
	/**
	 * We just use a simple 2-dimensional array to map file extension to media types.
	 * You could also use an enumeration, or a HashMap, or...
	 * 
	 * If we cannot determine the type, we default to the application/octet-stream,
	 * which is used for arbitrary binary data.
	 * 
	 * @param fileName The file name of the file to be sent
	 * @return The media type for the HTTP header
	 */

	private String getMediaType(String fileName) {
		final String[][] mediaTypes = {
				{"html", "text/html"},
				{"htm", "text/html"},
				{"css", "text/css"},
				{"xml", "text/xml"},
				{"txt", "text/plain"},
				{"jpg", "image/jpeg"},
				{"gif", "image/gif"},
				{"png", "image/png"},
				{"ico", "images/x-icon"}
				
		
		};
		
		String mediaType = "application/octet-stream";
		int extensionStart = fileName.indexOf('.')+1;
		if (extensionStart ==0) extensionStart = fileName.length();
		String fileExtension = fileName.substring(extensionStart,fileName.length());
		for (String[] mediaEntry:mediaTypes){
			if (mediaEntry[0].equals(fileExtension)){
				mediaType = mediaEntry[1];
				break;
			}
		}
		logger.info("File extension'" + fileExtension + "'maps to media type " +mediaType);
		return mediaType;
	}
	
	/**
	 * Send a binary file
	 * @param outClientBinary The OutputStream
	 * @param file
	 */

	private void sendBinaryFile(OutputStream outClient, File file) {
		BufferedInputStream inFile = null;
		try{
			inFile = new BufferedInputStream(new FileInputStream(file));
			int b = inFile.read();
			while (b > -1){
				outClient.write(b);
				b = inFile.read();
			}
		} catch (Exception e){
			logger.severe(e.toString());
		} finally{
			if (inFile != null) try {inFile.close(); } catch (Exception e){}
		}
		
	}
	
	/**
	 * Examine the incoming request. If it is a GET request, locate and return the requested file
	 * name. IF the request is not a GET request, or if we have any problem, return null.
	 * 
	 * Note: we assume that the root of our web content is a subdirectory "www" underneath the
	 * working directory. Hence: a request for "woof.html" will become "www/woof.html", and a request
	 * for "/path/to/woof.html" will becomme "www/path/to/woof.html"
	 * @param request Incoming request read from the client
	 * @return Name of the requested file, or else null
	 */
	

	private String parseGetRequest(String request) {
		String fileName = null;
		
		if (request.regionMatches(0, "GET", 0, 4)){
			int fileNameEnd = request.indexOf(" ",4);
			fileName = request.substring(4,fileNameEnd);
		}
		
		if (fileName != null){
			if (fileName.charAt(0) == '/'){
				fileName = "www" + fileName;
			}else{
				fileName = "www/" + fileName;
			}
		}
		return fileName;
		
	}
	
	/**
	 * Send a text file line-by-line
	 * @param outClientText The print writer for the client
	 * @param file The file to send
	 */

	private void sendTextFile(PrintWriter outClient, File file) {
		BufferedReader inFile = null;
		try{
			inFile = new BufferedReader(new FileReader(file));
			String line = inFile.readLine();
			while (line!=null){
				outClient.println(line);
				line = inFile.readLine();
			}
		} catch (Exception e){
			logger.severe(e.toString());
		}finally{
			if (inFile != null) try { inFile.close();} catch (Exception e){}
		}
		
	}
	
	/**
	 * Called by the controller, to start the task, to serve web content
	 * @param port
	 */
	
	
	public void serveContent(Integer port){
		this. port = port;
		new Thread(serverTask).start();
	}
	
	
	

}
