import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataEntry {
	//these values can be changed to reflect the quiz
	public static final int NUM_QUESTIONS = 9;
	//each number is the weight of that question, 1 is average (less important questions should have weight < 1, more important ones should be > 1
	public static final int[] WEIGHTS = new int[]{8,7,5,6,1,2,1,9,10,1,4,3,1,1};
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Type \"q\" at any time to quit and get matches.");
		PrintStream p = new PrintStream(new File("PersonalData.txt"));
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
			if(cont){
				p.println();
			}
		}
		jCupid(null); //TODO: change
	}

	private static boolean ask(String s, Scanner console, PrintStream p) {
		System.out.print(s);
		String answer = console.nextLine();
		if (answer.toLowerCase().equals("q")){
			return false;
		}
		p.print(answer + " ");
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
			String[] p1Data = fileReader.nextLine().split(" ");
			//the series of 1s or 2s that represent the answers to the questions
			String p1Answers = p1Data[4];
			String bestMatch = p1Data[0];
			double goodFitCount = 0;
			double max = -1;
			
			while(fileReader2.hasNextLine()){
				goodFitCount = 0;
				String[] p2Data = fileReader2.nextLine().split(" ");
				/*checks for:
				 * different names
				 * same grade
				 * if preferences line up (m,f -> f,m)
				 */
				if(!p1Data[0].equals(p2Data[0]) && p2Data[1].equals(p1Data[1]) && p2Data[2].equals(p1Data[3]) && p2Data[3].equals(p1Data[2])){
					String p2Answers = p2Data[4];
					for(int i = 0; i < NUM_QUESTIONS; i++){
						if(p1Answers.charAt(i) == p2Answers.charAt(i)){
							//increments the similarity index by the weight for each question (change weights at the top)
							goodFitCount+=WEIGHTS[i];
						}
					}
					if(goodFitCount > max){
						bestMatch = p2Data[0];
						max = goodFitCount;
					}
				}
			}
			System.out.println(p1Data[0] + " " + bestMatch);
			
		}
	}
}
