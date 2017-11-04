package com.avalia.learning.java.flightsimulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Simulation {

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

	public List<Flight> searchDB(String dep, String arr, String pref) throws SQLException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FlightSimulation");
		EntityManager entitymanager = emf.createEntityManager();
		TypedQuery<Flight> querry = entitymanager
				.createQuery("SELECT f FROM Flight f WHERE f.depLoc= ?dep AND f.arrLoc= ?arr", Flight.class);
		querry.setParameter("dep", dep);
		querry.setParameter("arr", arr);
		List<Flight> flights = querry.getResultList();
		sort(flights, pref);
		return flights;
	}

	private void extract(String path) throws IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FlightSimulation");
		EntityManager entitymanager = emf.createEntityManager();
		entitymanager.getTransaction().begin();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		line = br.readLine();
		while (line != null) {
			if (!line.isEmpty()) {
				entitymanager.persist(new Flight(line));
			}
			line = br.readLine();
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emf.close();
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
