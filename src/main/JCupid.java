package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dataEntry.FileGenerator;
import dataEntry.Questionnaire;
import matching.MatchPeople;
import matching.MatchPeopleMethod1;
import matching.MatchPeopleMethod2;

public class JCupid {
	
	//each number is the weight of that question 
	private static ArrayList<Integer> weights = new ArrayList<Integer>();
	
	public static final int LOWER_GRADE_BOUNDARY = 9;
	public static final int UPPER_GRADE_BOUNDARY = 12;
	
	public static final String GENERATED_FILE_NAME = "InputData.txt";
	public static final String TAKE_QUIZ_FILE_NAME = "FullData.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		weights = getWeights();
		int numQuestions = weights.size();
		
		
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
				Questionnaire q = new Questionnaire(numQuestions, TAKE_QUIZ_FILE_NAME, 
						LOWER_GRADE_BOUNDARY, UPPER_GRADE_BOUNDARY);
			} else {	
				processGeneratedFile = true;
				FileGenerator f = new FileGenerator(numQuestions, GENERATED_FILE_NAME, 
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
				MatchPeople mp = new MatchPeopleMethod1(file, weights);					//yay polymorphism
				
				//actually does the matching and processing
				mp.run(mp.fileToList());
			}
			
			System.out.print("Would you like to use method 2? (y for yes) ");
			if (console.nextLine().toLowerCase().equals("y")){
				//matches and process the data
				MatchPeople mp = new MatchPeopleMethod2(file, weights);					//yay polymorphism
				
				//actually does the matching and processing
				mp.run(mp.fileToList());
			}
		}
	}

	private static ArrayList<Integer> getWeights() throws FileNotFoundException {
		Scanner weightReader = new Scanner(new File("QuestionWeights.txt"));
		ArrayList<Integer> weights = new ArrayList<Integer>();
		weightReader.useDelimiter(",");
		while(weightReader.hasNextInt()){
			weights.add(weightReader.nextInt());
		}
		return weights;
		
	}
}
