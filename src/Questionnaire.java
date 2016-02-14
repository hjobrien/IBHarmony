import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Questionnaire extends asksQuestions {
	
	public static final String QUIZ_QS = "MatchMakingQuiz.txt";
	
	public Questionnaire (int qs, String file, int minGrade, int maxGrade) throws FileNotFoundException{
		genFile(qs, file, minGrade, maxGrade);
	}

	public void genFile(int qs, String file, int minGrade, int maxGrade) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("\nThis program matches potential lovebirds. Please follow the instructions.");
		System.out.println("Click \"enter/return\" with an empty input at any time to quit.\n");
		PrintStream p = new PrintStream(
			     new FileOutputStream(file, true));;
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
							cont = askQuestions(console, p, qs, QUIZ_QS);
						}
					}
				}
			}
			System.out.println();
		}
	}

	private static boolean askQuestions(Scanner console, PrintStream p, int num_qs, String quizQs)
			throws FileNotFoundException {
		Scanner file = new Scanner(new File(quizQs));
		String answerList = "";
		System.out.println("Please answer the following questions with appropriate responses.");
		System.out.print("(Type 0 for neither or 3 for both)\n");
		int questionCount = 0;
		while (file.hasNextLine() && questionCount < num_qs){
			questionCount++;
			System.out.print(file.nextLine() + " ");
			String answer = console.nextLine();
			if (answer.trim().length() == 0){
				return false;
			}
			answerList += checkAnswer(answer, console);
		}
		
		p.print(answerList);
		return true;
	}

	//makes sure that only valid inputs are entered
	//minor bug where if the first data entry is invalid and then the user tries to quit, 
	//the program will count the answer as neither and continue. 
	private static String checkAnswer(String answer, Scanner console) {
		while ((answer.charAt(0) != '1' && answer.charAt(0) != '2' && answer.charAt(0) != '3' 
				&& answer.charAt(0) != '0')){
			System.out.print("Please type again: ");
			answer = console.nextLine();
			if (answer.trim().length() == 0){
				return "0";
			}
		}
		return answer;
	}
}