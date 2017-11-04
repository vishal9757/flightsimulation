package com.avalia.learning.java.flightsimulation;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// Simulation s = new Simulation("C:\\flight");
		Simulation s = new Simulation();
		// List<Flight> flights = s.search("LHR", "PEK", "fare");
		// s.print(flights);
		DbConnection db = new DbConnection();
		// try {
		// db.insert();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// db.fetch();

		// Map<String, Object> map = new HashMap<>();
		// map.put("1", 1);
		// Simulation.print(db.search(map));

		// Simulation.print(db.searchFault("FRA", "LHR"));
		// Simulation.print(db.searchFault("FRA' OR 1=1 OR DEP_LOC='FRA", "LHR'
		// OR 1=1 OR ARR_LOC='LHR"));
		//
		// Simulation.print(db.search("FRA", "LHR"));
		// Simulation.print(db.search("FRA' OR 1=1 OR DEP_LOC='FRA", "LHR' OR
		// 1=1 OR ARR_LOC='LHR"));

		Simulation.print(s.searchDB("FRA", "LHR", "fare"));
	}

}
