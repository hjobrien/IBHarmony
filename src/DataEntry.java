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
	
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Type \"q\" at any time to quit and get matches.");
		PrintStream p = new PrintStream(new File("PersonalData.txt"));
		boolean cont = true;
//		String name;
//		int grade;
//		char gender;
//		char genderPref;
//		String answers;
		while (cont == true) {
//			System.out.print("Name: ");
//			name = console.next();
//			System.out.print("Grade: ");
//			grade = console.nextInt();
//			System.out.print("Gender: ");
//			gender = console.next().charAt(0);
//			System.out.print("Gender Preference: ");
//			genderPref = console.next().charAt(0);
//			System.out.print("Answers: ");
//			answers = console.next();
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
//			System.out.println("Quit? (y/n): ");
//			if(console.next().equals("y")){
//				cont = false;
//			}
			System.out.println();
		}
		
		jCupid(fileToList());
	}

	public static ArrayList<Person> fileToList(){
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File("PersonalData.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(fileReader.hasNextLine()){
			people.add(new Person(fileReader.nextLine(), fileReader.nextInt(), fileReader.next().charAt(0), fileReader.next().charAt(0), fileReader.next()));
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
//		//this scanner takes each name in order from the file, this person is the person to be matched with someone else
//		Scanner fileReader = null;
//		try {
//			fileReader = new Scanner(new File("PersonalData.txt"));
//		} catch (FileNotFoundException e) {}
		
		
		
//		while(fileReader.hasNextLine()){
		for(int i = 0; i < eligibleCandidates.size(); i++){
//			//this scanner is reset for each name generated y the first one, it also gets each name from the file as a candidate for matching
//			Scanner fileReader2 = null;
//			try {
//				fileReader2 = new Scanner(new File("PersonalData.txt"));
//			} catch (FileNotFoundException e) {}
//			String[] p1Data = fileReader.nextLine().split(" ");
			//the series of 1s or 2s that represent the answers to the questions
			Person p1 = eligibleCandidates.get(i);
			String p1Answers = p1.getAnswers();
//			String bestMatch = eligibleCandidates.get(i).getName();
			Person bestMatch = eligibleCandidates.get(i);
			double goodFitCount = 0;
			double max = -1;
			
			for(int j = 0; j < eligibleCandidates.size(); j++){
			Person p2 = eligibleCandidates.get(j);

//			while(fileReader2.hasNextLine()){
				goodFitCount = 0;
//				String[] p2Data = fileReader2.nextLine().split(" ");
				/*checks for:
				 * different names
				 * same grade
				 * if preferences line up (m,f -> f,m)
				 */
				String p1Name = p1.getName();
				String p2Name = p2.getName();
				int p1Grade = p1.getGrade();
				int p2Grade = p2.getGrade();
				char p1Gender = p1.getGender();
				char p2Gender = p2.getGender();
				char p1GenderPref = p1.getGenderPreference();
				char p2GenderPref = p2.getGenderPreference();


				if((!p1.isMatched() || !p2.isMatched()) && !p1Name.equals(p2Name) && p1Grade == p2Grade && p1Gender == p2GenderPref && p2Gender == p1GenderPref){
					String p2Answers = p2.getAnswers();
					for(int k = 0; k < NUM_QUESTIONS; k++){
						if(p1Answers.charAt(k) == p2Answers.charAt(k)){
							//increments the similarity index by the weight for each question (change weights at the top)
							goodFitCount+=WEIGHTS[k];
						}
					}
					if(goodFitCount > max){
						bestMatch = new Person(p2Name, p2Grade, p2Gender, p2GenderPref, p2Answers);
						max = goodFitCount;
					}
				}
			}
			bestMatch.hasBeenMatched();
			p1.hasBeenMatched();
			System.out.println("\"" + p1.getName() + "\"-\"" + bestMatch.getName() + "\"");
			
		}
	}
}
