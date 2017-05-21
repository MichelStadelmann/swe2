package network;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientModel {
	public String browse(String ipAddress,Integer port){
		Socket s = null;
		OutputStreamWriter out = null;
		BufferedReader inReader = null;
		String lineIn;
		StringBuffer urlContent = new StringBuffer();
		
		try{
			
			s = new Socket(ipAddress, port);
			out = new OutputStreamWriter(s.getOutputStream());
			out.write("Hello");
			
		}
		
		catch{
			
			
		}finally{
			
		}
		
		
		
	}

}
