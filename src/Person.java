
public class Person {
	public static char gender, genderPreference;
	public static int grade;
	public static String answers;
	public boolean matched = false;

	public Person(char gender, char genderPreference, int grade, String answers){
		this.gender = gender;
		this.genderPreference = genderPreference;
		this.grade = grade;
		this.answers = answers;
	}
	
	public int compare(Object o){
		
		//could return a score out of 100 maybe?
		return 0;
	}
	
	public boolean isMatched(){
		return matched;
	}
	
	public void hasBeenMatched(){
		this.matched = true;
	}

	public static char getGender() {
		return gender;
	}

	public static char getGenderPreference() {
		return genderPreference;
	}

	public static int getGrade() {
		return grade;
	}

	public static String getAnswers() {
		return answers;
	}

	
}
