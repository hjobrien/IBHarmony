import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchPeopleMethod1 {
	
	/*
	 * This Match People method iterates from the top of the file down, choosing the top match for each
	 * person and subsequently removing their pair. The drawback to this design is that it does not ensure
	 * that each person is matched to their best fit, only that the first person is. The lower down the list
	 * someone is, the less chance they have of being paired with their best match.
	 */
	
	public static int num_qs;
	public static int[] weights;
	
	public static String fileName;
	
	public static ArrayList<Person> unpaired = new ArrayList<Person>();
	
	public MatchPeopleMethod1(String file, int[] inWeights, int qs){
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
		System.out.println("\n");
		System.out.println(String.format("%20.20s  %s\t %s", "Name of Person", "<3 Score", "Name of Match"));
		System.out.println(String.format("%20.20s  %s", "", "out of " + weightSum()));
		while (eligibleCandidates.size() > 1){
			pairPeople(eligibleCandidates);
		}
		if (eligibleCandidates.size() == 1){
			unpaired.add(eligibleCandidates.get(0));
		}
		System.out.println(printUnpaired());
	}

	private int weightSum() {
		int sum = 0;
		for (int i = 0; i < weights.length; i++){
			sum += weights[i];
		}
		return sum;
	}

	private String printUnpaired() {
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

	private static void pairPeople(ArrayList<Person> eligibleCandidates) {
		Person p1 = eligibleCandidates.get(0);
		Match bestMatch = null;
		int bestGoodFitCount = 0;
		
		for(int j = 1; j < eligibleCandidates.size(); j++){
			
			Person p2 = eligibleCandidates.get(j);
			int tempGoodFitCount = 0;
			
			if(p1.matchesWith(p2)){
				Match m = new Match(p1, p2, weights);
				tempGoodFitCount = m.getMatchScore();
				if(tempGoodFitCount > bestGoodFitCount){
					bestMatch = m;
					bestGoodFitCount = tempGoodFitCount;
				}
			}
		}
		
		if (bestGoodFitCount == 0){
			unpaired.add(eligibleCandidates.get(0));
			eligibleCandidates.remove(0);
		} else {
			eligibleCandidates.remove(0);
			eligibleCandidates.remove(eligibleCandidates.indexOf(bestMatch.getP2()));
			System.out.println(display(bestMatch));
		}
	}
	
	public static int getGoodFitCount(Person p1, Person p2) {
		int tempGoodFitCount = 0;
		String p1Answers = p1.getAnswers();
		String p2Answers = p2.getAnswers();
		for(int k = 0; k < num_qs; k++){
			if(p1Answers.charAt(k) == p2Answers.charAt(k)){
				//increments the similarity index by the weight for each question 
				//(change weights at the top)
				tempGoodFitCount+=weights[k];
			}
		}
		return tempGoodFitCount;
	}

	private static String display(Match m) {
		return String.format("%20.20s \t %d\t%s", m.getP1().getName(), m.getMatchScore(), m.getP2().getName());
	}
}
