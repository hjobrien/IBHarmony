import java.io.PrintStream;
import java.util.Scanner;

public class asksQuestions {

	protected boolean askPreferredGender(Scanner console, PrintStream p) {
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
	
	protected boolean askGender(Scanner console, PrintStream p) {
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
	
	protected boolean askGrade(Scanner console, PrintStream p, int minGrade, int maxGrade) {
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
		while (gradeAsInt < minGrade || gradeAsInt > maxGrade|| answer.trim().length() == 0){
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
	
	protected boolean askName(Scanner console, PrintStream p) {
		System.out.print("Name: ");
		String answer = console.nextLine();
		if (answer.trim().length() == 0){
			return false;
		}
		
		p.println("\n" + answer);
		return true;
	}
}
