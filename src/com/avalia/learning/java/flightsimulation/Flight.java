package com.avalia.learning.java.flightsimulation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {
	@Id
	private String flightNum;
	@Column(name = "dep_loc")
	private String depLoc;
	@Column(name = "arr_loc")
	private String arrLoc;
	@Column(name = "valid")
	private String validTill;
	@Column(name = "time")
	private int flightTime;
	@Column(name = "duration")
	private float flightDuration;
	@Column(name = "fare")
	private int fare;

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

	public Flight() {
		// TODO Auto-generated constructor stub
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

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getDepLoc() {
		return depLoc;
	}

	public void setDepLoc(String depLoc) {
		this.depLoc = depLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public void setArrLoc(String arrLoc) {
		this.arrLoc = arrLoc;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public float getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(float flightDuration) {
		this.flightDuration = flightDuration;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return flightNum + "," + depLoc + "," + arrLoc + "," + validTill + "," + flightTime + "," + flightDuration + ","
				+ fare;
	}

}
