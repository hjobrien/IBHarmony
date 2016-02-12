import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileGenerator {
	
	public static /*final*/ int NUM_QUESTIONS /*= 14*/;
	
	public static /*final*/ String fileName /*= "PersonalData.txt"*/;

	public FileGenerator(int qs, String file) throws FileNotFoundException{
		NUM_QUESTIONS = qs;
		fileName = file;
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Click \"enter/return\" with an empty input at any time to quit.");
		PrintStream p = new PrintStream(new File(fileName));
		boolean cont = true;
		while (cont == true) {
			cont = askName(console, p);
			if (cont){
				cont = askGrade(console, p);
				if (cont){
					cont = askGender(console, p);
					if (cont){
						cont = askPreferredGender(console, p);
						if (cont){
							cont = askAnswers(console, p);
						}
					}
				}
			}
			System.out.println();
			p.println();
		} 
	}
	
//	public static void main(String[] args) throws FileNotFoundException{
//		Scanner console = new Scanner(System.in);
//		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
//		System.out.println("Click \"enter/return\" with an empty input at any time to quit.");
//		PrintStream p = new PrintStream(new File(fileName));
//		boolean cont = true;
//		while (cont == true) {
//			cont = askName(console, p);
//			if (cont){
//				cont = askGrade(console, p);
//				if (cont){
//					cont = askGender(console, p);
//					if (cont){
//						cont = askPreferredGender(console, p);
//						if (cont){
//							cont = askAnswers(console, p);
//						}
//					}
//				}
//			}
//			System.out.println();
//			p.println();
//		} 
//	}

	private static boolean askAnswers(Scanner console, PrintStream p) {
		System.out.print("Type either 1 or 2, denoting the first or second choice: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		boolean allGood = checkAnswers(answer);
		while (answer.length() != NUM_QUESTIONS || !allGood || answer.trim().length() == 0){
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

	private static boolean askPreferredGender(Scanner console, PrintStream p) {
		System.out.print("Preferred Gender: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		while ((!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f")) || answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			answer = console.nextLine();
		}
		
		p.print(answer + " ");
		return true;
	}

	private static boolean askGender(Scanner console, PrintStream p) {
		System.out.print("Gender: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		while ((!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f")) || answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			answer = console.nextLine();
		}
		
		p.print(answer + " ");
		return true;
	}

	private static boolean askGrade(Scanner console, PrintStream p) {
		System.out.print("Grade: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		
		Scanner num = new Scanner(answer);
		int gradeAsInt = 0;
		if (num.hasNextInt()){
			answer = String.valueOf(num.nextInt());
			gradeAsInt = Integer.parseInt(answer);
		}
		while (gradeAsInt < 9 || gradeAsInt > 12|| answer.trim().length() == 0){
			if (answer.trim().length() == 0){
				return false;
			}
			System.out.print("Please type again: ");
			answer = console.nextLine();
			if (answer.trim().length() == 0){
				return false;
			}
			num = new Scanner(answer);
			if (num.hasNextInt()){
				answer = String.valueOf(num.nextInt());
				gradeAsInt = Integer.parseInt(answer);
			}
		}
		p.print(answer + " ");
		return true;
	}

	private static boolean askName(Scanner console, PrintStream p) {
		System.out.print("Name: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		
		p.println(answer);
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
}
