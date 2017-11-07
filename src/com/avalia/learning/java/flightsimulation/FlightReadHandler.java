package com.avalia.learning.java.flightsimulation;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Arrays;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FlightReadHandler implements HttpHandler {

	@SuppressWarnings("deprecation")
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		if (requestMethod.equalsIgnoreCase("GET")) {
			Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/HTML");
			exchange.sendResponseHeaders(200, 0);

			OutputStream responseBody = exchange.getResponseBody();

			Headers requestHeaders = exchange.getRequestHeaders();

			String query = exchange.getRequestURI().getQuery();
			String path = URLDecoder.decode(query,"UTF-8");
			Simulation s = new Simulation();
			char[] c = path.toCharArray();
			
			for (int i = 0; i < c.length; i++) {
				if(c[i]=='/')
					c[i]='\\';
			}
			path=new String(c);
			
			s.read(path);
			responseBody.write("Inserted Succesfully".getBytes());

			responseBody.close();

		}
	}
}
