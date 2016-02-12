
public class Person {
	private char gender, genderPreference;
	private int grade;
	private String answers;
	private String name;

	public Person(String name, int grade, char gender, char genderPreference, String answers){
		this.gender = gender;
		this.genderPreference = genderPreference;
		this.grade = grade;
		this.answers = answers;
		this.name = name;
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

	public boolean matchesWith(Person o){
		if (this.getName().equals(o.getName()))
			return false;
		if (this.getGrade() != o.getGrade())
			return false;
		if (this.getGender() != o.getGenderPreference())
			return false;
		if (this.getGenderPreference() != o.getGender())
			return false;
		return true;
	}
	
}
