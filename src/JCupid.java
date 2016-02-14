import java.io.FileNotFoundException;
import java.util.Scanner;

public class JCupid {
	
	//these values can be changed to reflect the quiz
	//also can be less than the number of questions in the MatchMakingQuiz.txt file
	public static final int NUM_QUESTIONS = 14;
	
	//each number is the weight of that question, 1 is average 
	//(less important questions should have weight < 1, more important ones should be > 1
	public static final int[] WEIGHTS = new int[]{3,2,1,1,1,1,1,3,3,1,2,2,1,1};
//	public static final int[] WEIGHTS = new int[]{8,7,5,6,1,2,1,9,10,1,4,3,1,1};
	
	public static final int LOWER_GRADE_BOUNDARY = 9;
	public static final int UPPER_GRADE_BOUNDARY = 12;
	
	public static final String GENERATED_FILE_NAME = "InputData.txt";
	public static final String TAKE_QUIZ_FILE_NAME = "FullData.txt";
	public static final String PROCESSED_FILE_NAME = "FullData.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		
		//if you want to generate a file yourself
		Scanner console = new Scanner(System.in);
		System.out.print("Would you like to enter data? (y for yes) ");
		if (console.nextLine().toLowerCase().equals("y")){
			System.out.print("Would you like to take the quiz? (y for yes) ");
			
			/*
			 * maybe not the best coding practice, but it seemed like the easiest
			 * way to get the things i want to be static to be static.
			 * feel free to alter these if you want (if you can keep the static
			 * references the same)
			 */
			if (console.nextLine().toLowerCase().equals("y")){
				Questionnaire q = new Questionnaire(NUM_QUESTIONS, TAKE_QUIZ_FILE_NAME, 
						LOWER_GRADE_BOUNDARY, UPPER_GRADE_BOUNDARY);
			} else {	
				FileGenerator f = new FileGenerator(NUM_QUESTIONS, GENERATED_FILE_NAME, 
						LOWER_GRADE_BOUNDARY, UPPER_GRADE_BOUNDARY);
			}
		}
		
		System.out.print("Would you like to process the data? (y for yes) ");
		if (console.nextLine().toLowerCase().equals("y")){
		
			//matches and process the data
			MatchPeopleMethod1 mp1 = new MatchPeopleMethod1(PROCESSED_FILE_NAME, WEIGHTS, NUM_QUESTIONS);
			
			//actually does the matching and processing
			mp1.run(mp1.fileToList());
			
			//matches and process the data
			MatchPeopleMethod2 mp2 = new MatchPeopleMethod2(PROCESSED_FILE_NAME, WEIGHTS, NUM_QUESTIONS);
			
			//actually does the matching and processing
			mp2.run(mp2.fileToList());
		}
	}
}
