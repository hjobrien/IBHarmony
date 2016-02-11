import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JCupid {
	
	public static final int NUM_QUESTIONS = 14;
	public static final int[] WEIGHTS = new int[]{3,2,1,1,1,1,1,3,3,1,2,2,1,1};
	
	public static final String fileName = "FullInfo.txt";
	
	public static void main(String[] args){
		jCupid(fileToList());
	}
	
	public static ArrayList<Person> fileToList(){
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
