import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchPeopleMethod2 {
	
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
	
	public static int num_qs;
	public static int[] weights;
	
	public static String fileName;
		
	public MatchPeopleMethod2(String file, int[] inWeights, int qs){
		num_qs = qs;
		weights = inWeights;
		fileName = file;
	}
	
	public ArrayList<Person> fileToList(){
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(fileReader.hasNextLine()){
			String name = fileReader.nextLine();
			int grade = fileReader.nextInt();
			char gender = fileReader.next().toLowerCase().charAt(0);
			char genderPreference = fileReader.next().toLowerCase().charAt(0);
			String answers = fileReader.next();
			people.add(new Person(name, grade, gender, genderPreference, answers));
			if (fileReader.hasNextLine()){
				fileReader.nextLine();
			}
		}
		return people;
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
		
		System.out.println("\n");
		System.out.println(String.format("%20.20s  %s\t %s", "Name of Person", "<3 Score", "Name of Match"));
		System.out.println(String.format("%20.20s  %s", "", "out of " + weightSum()));
		
		//sorts and prints out the pairs
		//manipulates the way java stores objects to allow the pairPeople method to be void while
		//still altering the allMatches and eligibleCandidates ArrayLists
		while (allMatches.size() > 0){
			pairPeople(allMatches, eligibleCandidates);
		}
		
		//after all the top pairs have been formed, the unpaired people are printed
		System.out.println(printUnpaired(eligibleCandidates));
	}

	private int weightSum() {
		int sum = 0;
		for (int i = 0; i < weights.length; i++){
			sum += weights[i];
		}
		return sum;
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
		System.out.println(display(bestMatch));
		
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

	private static String display(Match m) {
		return String.format("%20.20s \t %d\t%s", m.getP1().getName(), m.getMatchScore(), m.getP2().getName());
	}

	private String printUnpaired(ArrayList<Person> unpaired) {
		String s = "";
		if (unpaired.size() > 0){
			s += "\nThe following people were unpaired:\n";
			for (Person p : unpaired){
				s += String.format("%20.20s ", p.getName());
				s += String.format("\t %2.2s ", p.getGrade());
				s += p.getGender() + " ";
				s += p.getGenderPreference() + "\n";
			}
		}
		return s;
	}
}
