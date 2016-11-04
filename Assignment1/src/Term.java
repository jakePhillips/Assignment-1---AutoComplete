import java.util.ArrayList;

/**
 * The Term class
 */
public class Term {
	private double weight;
	private String word;
	
	@Override
	public String toString() {
		return "StoreWord [weight=" + weight + ", word=" + word + "]";
	}
	
	/**
	 * The constructor for the Term class
	 */
	public Term(Double weights, String term) {
		// Here is your error lol :3
		this.weight = weights;
		this.word = term;
	}
// GETTERS AND SETTERS //
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}




