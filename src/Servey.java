import java.util.ArrayList;

public class Servey {

	private ArrayList<Person> voters;
	private ArrayList<Restarants> potentialVenues;
	private ArrayList<Integer> numVotes;

	public ArrayList<Integer> getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(ArrayList<Integer> numVotes) {
		this.numVotes = numVotes;
	}

	public Servey() {

	}

	public Servey(ArrayList<Person> voters, ArrayList<Restarants> potentialVenues) {
		super();
		this.voters = voters;
		this.potentialVenues = potentialVenues;
	}

	public ArrayList<Person> getVoters() {
		return voters;
	}

	public void setVoters(ArrayList<Person> voters) {
		this.voters = voters;
	}

	public ArrayList<Restarants> getPotentialVenues() {
		return potentialVenues;
	}

	public void setPotentialVenues(ArrayList<Restarants> potentialVenues) {
		this.potentialVenues = potentialVenues;
	}

	@Override
	public String toString() {
		return "Servey [voters=" + voters + ", potentialVenues=" + potentialVenues + ", numVotes=" + numVotes + "]";
	}

	
}
