package com.avalia.learning.java.flightsimulation;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Flight {
	String flightNum;
	String depLoc;
	String arrLoc;
	String validTill;
	int flightTime;
	float flightDuration;
	int fare;

	public Flight(String flightNum, String depLoc, String arrLoc, String validTill, int flightTime,
			float flightDuration, int fare) {
		super();
		this.flightNum = flightNum;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.fare = fare;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrLoc == null) ? 0 : arrLoc.hashCode());
		result = prime * result + ((depLoc == null) ? 0 : depLoc.hashCode());
		result = prime * result + fare;
		result = prime * result + Float.floatToIntBits(flightDuration);
		result = prime * result + ((flightNum == null) ? 0 : flightNum.hashCode());
		result = prime * result + flightTime;
		result = prime * result + ((validTill == null) ? 0 : validTill.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (arrLoc == null) {
			if (other.arrLoc != null)
				return false;
		} else if (!arrLoc.equals(other.arrLoc))
			return false;
		if (depLoc == null) {
			if (other.depLoc != null)
				return false;
		} else if (!depLoc.equals(other.depLoc))
			return false;
		if (fare != other.fare)
			return false;
		if (Float.floatToIntBits(flightDuration) != Float.floatToIntBits(other.flightDuration))
			return false;
		if (flightNum == null) {
			if (other.flightNum != null)
				return false;
		} else if (!flightNum.equals(other.flightNum))
			return false;
		if (flightTime != other.flightTime)
			return false;
		if (validTill == null) {
			if (other.validTill != null)
				return false;
		} else if (!validTill.equals(other.validTill))
			return false;
		return true;
	}

	public Flight(String str) {
		String[] entry = str.split(",");
		this.flightNum = entry[0];
		this.depLoc = entry[1];
		this.arrLoc = entry[2];
		this.validTill = entry[3];
		this.flightTime = Integer.parseInt(entry[4]);
		this.flightDuration = Float.parseFloat(entry[5]);
		this.fare = Integer.parseInt(entry[6]);
	}

	public void save() throws SQLException {
		DbConnection db = new DbConnection();
		PreparedStatement ps = db.con.prepareStatement("insert into flight_entry value (?,?,?,?,?,?,?)");
		ps.setString(1, this.flightNum);
		ps.setString(2, this.depLoc);
		ps.setString(3, this.arrLoc);
		ps.setString(4, this.validTill);
		ps.setInt(5, this.flightTime);
		ps.setFloat(6, this.flightDuration);
		ps.setInt(7, this.fare);
		ps.executeUpdate();
		System.out.println("Inserted new Flight Record");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return flightNum + "," + depLoc + "," + arrLoc + "," + validTill + "," + flightTime + "," + flightDuration + ","
				+ fare;
	}

}
