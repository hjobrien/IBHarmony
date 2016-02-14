import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileGenerator extends asksQuestions {
	
	public FileGenerator (int qs, String file, int minGrade, int maxGrade) throws FileNotFoundException{
		genFile(qs, file, minGrade, maxGrade);
	}
	
	public void genFile(int qs, String file, int minGrade, int maxGrade) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Click \"enter/return\" with an empty input at any time to quit.");
		PrintStream p = new PrintStream(new File(file));
		boolean cont = true;
		while (cont == true) {
			cont = askName(console, p);
			if (cont){
				cont = askGrade(console, p, minGrade, maxGrade);
				if (cont){
					cont = askGender(console, p);
					if (cont){
						cont = askPreferredGender(console, p);
						if (cont){
							cont = askAnswers(console, p, qs);
						}
					}
				}
			}
			System.out.println();
			p.println();
		} 
	}
	
	private static boolean askAnswers(Scanner console, PrintStream p, int num_qs) {
		System.out.print("Type 0 for neither, 1 for 1st, 2 for 2nd, or 3 for both: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		boolean allGood = checkAnswers(answer);
		while (answer.length() != num_qs || !allGood || answer.trim().length() == 0){
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
	
	//makes sure that only valid inputs are entered
	private static boolean checkAnswers(String answer) {
		for (int i = 0; i < answer.length(); i++){
			if (answer.charAt(i) != '1' && answer.charAt(i) != '2' && answer.charAt(i) != '3' 
					&& answer.charAt(i) != '0'){
				return false;
			}
		}
		return true;
	}
}
