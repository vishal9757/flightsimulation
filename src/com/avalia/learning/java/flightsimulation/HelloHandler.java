package com.avalia.learning.java.flightsimulation;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HelloHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		if (requestMethod.equalsIgnoreCase("GET")) {
			Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/HTML");
			exchange.sendResponseHeaders(200, 0);
			StringBuffer sb = new StringBuffer();
			sb.append("<html><body><p>hello</p></body></html");
			OutputStream responseBody = exchange.getResponseBody();
			responseBody.write(sb.toString().getBytes());

			responseBody.close();

		}
	}
}
