import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
 

public class OtherHttpServer {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/info", new InfoHandler());
    server.createContext("/get", new GetHandler());
    server.createContext("/shutdown", new ShutdownHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  static class InfoHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      String response = "Use /get to download an image";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }

 static class ShutdownHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {


System.out.println("In the shutdown handler");
String command = t.getRequestURI().getQuery();
System.out.println(command);
String res = executeCommand(command);
System.out.println(res);
//String part1 = readFile("part1.html");
//String part2 = readFile("part2.html");

String response ="nothing!!!";
//response = part1+res+part2;
//System.out.println(response);


	Headers h = t.getResponseHeaders();
     	// h.add("Content-Type", "text/html");

 	t.sendResponseHeaders(200, res.length());
     	 OutputStream os = t.getResponseBody();
     	 os.write(res.getBytes());
      	os.close();


    }
  }

  static class GetHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {

String captured_file = "null.png";
	//capture screenshot to a file
 try{
      captured_file = ScreenCapture.capture();
}catch(Exception ex){}

      // add the required response header for a PDF file
      Headers h = t.getResponseHeaders();
      h.add("Content-Type", "image/png");

      // a PDF (you provide your own!)
      File file = new File (captured_file);
      byte [] bytearray  = new byte [(int)file.length()];
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(bytearray, 0, bytearray.length);

      // ok, we are ready to send the response.
      t.sendResponseHeaders(200, file.length());
      OutputStream os = t.getResponseBody();
      os.write(bytearray,0,bytearray.length);
      os.close();
      fis.close();
      bis.close();
	DeleteFile.delete(captured_file);
	 executeCommand("erase *.PNG");
    }
  }
private static String executeCommand(String command) {
 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
                        reader.close();
 
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e);
		}
 
		return output.toString();
 
	}

private static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}

}
