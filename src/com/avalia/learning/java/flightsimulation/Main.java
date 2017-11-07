package com.avalia.learning.java.flightsimulation;

import java.io.IOException;
import java.io.IOException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpServer;

public class Main {

	// public static void main(String[] args) throws ClassNotFoundException,
	// SQLException {
	// Simulation s = new Simulation();
	// try {
	// s.read("C:\\flight");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// Simulation.print(s.searchDB("FRA", "LHR", "duration"));
	// }

	public static void main(String[] args) throws IOException {
		InetSocketAddress addr = new InetSocketAddress(8000);

		HttpServer server = HttpServer.create(addr, 0);

		server.createContext("/flights", new FlightSearchHandler());
		server.createContext("/read", new FlightReadHandler());
		server.createContext("/hello", new HelloHandler());

		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
		System.out.println("Server is listening on port 8000");
	}
}

class FlightSearchHandler implements HttpHandler {
	public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();

		if (query != null) {
			String[] pairs = query.split("&");
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
						URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			}
		}
		return query_pairs;
	}

	@SuppressWarnings("restriction")
	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		if (requestMethod.equalsIgnoreCase("GET")) {
			Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/HTML");
			exchange.sendResponseHeaders(200, 0);

			OutputStream responseBody = exchange.getResponseBody();

			Headers requestHeaders = exchange.getRequestHeaders();
			Set<String> keySet = requestHeaders.keySet();
			StringBuffer sb = new StringBuffer();
			sb.append("<html><body><table>");
			sb.append("<tr>");
			sb.append("<th>Number</th>");
			sb.append("<th>Arrival</th>");
			sb.append("<th>Departure</th>");
			sb.append("<th>Duration</th>");
			sb.append("<th>Time</th>");
			sb.append("<th>Fare</th>");
			sb.append("</tr>");
			
			Map<String, String> query = splitQuery(exchange.getRequestURI().getQuery());
			Simulation s = new Simulation();
			List<Flight> flights;
			try {
				flights = s.searchDB(query.get("dep"), query.get("arr"), query.get("pref"));
				for (Flight flight : flights) {
					sb.append("<tr>");
					sb.append("<td>" + flight.getFlightNum() + "</td>");
					sb.append("<td>" + flight.getArrLoc() + "</td>");
					sb.append("<td>" + flight.getDepLoc() + "</td>");
					sb.append("<td>" + flight.getFlightDuration() + "</td>");
					sb.append("<td>" + flight.getFlightTime() + "</td>");
					sb.append("<td>" + flight.getFare() + "</td>");
					sb.append("</tr>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append("</table></body></html");
			responseBody.write(sb.toString().getBytes());
			responseBody.close();
		}
	}
}
