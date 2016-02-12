import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchPeople {
	
	public static int num_qs;
	public static int[] weights;
	
	public static String fileName;
	
	public MatchPeople(String file, int[] inWeights, int qs){
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
		while (eligibleCandidates.size() > 1){
			pairPeople(eligibleCandidates);
		}
		if (eligibleCandidates.size() == 1){
			System.out.println("****" + eligibleCandidates.get(0).getName() + " could not be paired. ****");
		}
	}

	private static void pairPeople(ArrayList<Person> eligibleCandidates) {
		Person p1 = eligibleCandidates.get(0);
		String p1Answers = p1.getAnswers();
		Person bestMatch = eligibleCandidates.get(1);
		int bestGoodFitCount = 0;
		
		for(int j = 1; j < eligibleCandidates.size(); j++){
			
			Person p2 = eligibleCandidates.get(j);
			int tempGoodFitCount = 0;
			
			if(p1.matchesWith(p2)){
				String p2Answers = p2.getAnswers();
				for(int k = 0; k < num_qs; k++){
					if(p1Answers.charAt(k) == p2Answers.charAt(k)){
						//increments the similarity index by the weight for each question 
						//(change weights at the top)
						tempGoodFitCount+=weights[k];
					}
				}
				if(tempGoodFitCount > bestGoodFitCount){
					bestMatch = eligibleCandidates.get(j);
					bestGoodFitCount = tempGoodFitCount;
				}
			}
		}
		eligibleCandidates.remove(0);
		if (bestGoodFitCount == 0){
			System.out.println("****" + eligibleCandidates.get(0).getName() + " could not be paired. ****");
		} else {
			eligibleCandidates.remove(eligibleCandidates.indexOf(bestMatch));
			System.out.println(display(p1, bestMatch, bestGoodFitCount));
		}
	}

	private static String display(Person p1, Person bestMatch, int fitCount) {
		return String.format("%20.20s \t %d \t %s", p1.getName(), fitCount, bestMatch.getName());
	}
}
