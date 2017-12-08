package com.gc.dto;

import java.io.Serializable;

public class SurveyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private int surveyID;
	private String optVenueID1;
	private String optVenueID2;
	private String optVenueID3;
	private String optVenueID4;
	private String optVenueID5;
	private int VoteCount1;
	private int VoteCount2;
	private int VoteCount3;
	private int VoteCount4;
	private int VoteCount5;

	private int finalVenueID;
	private boolean hasVoted;

	public SurveyDto() {

	}

	public SurveyDto(int surveyID, int finalVenueID, boolean hasVoted, String optVenueID1, String optVenueID2,
			String optVenueID3, String optVenueID4, String optVenueID5, int VoteCount1, int VoteCount2, int VoteCount3, int VoteCount4, int VoteCount5) {
		super();
		this.surveyID = surveyID;
		this.finalVenueID = finalVenueID;
		this.hasVoted = hasVoted;
		this.optVenueID1 = optVenueID1;
		this.optVenueID2 = optVenueID2;
		this.optVenueID3 = optVenueID3;
		this.optVenueID4 = optVenueID4;
		this.optVenueID5 = optVenueID5;
		this.VoteCount1 = VoteCount1;
		this.VoteCount2 = VoteCount2;
		this.VoteCount3 = VoteCount3;
		this.VoteCount4 = VoteCount4;
		this.VoteCount5 = VoteCount5; 
	}

	public int getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}

	public int getfinalVenueID() {
		return finalVenueID;
	}

	public void setfinalVenueID(int finalVenueID) {
		this.finalVenueID = finalVenueID;
	}

	public boolean isHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}

	public String getOptVenueID1() {
		return optVenueID1;
	}

	public void setOptVenueID1(String optVenueID1) {
		this.optVenueID1 = optVenueID1;
	}

	public String getOptVenueID2() {
		return optVenueID2;
	}

	public void setOptVenueID2(String optVenueID2) {
		this.optVenueID2 = optVenueID2;
	}

	public String getOptVenueID3() {
		return optVenueID3;
	}

	public void setOptVenueID3(String optVenueID3) {
		this.optVenueID3 = optVenueID3;
	}

	public String getOptVenueID4() {
		return optVenueID4;
	}

	public void setOptVenueID4(String optVenueID4) {
		this.optVenueID4 = optVenueID4;
	}

	public String getOptVenueID5() {
		return optVenueID5;
	}

	public void setOptVenueID5(String optVenueID5) {
		this.optVenueID5 = optVenueID5;
	}
	
	

	public int getVoteCount1() {
		return VoteCount1;
	}

	public void setVoteCount1(int voteCount1) {
		VoteCount1 = voteCount1;
	}

	public int getVoteCount2() {
		return VoteCount2;
	}

	public void setVoteCount2(int voteCount2) {
		VoteCount2 = voteCount2;
	}

	public int getVoteCount3() {
		return VoteCount3;
	}

	public void setVoteCount3(int voteCount3) {
		VoteCount3 = voteCount3;
	}

	public int getVoteCount4() {
		return VoteCount4;
	}

	public void setVoteCount4(int voteCount4) {
		VoteCount4 = voteCount4;
	}

	public int getVoteCount5() {
		return VoteCount5;
	}

	public void setVoteCount5(int voteCount5) {
		VoteCount5 = voteCount5;
	}
	
	

	@Override
	public String toString() {
		return "SurveyDto [surveyID=" + surveyID + ", optVenueID1=" + optVenueID1 + ", optVenueID2=" + optVenueID2
				+ ", optVenueID3=" + optVenueID3 + ", optVenueID4=" + optVenueID4 + ", optVenueID5=" + optVenueID5
				+ ", VoteCount1=" + VoteCount1 + ", VoteCount2=" + VoteCount2 + ", VoteCount3=" + VoteCount3
				+ ", VoteCount4=" + VoteCount4 + ", VoteCount5=" + VoteCount5 + ", finalVenueID=" + finalVenueID
				+ ", hasVoted=" + hasVoted + "]";
	}

}
