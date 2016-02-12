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
	
	public static final String fileName = "PersonalData.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Type \"q\" at any time to quit and get matches.");
		PrintStream p = new PrintStream(new File(fileName));
		boolean cont = true;
		while (cont == true) {
			cont = askName(console, p);
			if (cont){
				cont = askGrade(console, p);
				if (cont){
					cont = askGender(console, p);
					if (cont){
						cont = askPreferredGender(console, p);
						if (cont){
							cont = askAnswers(console, p);
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
	
	private static boolean askAnswers(Scanner console, PrintStream p) {
		System.out.print("Type either 1 or 2, denoting the first or second choice: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		boolean allGood = checkAnswers(answer);
		while (answer.length() != NUM_QUESTIONS || !allGood || answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			answer = console.nextLine();
			allGood = checkAnswers(answer);
		}
		
		p.print(answer + " ");
		return true;
	}

	private static boolean askPreferredGender(Scanner console, PrintStream p) {
		System.out.print("Preferred Gender: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		while ((!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f")) || answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			answer = console.nextLine();
		}
		
		p.print(answer + " ");
		return true;
	}

	private static boolean askGender(Scanner console, PrintStream p) {
		System.out.print("Gender: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		while ((!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f")) || answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			answer = console.nextLine();
		}
		
		p.print(answer + " ");
		return true;
	}

	//error checking for grades still isn't perfect
	//if the user enters non-ints, the program doesn't process well
	private static boolean askGrade(Scanner console, PrintStream p) {
		System.out.print("Grade: ");
		String answer = "0";
		if (console.hasNextInt()){
			answer = console.nextLine();
		} else {
			console.next();
		}
		if (answer.trim().length() == 0){
			return false;
		}
		int gradeAsInt = Integer.parseInt(answer);
		while (gradeAsInt < 9 || gradeAsInt > 12|| answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			if (console.hasNextInt()){
				answer = String.valueOf(console.nextInt());
			} else {
				console.next();
			}
			gradeAsInt = Integer.parseInt(answer);
		}
		
		p.print(answer + " ");
		return true;
	}

	private static boolean askName(Scanner console, PrintStream p) {
		System.out.print("Name: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		
		p.println(answer);
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
