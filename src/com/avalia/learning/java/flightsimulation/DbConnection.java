package com.avalia.learning.java.flightsimulation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DbConnection {
	public Connection con;

	public DbConnection() {
		try {
			con = createConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

//	public void insert() throws SQLException {
//		List<Flight> f1 = getFlights();
//		for (Flight flight : f1) {
//			try {
//				PreparedStatement ps = con.prepareStatement("insert into flight_entry value (?,?,?,?,?,?,?)");
//				ps.setString(1, flight.flightNum);
//				ps.setString(2, flight.depLoc);
//				ps.setString(3, flight.arrLoc);
//				ps.setString(4, flight.validTill);
//				ps.setInt(5, flight.flightTime);
//				ps.setFloat(6, flight.flightDuration);
//				ps.setInt(7, flight.fare);
//				ps.executeUpdate();
//				System.out.println("Inserted new Record");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

//	private List<Flight> getFlights() {
//		Simulation s = new Simulation("C:\\flight");
//		return f1;
//	}

	private Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://35.198.199.48:3306/flights", "vishal", "vishal@54");
		return con;
	}

//	public void fetch() throws SQLException, ClassNotFoundException {
//		Connection con = createConnection();
//
//		Statement st = con.createStatement();
//		// PreparedStatement ps = con.prepareStatement("select * from
//		// flight_entry where DEP_LOC=? AND ARR_LOC=?);
//		ResultSet rs = st.executeQuery("select * from flight_entry where DEP_LOC'FRA' AND ARR_LOC='LHR'");
//
//		while (rs.next()) {
//			System.out.println(rs.getString(1) + "->" + rs.getString(2) + "->" + rs.getString(3) + "->"
//					+ rs.getString(4) + "->" + rs.getInt(5) + "->" + rs.getDouble(6) + "->" + rs.getInt(7));
//		}
//
//	}
//
//	public List<Flight> search(String dep, String arr) throws SQLException {
//		List<Flight> flights = new ArrayList<>();
//		PreparedStatement ps = con.prepareStatement("select * from flight_entry where DEP_LOC=? AND ARR_LOC=?");
//		ps.setString(1, dep);
//		ps.setString(2, arr);
//		ResultSet rs = ps.executeQuery();
//		while (rs.next()) {
//			flights.add(new Flight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
//					rs.getFloat(6), rs.getInt(7)));
//		}
//		return flights;
//	}
//
//	public List<Flight> searchFault(String dep, String arr) throws SQLException {
//
//		List<Flight> flights = new ArrayList<>();
//		Statement st = con.createStatement();
//		ResultSet rs = st
//				.executeQuery("select * from flight_entry where DEP_LOC='" + dep + "' AND ARR_LOC='" + arr + "'");
//		while (rs.next()) {
//			flights.add(new Flight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
//					rs.getFloat(6), rs.getInt(7)));
//		}
//
//		return flights;
//	}

	@Override
	protected void finalize() throws Throwable {
		con.close();
		super.finalize();
	}

}
