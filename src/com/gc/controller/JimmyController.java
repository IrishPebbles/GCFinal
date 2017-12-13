package com.gc.controller;

import java.util.ArrayList;

import com.gc.dao.AttendeesDaoImpl;
import com.gc.dao.PersonDaoImpl;
import com.gc.dao.SurveyDaoImpl;
import com.gc.dto.AttendeesDto;
import com.gc.dto.PersonDto;
import com.gc.dto.SurveyDto;
import com.gc.util.RestaurantObj;
import com.gc.util.ZoomatoAPI;



	public class JimmyController {
		AttendeesDaoImpl attendeeDao = new AttendeesDaoImpl();
		SurveyDaoImpl surveyDao = new SurveyDaoImpl();
		SurveyDto surveyDTO = new SurveyDto();
		ZoomatoAPI grabInfoFromAPI = new ZoomatoAPI(); 
		RestaurantObj winningRestInfo = new RestaurantObj(); 

		// Here we pull in the survey once the column value "HasVoted" has been marked
		// true.
		// This triggers once the last participant has submitted their vote
		ArrayList<SurveyDto> finalSurvey = (ArrayList<SurveyDto>) surveyDao
				.searchSurvey("WeaselStompingDay,2017-12-21,97");{
		surveyDTO = finalSurvey.get(0);
		// Above I assign the arraylist the survey arrives in into an object for
		// manipulation

		ArrayList<Integer> voteCountArray = new ArrayList();
		voteCountArray.add(surveyDTO.getVoteCount1());
		voteCountArray.add(surveyDTO.getVoteCount2());
		voteCountArray.add(surveyDTO.getVoteCount3());
		voteCountArray.add(surveyDTO.getVoteCount4());
		voteCountArray.add(surveyDTO.getVoteCount5());
		// Here I'm assigning the numbers of votes and venue IDs into corresponding
		// positions in two different arrays
		ArrayList<String> venueArray = new ArrayList();
		venueArray.add(surveyDTO.getOptVenueID1());
		venueArray.add(surveyDTO.getOptVenueID2());
		venueArray.add(surveyDTO.getOptVenueID3());
		venueArray.add(surveyDTO.getOptVenueID4());
		venueArray.add(surveyDTO.getOptVenueID5());

		int temp = 0;
		String venue = "";
		// This "for" loop cycles through the voteCountArray list and finds the highest
		// vote count.
		// It also returns the restaurant ID of the corresponding entry from venueArray.
		for (int i = 0; i < voteCountArray.size(); i++) {
			if (voteCountArray.get(i) > temp) {
				temp = voteCountArray.get(i);
				venue = venueArray.get(i).toString();
			}
		}
		
		
		//Here I call the api and put in the winning venue's restID
		//I then assign it to a local Restaurant object, which is how I present the information.
		winningRestInfo = grabInfoFromAPI.searchByRestID(venue); 
		System.out.println("And the winner is: " + winningRestInfo.getRestName() );

		//TODO play with boolean values for determining whether or not people have voted
		//TODO figure out how to join the tables attendees and survey so we can call from both
		//TODO Write the logic that switches a survey to finished and triggers the final count and display

	
		}
				
				public static void main(String[] args) {
					attendeeHasVoted("jimmyburger@onereverse.com");
				}
				
				
	public static void attendeeHasVoted(String voterEmail/*@RequestParam("voterEmail") String voterEmail, @RequestParam("surveyID") String surveyID*/){
		PersonDaoImpl personDAO = new PersonDaoImpl();
		PersonDto personDTO = new PersonDto();
		AttendeesDaoImpl attendeeDAO = new AttendeesDaoImpl();
		AttendeesDto attendeeDTO = new AttendeesDto();
		
		
		//below I create a person object so that we can access the attendee information. We use the person DAP to search for the relevant
		//person by their email (they are created when their email is entered for the first time). We then have it "get" the object out of 
		//the array it is returned in.
		personDTO = personDAO.searchByEmail(voterEmail).get(0);
		System.out.println("We got: " + personDTO.getUserEmail());
		
		
		// use person id to find attendee
		attendeeDTO = attendeeDAO.searchByPersonID(personDTO.getUserID()).get(0); 
		//above we instantiate the attendeeDTO object. We call the information using the DAO that searches by Person ID, then plug in
		//the person ID by calling it from the person DTO. The get 0 is because the attendeeDAO returns an attendee object in an array,
		//so we need to "get" it so it can be converted into the object we call attendeeDTO
		//switch false to true
		attendeeDTO.setVoted(true);
		
		//Here we set the value to true
		
	}
				
				
				
				
				
				
				
				
	}