import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileGenerator {
	
	public static final int NUM_QUESTIONS = 9;
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("This program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Type \"q\" at any time to quit.");
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
			p.println();
		} 
	}

	private static boolean ask(String s, Scanner console, PrintStream p) {
		System.out.print(s);
		String answer = console.nextLine();
		if (answer.toLowerCase().equals("q")){
			return false;
		}
	 	
		//checks to make sure the grade input is valid
		if (s.contains("Grade")){
			int gradeAsInt = Integer.parseInt(answer);
			while (gradeAsInt < 9 || gradeAsInt > 12){
				if (answer.toLowerCase().equals("q")){
					return false;
				}
				System.out.print("Please type again: ");
				answer = console.nextLine();
				gradeAsInt = Integer.parseInt(answer);
			}
		}
		
		//checks to make sure the gender input is valid
		if (s.contains("Gender")){
			while (!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f")){
				if (answer.toLowerCase().equals("q")){
					return false;
				}
				System.out.print("Please type again: ");
				answer = console.nextLine();
			}
		}
		
		//checks to make sure the answer input is valid
		if (s.contains("Type")){
			while (answer.length() != NUM_QUESTIONS){
				if (answer.toLowerCase().equals("q")){
					return false;
				}
				System.out.print("Please type again: ");
				answer = console.nextLine();
			}
		}
				
		p.print(answer + " ");
		return true;
	}
}
