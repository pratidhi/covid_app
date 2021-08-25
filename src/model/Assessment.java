package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Assessment {

	private ArrayList<String> symptoms;
	private boolean travelHistory;
	private boolean contactWithCovidPatient;

	public ArrayList<String> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(ArrayList<String> symptoms) {
		this.symptoms = symptoms;
	}
	public boolean isTravelHistory() {
		return travelHistory;
	}
	public void setTravelHistory(boolean travelHistory) {
		this.travelHistory = travelHistory;
	}

	public boolean isContactWithCovidPatient() {
		return contactWithCovidPatient;
	}
	public void setContactWithCovidPatient(boolean contactWithCovidPatient) {
		this.contactWithCovidPatient = contactWithCovidPatient;
	}
	@Override
	public String toString() {
		return "Assessment [symptoms=" + symptoms + ", travelHistory=" + travelHistory + ", contactWithCovidPatient="
				+ contactWithCovidPatient + "]";
	}
	
}
