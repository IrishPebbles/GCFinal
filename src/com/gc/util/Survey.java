package com.gc.util;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Survey {

	private ArrayList<Person> voters;
	private ArrayList<String> potentialVenues;
	private Integer[] voteScore;
	
	public ArrayList<String> getPotentialVenues() {
		return potentialVenues;
	}

	public void setPotentialVenues(ArrayList<String> potentialVenues) {
		this.potentialVenues = potentialVenues;
	}

	public void setVoters(ArrayList<Person> voters) {
		this.voters = voters;
	}

	private ArrayList<Integer> numVotes;
	

	public ArrayList<Person> getVoters() {
		return voters;
	}

	public Survey() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int updateScore() {
		return 0;
	}

	public RestaurantObj finalResult() {
		return null;
	}

	public boolean votingHasEnded() {
		return false;
	}

	public ArrayList<Integer> getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(ArrayList<Integer> numVotes) {
		this.numVotes = numVotes;
	}
	
	public void createPotentialList(GeolocationAPI location) {
		ZoomatoAPI zApi = new ZoomatoAPI(location);
		potentialVenues = zApi.getList();
		System.out.println("In Method create " +potentialVenues);
		
		
	}

	@Override
	public String toString() {
		return "Survey [voters=" + voters + ", potentialVenues=" + potentialVenues + "]";
	}

}
