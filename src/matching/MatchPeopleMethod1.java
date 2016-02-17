package matching;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MatchPeopleMethod1 extends MatchPeople{
	
	/*
	 * This Match People method iterates from the top of the file down, choosing the top match for each
	 * person and subsequently removing their pair. The drawback to this design is that it does not ensure
	 * that each person is matched to their best fit, only that the first person is. The lower down the list
	 * someone is, the less chance they have of being paired with their best match.
	
	 */
		
	public MatchPeopleMethod1(String file, ArrayList<Integer> inWeights, int qs) throws FileNotFoundException{
		super(file, inWeights, qs);
	}
	
	public void run(ArrayList<Person> eligibleCandidates){
		ArrayList<Person> unpaired = new ArrayList<Person>();
		
		p.println("\n");
		p.println(String.format("%20.20s  %s\t %s", "Name of Person", "<3 Score", "Name of Match"));
		p.println(String.format("%20.20s  %s", "", "out of " + super.weightSum()));
		while (eligibleCandidates.size() > 1){
			pairPeople(eligibleCandidates, unpaired);
		}
		
		if (includeSumScore){
			System.out.println(sumScore);
		}
		
		if (eligibleCandidates.size() == 1){
			unpaired.add(eligibleCandidates.get(0));
		}
		p.println(printUnpaired(unpaired));
	}

	private void pairPeople(ArrayList<Person> eligibleCandidates, ArrayList<Person> unpaired) {
		Person p1 = eligibleCandidates.get(0);
		Match bestMatch = null;
		int bestMatchScore = 0;
		
		for(int j = 1; j < eligibleCandidates.size(); j++){
			
			Person p2 = eligibleCandidates.get(j);
			int tempMatchScore = 0;
			
			if(p1.matchesWith(p2)){
				Match m = new Match(p1, p2, weights);
				tempMatchScore = m.getMatchScore();
				if(tempMatchScore > bestMatchScore){
					bestMatch = m;
					bestMatchScore = tempMatchScore;
				}
			}
		}
		
		if (bestMatchScore != 0){
			sumScore += bestMatch.getMatchScore();
		}
		
		if (bestMatchScore == 0){
			unpaired.add(eligibleCandidates.get(0));
			eligibleCandidates.remove(0);
		} else {
			eligibleCandidates.remove(0);
			eligibleCandidates.remove(eligibleCandidates.indexOf(bestMatch.getP2()));
			p.println(display(bestMatch));
		}
	}
}
