
public class Person {
	public static char gender, genderPreference;
	public static int grade;
	public static String answers;

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
}
