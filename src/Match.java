
public class Match {
	private int matchScore;
	private Person p1;
	private Person p2;

	public Match (Person p1, Person p2){
		this.p1 = p1;
		this.p2 = p2;
		this.matchScore = MatchPeopleMethod1.getGoodFitCount(p1, p2);
	}

	public int getMatchScore() {
		return matchScore;
	}

	public Person getP1() {
		return p1;
	}

	public Person getP2() {
		return p2;
	}
	
	
	
}
