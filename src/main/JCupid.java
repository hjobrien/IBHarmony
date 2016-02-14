package main;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dataEntry.FileGenerator;
import dataEntry.Questionnaire;
import matching.MatchPeopleMethod1;
import matching.MatchPeopleMethod2;

public class JCupid {
	
	//these values can be changed to reflect the quiz
	//also can be less than the number of questions in the MatchMakingQuiz.txt file
	public static final int NUM_QUESTIONS = 14;
	
	//each number is the weight of that question 
	public static final int[] WEIGHTS = new int[]{3,2,1,1,1,1,1,3,3,1,2,2,1,1};
//	public static final int[] WEIGHTS = new int[]{8,7,5,6,1,2,1,9,10,1,4,3,1,1};
	
	public static final int LOWER_GRADE_BOUNDARY = 9;
	public static final int UPPER_GRADE_BOUNDARY = 12;
	
	public static final String GENERATED_FILE_NAME = "InputData.txt";
	public static final String TAKE_QUIZ_FILE_NAME = "FullData.txt";
//	public static final String PROCESSED_FILE_NAME = "FullData.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		boolean processGeneratedFile = false;
		
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
				processGeneratedFile = true;
				FileGenerator f = new FileGenerator(NUM_QUESTIONS, GENERATED_FILE_NAME, 
						LOWER_GRADE_BOUNDARY, UPPER_GRADE_BOUNDARY);
			}
		}
		
		System.out.print("Would you like to process the data? (y for yes) ");
		if (console.nextLine().toLowerCase().equals("y")){
			String file = TAKE_QUIZ_FILE_NAME;
			if (processGeneratedFile){
				file = GENERATED_FILE_NAME;
			}
			
			System.out.print("Would you like to use method 1? (y for yes) ");
			if (console.nextLine().toLowerCase().equals("y")){
				//matches and process the data
				MatchPeopleMethod1 mp = new MatchPeopleMethod1(file, WEIGHTS, NUM_QUESTIONS);
				
				//actually does the matching and processing
				mp.run(mp.fileToList());
			}
			
			System.out.print("Would you like to use method 2? (y for yes) ");
			if (console.nextLine().toLowerCase().equals("y")){
				//matches and process the data
				MatchPeopleMethod2 mp = new MatchPeopleMethod2(file, WEIGHTS, NUM_QUESTIONS);
				
				//actually does the matching and processing
				mp.run(mp.fileToList());
			}
		}
	}
}
