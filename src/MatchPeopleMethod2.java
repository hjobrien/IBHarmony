import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MatchPeopleMethod2 extends MatchPeople{
	
	/*
	 * This MatchPeople method iterates through every possible match a person can have, with the resulting
	 * matchFitScore between two people and the people's names being stored in an ArrayList of Match objects.
	 * After all the objects are created, the program will then iterate through each match and choose the 
	 * one with the highest matchFitScore. Each element of the ArrayList that contained the people already 
	 * chosen will be removed, and the program will iterate again. This process will continue until
	 * the ArrayList does not have any matches remaining. 
	 */
	
	/*
	 * NOTE***
	 * this still slightly favors people who are higher on the list because if a match with the 
	 * same score is found, it will not currently overwrite the first match. This is changeable though.
	 */
		
	public MatchPeopleMethod2(String file, int[] inWeights, int qs) throws FileNotFoundException{
		super(file, inWeights, qs);
	}
	
	public void run(ArrayList<Person> eligibleCandidates){
		
		//generates the ArrayList of all the possible matches
		ArrayList<Match> allMatches = new ArrayList<Match>();
		for (int i = 0; i < eligibleCandidates.size() - 1; i++){
			Person p1 = eligibleCandidates.get(i);
			for (int j = i + 1; j < eligibleCandidates.size(); j++){
				Person p2 = eligibleCandidates.get(j);
				if(p1.matchesWith(p2)){
					allMatches.add(new Match(p1, p2, weights));
				}
			}
		}
		
		p.println("\n");
		p.println(String.format("%20.20s  %s\t %s", "Name of Person", "<3 Score", "Name of Match"));
		p.println(String.format("%20.20s  %s", "", "out of " + weightSum()));
		
		//sorts and prints out the pairs
		//manipulates the way java stores objects to allow the pairPeople method to be void while
		//still altering the allMatches and eligibleCandidates ArrayLists
		while (allMatches.size() > 0){
			pairPeople(allMatches, eligibleCandidates);
		}
		
		if (includeSumScore){
			System.out.println(sumScore);
		}
		
		//after all the top pairs have been formed, the unpaired people are printed
		p.println(printUnpaired(eligibleCandidates));
	}

	private void pairPeople(ArrayList<Match> allMatches, ArrayList<Person> eligibleCandidates) {
		Match bestMatch = null;
		int highestMatchScore = -1;
		for (Match m : allMatches){
			int matchScore = m.getMatchScore();
			
			//changing > to >= would switch the favoring from top of the list to bottom
			if (matchScore >/*=*/ highestMatchScore){
				highestMatchScore = matchScore;
				bestMatch = m;
			}
		}
		p.println(display(bestMatch));
		
		if (includeSumScore){
			this.sumScore += bestMatch.getMatchScore();
		}
		
		for (int i = allMatches.size() - 1; i >= 0; i--){
			if (checkForPeopleMatches(allMatches.get(i), bestMatch)){
				allMatches.remove(i);
			}
		}
		
		eligibleCandidates.remove(eligibleCandidates.indexOf(bestMatch.getP2()));
		eligibleCandidates.remove(eligibleCandidates.indexOf(bestMatch.getP1()));
	}

	private boolean checkForPeopleMatches(Match match, Match bestMatch) {	
		Person p11 = match.getP1();
		Person p12 = match.getP2();
		
		Person p21 = bestMatch.getP1();
		Person p22 = bestMatch.getP2();
		
		if (p11.equals(p21)){
			return true;
		} else if (p11.equals(p22)){
			return true;
		} else if (p12.equals(p21)){
			return true;
		} else if (p12.equals(p22)){
			return true;
		}
		
		return false;
	}
}
