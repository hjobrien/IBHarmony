package matching;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchPeople {
	
	public static boolean includeSumScore = false;
	
	public static int num_qs;
	public static ArrayList<Integer> weights;
	public int sumScore;
	
	public static String fileName;
	
	public static PrintStream p;
	
	public MatchPeople(String file, ArrayList<Integer> inWeights) throws FileNotFoundException{
		p = new PrintStream(System.out);
		num_qs = inWeights.size();
		weights = inWeights;
		fileName = file;
	}
	
	public ArrayList<Person> fileToList(){
		ArrayList<Person> people = new ArrayList<Person>();
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(fileReader.hasNextLine()){
			String name = fileReader.nextLine();
			int grade = fileReader.nextInt();
			char gender = fileReader.next().toLowerCase().charAt(0);
			char genderPreference = fileReader.next().toLowerCase().charAt(0);
			String answers = fileReader.next();
			people.add(new Person(name, grade, gender, genderPreference, answers));
			if (fileReader.hasNextLine()){
				fileReader.nextLine();
			}
		}
		return people;
	}
	
	public int weightSum() {
		int sum = 0;
		for (int i = 0; i < weights.size(); i++){
			sum += weights.get(i);
		}
		return sum;
	}	
	
	public static String display(Match m) {
		return String.format("%20.20s \t %d\t%s", m.getP1().getName(), m.getMatchScore(), m.getP2().getName());
	}

	public String printUnpaired(ArrayList<Person> unpaired) {
		String s = "";
		if (unpaired.size() > 0){
			s += "\nThe following people were unpaired:\n";
			for (Person p : unpaired){
				s += String.format("%20.20s ", p.getName());
				s += String.format("\t %2.2s ", p.getGrade());
				s += p.getGender() + " ";
				s += p.getGenderPreference() + "\n";
			}
		}
		return s;
	}
	
	public void run(ArrayList<Person> eligibleCandidates){
		
	}
}
