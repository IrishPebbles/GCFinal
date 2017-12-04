import java.util.ArrayList;

public class Survey {

	private ArrayList<Person> voters;
	private ArrayList<Restaurant> potentialVenues;
	private ArrayList<Integer> numVotes;

	public ArrayList<Person> getVoters() {
		return voters;
	}

	public void setVoters(ArrayList<Person> voters) {
		this.voters = voters;
	}

	public ArrayList<Restaurant> getPotentialVenues() {
		return potentialVenues;
	}

	public void setPotentialVenues(ArrayList<Restaurant> potentialVenues) {
		this.potentialVenues = potentialVenues;
	}

	public Survey() {
		// TODO Auto-generated constructor stub
	}

	public int updateScore() {
		return 0;
	}

	public Restaurant finalResult() {
		return null;
	}

	public boolean votingHasEnded() {
		return false;
	}

	public Survey(ArrayList<Person> voters, ArrayList<Restaurant> potentialVenues, ArrayList<Integer> numVotes) {
		super();
		this.voters = voters;
		this.potentialVenues = potentialVenues;
		this.numVotes = numVotes;
	}

	public ArrayList<Integer> getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(ArrayList<Integer> numVotes) {
		this.numVotes = numVotes;
	}

	@Override
	public String toString() {
		return "Survey [voters=" + voters + ", potentialVenues=" + potentialVenues + "]";
	}

}
