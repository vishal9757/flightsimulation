package com.avalia.learning.java.flightsimulation;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {
	private List<Flight> data;
	private Connection con;

	public Simulation() {
		// this.data = read(path);
		con = new DbConnection().con;
	}

	// public List<Flight> read(String path) {
	// List<Flight> flights = new ArrayList<>();
	// File file = new File(path);
	// if (file.isDirectory()) {
	// File[] files = file.listFiles();
	// for (File f : files) {
	// try {
	// flights.addAll(extract(f.getPath()));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// return flights;
	// }

	public void read(String path) throws IOException {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				extract(f.getPath());
			}
		} else {
			extract(path);
		}
	}

	public List<Flight> search(String deptLoc, String arrLoc, String pref) {
		List<Flight> flights = new ArrayList<>();
		for (Flight f : data) {
			if (f.depLoc.equals(deptLoc) && f.arrLoc.equals(arrLoc))
				flights.add(f);
		}
		sort(flights, pref);
		return flights;
	}

	public List<Flight> searchDB(String dep, String arr, String pref) throws SQLException {

		List<Flight> flights = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement("select * from flight_entry where DEP_LOC=? AND ARR_LOC=?");
		ps.setString(1, dep);
		ps.setString(2, arr);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			flights.add(new Flight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
					rs.getFloat(6), rs.getInt(7)));
		}
		sort(flights, pref);
		return flights;
	}

	// private List<Flight> extract(String path) throws IOException {
	// List<Flight> flights = new ArrayList<>();
	// BufferedReader br = new BufferedReader(new FileReader(path));
	// String line = br.readLine();
	// line = br.readLine();
	// while (line != null) {
	// if (!line.isEmpty()) {
	// flights.add(new Flight(line));
	// }
	// line = br.readLine();
	// }
	// br.close();
	// return flights;
	// }

	private void extract(String path) throws IOException {
		List<Flight> flights = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		line = br.readLine();
		while (line != null) {
			if (!line.isEmpty()) {
				try {
					new Flight(line).save();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			line = br.readLine();
		}
		br.close();
	}

	public static void print(List<Flight> flight) {
		for (Flight x : flight) {
			System.out.println(x);
		}
	}

	private void sort(List<Flight> flight, String pref) {
		if (pref.equalsIgnoreCase("fare"))
			Collections.sort(flight, new FareComparator());
		else if (pref.equalsIgnoreCase("duration"))
			Collections.sort(flight, new DurationComparator());
	}

}
