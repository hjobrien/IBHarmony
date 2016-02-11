
public class Person {
	private char gender, genderPreference;
	private int grade;
	private String answers;
	private String name;
	private boolean matched = false;

	public Person(String name, int grade, char gender, char genderPreference, String answers){
		this.gender = gender;
		this.genderPreference = genderPreference;
		this.grade = grade;
		this.answers = answers;
		this.name = name;
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

	public char getGender() {
		return gender;
	}

	public char getGenderPreference() {
		return genderPreference;
	}

	public int getGrade() {
		return grade;
	}

	public String getAnswers() {
		return answers;
	}

	public String getName() {
		return name;
	}


	
}
