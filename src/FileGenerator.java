import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class FileGenerator {
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
		if (s.contains("Gender")){
			if (!answer.toLowerCase().equals("m") && !answer.toLowerCase().equals("f")){
				System.out.println("Please type again.");
				
			}
		}	
		p.print(answer + " ");
		return true;
	}
}
