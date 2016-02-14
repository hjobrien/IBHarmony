package matching;

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
		//With my new code this shouldn't ever come up
		//but i guess it's better safe than sorry
		if (this.getName().equals(o.getName()))
			return false;
		
		/*could easily be changed with this equation: 
		 * if (Math.abs(this.getGrade() - o.getGrade()) > 1)
		 */
		if (this.getGrade() != o.getGrade())
			return false;
		
		//these two shouldn't be changed unless we account for people
		//who are open to either gender
		if (this.getGender() != o.getGenderPreference())
			return false;
		if (this.getGenderPreference() != o.getGender())
			return false;
		
		//if none of the above statements trigger:
		return true;
	}
	
}
