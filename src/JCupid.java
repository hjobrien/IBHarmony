import java.io.FileNotFoundException;

public class JCupid {
	
	//these values can be changed to reflect the quiz
	public static final int NUM_QUESTIONS = 14;
	
	//each number is the weight of that question, 1 is average (less important questions should have weight < 1, more important ones should be > 1
	public static final int[] WEIGHTS = new int[]{3,2,1,1,1,1,1,3,3,1,2,2,1,1};
	
	public static final String generatedFileName = "PersonalData.txt";
	public static final String processedFileName = "FullData.txt";
	public static final String GENERATED_FILE_NAME = "PersonalData.txt";
	public static final String PROCESSED_FILE_NAME = "PersonalData.txt";
	
	public static void main(String[] args) throws FileNotFoundException{
		//if you want to generate a file yourself
//		FileGenerator fg = new FileGenerator(NUM_QUESTIONS, generatedFileName);
		FileGenerator.genFile(NUM_QUESTIONS, GENERATED_FILE_NAME);
		
		//creates the class that matches people together
		MatchPeople mp = new MatchPeople(PROCESSED_FILE_NAME, WEIGHTS, NUM_QUESTIONS);
		
		//actually does the matching and processing
		mp.run(mp.fileToList());
	}
}
