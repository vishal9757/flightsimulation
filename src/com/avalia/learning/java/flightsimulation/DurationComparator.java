package com.avalia.learning.java.flightsimulation;


import java.util.Comparator;

public class DurationComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {
		return new Float(f1.flightDuration).compareTo(f2.flightDuration);
	}

}
