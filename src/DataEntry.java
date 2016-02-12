import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataEntry {
	//these values can be changed to reflect the quiz
	public static final int NUM_QUESTIONS = 14;
	//each number is the weight of that question, 1 is average (less important questions should have weight < 1, more important ones should be > 1
	public static final int[] WEIGHTS = new int[]{3,2,1,1,1,1,1,3,3,1,2,2,1,1};
	
	public static final String fileName = "FullInfo.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Type \"q\" at any time to quit and get matches.");
		PrintStream p = new PrintStream(new File(fileName));
		boolean cont = true;
		while (cont == true) {
			cont = ask("Name: ", console, p);
			if (cont){
				cont = ask("Grade: ", console, p);
				if (cont){
					cont = ask("Gender: ", console, p);
					if (cont){
						cont = ask("Preferred Gender: ", console, p);
						if (cont){
							cont = ask("Type either 1 or 2, denoting the first or second choice: ", console, p);
						}
					}
				}
			}
			System.out.println();
		}
		
		jCupid(fileToList());
	}

	public static ArrayList<Person> fileToList(){
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
	
	private static boolean ask(String s, Scanner console, PrintStream p) {
		System.out.print(s);
		String answer = console.nextLine();
		if (answer.toLowerCase().equals("q")){
			return false;
		}
		
		if(s.contains("Name")){
			answer += "\n";
		}
	 	
		//checks to make sure the grade input is valid
		if (s.contains("Grade")){
			int gradeAsInt = Integer.parseInt(answer);
			while (gradeAsInt < 9 || gradeAsInt > 12|| answer.equals("q")){
				if (answer.toLowerCase().equals("q")){
					return false;
				}
				System.out.print("Please type again: ");
				answer = console.nextLine();
				gradeAsInt = Integer.parseInt(answer);
			}
		}
		
		//checks to make sure the gender input is valid
		if (s.contains("Gender")){
			while ((!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f"))|| answer.equals("q")){
				if (answer.toLowerCase().equals("q")){
					return false;
				}
				System.out.print("Please type again: ");
				answer = console.nextLine();
			}
		}
		
		//checks to make sure the answer input is valid
		if (s.contains("Type")){
			boolean allGood = checkAnswers(answer);
			while (answer.length() != NUM_QUESTIONS || !allGood || answer.equals("q")){
				if (answer.toLowerCase().equals("q")){
					return false;
				}
				System.out.print("Please type again: ");
				answer = console.nextLine();
				allGood = checkAnswers(answer);
			}
		}
				
		p.print(" " + answer);
		return true;
	}
	
	//makes sure that only 1s and 2s are entered
	private static boolean checkAnswers(String answer) {
		for (int i = 0; i < answer.length(); i++){
			if (answer.charAt(i) != '1' && answer.charAt(i) != '2'){
				return false;
			}
		}
		return true;
}
	public static void jCupid(ArrayList<Person> eligibleCandidates){
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
				for(int k = 0; k < NUM_QUESTIONS; k++){
					if(p1Answers.charAt(k) == p2Answers.charAt(k)){
						//increments the similarity index by the weight for each question (change weights at the top)
						tempGoodFitCount+=WEIGHTS[k];
					}
				}
				if(tempGoodFitCount > bestGoodFitCount){
					bestMatch = eligibleCandidates.get(j);
					bestGoodFitCount = tempGoodFitCount;
				}
			}
		}
		eligibleCandidates.remove(0);
		eligibleCandidates.remove(eligibleCandidates.indexOf(bestMatch));
		System.out.println(display(p1, bestMatch, bestGoodFitCount));
	}

	private static String display(Person p1, Person bestMatch, int fitCount) {
		return String.format("%20.20s \t %d \t %s", p1.getName(), fitCount, bestMatch.getName());
	}
}
