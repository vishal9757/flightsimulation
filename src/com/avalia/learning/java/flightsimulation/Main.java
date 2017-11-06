package com.avalia.learning.java.flightsimulation;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Simulation s = new Simulation();
//		try {
//			s.read("C:\\flight");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Simulation.print(s.searchDB("FRA", "LHR", "duration"));
	}

}
