import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class JCupid {
	//these values can be changed to reflect the quiz
	public static final int NUM_QUESTIONS = 14;
	//each number is the weight of that question, 1 is average (less important questions should have weight < 1, more important ones should be > 1
	public static final int[] WEIGHTS = new int[]{3,2,1,1,1,1,1,3,3,1,2,2,1,1};
	
	public static final String fileName = "PersonalData.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		FileGenerator fg = new FileGenerator(NUM_QUESTIONS, fileName);
		MatchPeople mp = new MatchPeople(fileName, WEIGHTS, NUM_QUESTIONS);
		mp.jCupid(mp.fileToList());
	}
}
