
public class Match {
	private int matchScore;
	private Person p1;
	private Person p2;

	public Match (Person p1, Person p2, int[] weights){
		this.p1 = p1;
		this.p2 = p2;
		this.matchScore = getGoodFitCount(p1, p2, weights);
	}
	
	public static int getGoodFitCount(Person p1, Person p2, int[] weights) {
		int tempGoodFitCount = 0;
		String p1Answers = p1.getAnswers();
		String p2Answers = p2.getAnswers();
		for(int k = 0; k < p1Answers.length(); k++){
			if(p1Answers.charAt(k) == p2Answers.charAt(k)){
				//increments the similarity index by the weight for each question 
				//(change weights at the top)
				tempGoodFitCount+=weights[k];
			}
		}
		return tempGoodFitCount;
	}

	public int getMatchScore() {
		return matchScore;
	}

	public Person getP1() {
		return p1;
	}

	public Person getP2() {
		return p2;
	}
	
	
	
}
