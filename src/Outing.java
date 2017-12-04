import java.util.ArrayList;

public class Outing {
	private int dateOfEvent;
	private String timeOfEvent;
	private Restarants finalCount;
	private Person organizer;
	private ArrayList<Person> attendees;

	public Outing() {

	}

	public Outing(int dateOfEvent, String timeOfEvent, Restarants finalCount, Person organizer,
			ArrayList<Person> attendees) {
		super();
		this.dateOfEvent = dateOfEvent;
		this.timeOfEvent = timeOfEvent;
		this.finalCount = finalCount;
		this.organizer = organizer;
		this.attendees = attendees;
	}

	public int getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(int dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String getTimeOfEvent() {
		return timeOfEvent;
	}

	public void setTimeOfEvent(String timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}

	public Restarants getFinalCount() {
		return finalCount;
	}

	public void setFinalCount(Restarants finalCount) {
		this.finalCount = finalCount;
	}

	public Person getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Person organizer) {
		this.organizer = organizer;
	}

	public ArrayList<Person> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<Person> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		return "Outing [dateOfEvent=" + dateOfEvent + ", timeOfEvent=" + timeOfEvent + ", finalCount=" + finalCount
				+ ", organizer=" + organizer + ", attendees=" + attendees + "]";
	}
	
	public void setSearchRadius() {
		
	}
	
	public void setVotingTimeLimit() {
		// TODO create method that allows user to set time limit to end voting
	}
	
	
}
