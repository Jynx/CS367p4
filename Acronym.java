/**
 * This class is an implementation of the java Acronym class (with overrides
 * on the equals and toString methods.
 * bugs: None known
 * 
 * @author Steven Volocyk
 * 
 */
public class Acronym implements Comparable<Acronym> {

	private String mAcronym; // acronym name 
	private String mMeaning; // acronym meaning 
	/**
	 * constructor for acronym object
	 * will throw IllegalArgumentException for incorrect input.
	 * @param acronym name of acronym
	 * @param meaning meaning of acronym
	 */
	public Acronym(String acronym, String meaning){
		if (!valid(acronym) || !valid(meaning) ) {
			throw new IllegalArgumentException();
		}
		mAcronym = acronym;
		mMeaning = meaning;
	}
	
	/**
	 * Method used for checking validity of acronym name or meaning
	 * @param s string to be validated
	 * @return true if valid else false
	 */
	public boolean valid(String s) {
		if(s.length() < 2 || (Character.isWhitespace(s.charAt(0)))
				|| s.contains(":")) {
			return false;
		} else { return true; }
	}
	
	/**
	 * Compares two acronym names against one another.
	 * @param acronym object 
	 * @return returns 
	 */
	public int compareTo(Acronym acro) {
		return mAcronym.compareTo(acro.mAcronym);
	}
	
	/**
	 * returns the name of the Acronym associated with
	 * the acronym object
	 * @return mAcronym meaning.
	 */
	public String getAcronym() {
		return mAcronym;
	}
	
	/**
	 * gets the meaning of the acronym
	 * @return meaning of the acronym associated with
	 * given acronym object
	 */
	public String getMeaning() {
		return mMeaning;
	}
	
	/**
	 * Used for modifying an acronym name. Will handle Illegal
	 * Argument Exception
	 * @param newAcronym String to replace current definition
	 */
	public void setAcronym(String newAcronym) {
		if(!valid(newAcronym)) {
			throw new IllegalArgumentException();
		}
		else { mAcronym = newAcronym; }
	}
	
	/**
	 * Used for modifying an acronym meaning. Will handle
	 * IllegalArgumentException
	 * @param newMeaning new meaning of acronym
	 */
	public void setMeaning(String newMeaning) {
		if(!valid(newMeaning)) {
			throw new IllegalArgumentException();
		}
		else {mMeaning = newMeaning; }
	}

	/** Will check to make sure the object is of type Acronym
	 * and will compare two meanings.
	 * @param Object to compare
	 * @return true if equal, else false.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Acronym)) {
			 return false;
			}
		if ( mMeaning.equalsIgnoreCase(((Acronym)other).getMeaning())) {
			return true;
		}
		else { return false; }
	}
	
	/**
	 * Converts a given string to the format acronym(Meaning) 
	 * @return formatted string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(mAcronym + "(" + mMeaning + ")");
		return builder.toString();
	}
}

