package com.avalia.learning.java.flightsimulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Simulation {

	public void read(String path) throws IOException {
		File file = new File(path);
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cgf.xml");// populates the data of the
											// configuration file

		// creating session factory object
		SessionFactory factory = cfg.buildSessionFactory();

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				extract(f.getPath(), factory);
			}
		} else {
			extract(path, factory);
		}
	}

	public List<Flight> searchDB(String dep, String arr, String pref) throws SQLException {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cgf.xml");// populates the data of the
											// configuration file

		// creating session factory object
		SessionFactory factory = cfg.buildSessionFactory();
		String hql = "select f from Flight f where arrLoc='" + arr + "'and depLoc='" + dep + "'";
		Session session = factory.openSession();
		Query query = session.createQuery(hql);

		List<Flight> flights = query.list();
		sort(flights, pref);
		return flights;
	}

	private void extract(String path, SessionFactory factory) throws IOException {

		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		line = br.readLine();
		while (line != null) {
			Transaction t = session.beginTransaction();
			if (!line.isEmpty()) {
				session.persist(new Flight(line));// persisting the object
			}
			line = br.readLine();
			t.commit();// transaction is committed
		}
		session.close();
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
