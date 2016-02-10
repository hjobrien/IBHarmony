import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JCupid {
	
	public static final int NUM_QUESTIONS = 9;
	public static final int[] WEIGHTS = new int[]{1,1,1,1,1,1,1,1,1};
	
	public static void main(String[] args){
		compare();
	}
	
	public static void compare(){
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File("PersonalData.txt"));
		} catch (FileNotFoundException e) {}
		
		
		
		while(fileReader.hasNextLine()){
			Scanner fileReader2 = null;
			try {
				fileReader2 = new Scanner(new File("PersonalData.txt"));
			} catch (FileNotFoundException e) {}
			String[] p1Data = fileReader.nextLine().split(" ");
			String p1Answers = p1Data[4];
			String bestMatch = p1Data[0];
			double goodFitCount = 0;
			double max = -1;
			
			while(fileReader2.hasNextLine()){
				goodFitCount = 0;
				String[] p2Data = fileReader2.nextLine().split(" ");
				if(p2Data[1].equals(p1Data[1]) && p2Data[2].equals(p1Data[3]) && p2Data[3].equals(p1Data[2])){
					String p2Answers = p2Data[4];
					for(int i = 0; i < NUM_QUESTIONS; i++){
						if(p1Answers.charAt(i) == p2Answers.charAt(i)){
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
