package com.avalia.learning.java.flightsimulation;


import java.util.Comparator;

public class FareComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {

		return new Integer(f1.fare).compareTo(f2.fare);
	}
}
