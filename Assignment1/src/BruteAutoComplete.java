import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The BruteAutoComplete class will carry out the BruteAutoComplete methods
 * 
 * @author 		Jake Phillips
 * @version 	1.0	(4 Nov 2016)
 */
public class BruteAutoComplete implements AutoComplete {
	private ArrayList<Term> words;
	private static Scanner input;

	/**
	 * The main method will be the starting method of the 
	 * program.
	 * 
	 * @param args
	 */
	public static void main (String[]args) throws Exception
	{
		new BruteAutoComplete();
		
	}
	
	/**
	 * The BruteAutoComplete constructor this is where the data from the file is parsed
	 * and aded to the arrayList and this will also call the main menu
	 */
	public BruteAutoComplete() throws FileNotFoundException
	{
		  input = new Scanner(System.in);
		  File usersFile = new File("C:/College Work/Year 1/Semester 2/Programming/Eclipse workspace/Assignment1/Words/wordList.txt");
		  Scanner inUsers = new Scanner(usersFile);
		  String delims = "\\s+";//each field in the file is separated(delimited) by a space.
		  words= new ArrayList<Term>();
		  while (inUsers.hasNextLine()) {
		    // get user and rating from data source
		    String userDetails = inUsers.nextLine().trim();
		    // parse user details string
		    String[] userTokens = userDetails.split(delims);
		    //System.out.println(userTokens.length);

		    // output user data to console.
		    //System.out.println(userDetails);
		    if (userTokens.length > 1) {
		    	Double weights = Double.parseDouble(userTokens[0]);
		      words.add(new Term(weights,userTokens[1]));
		    }
		  }
		      //System.out.println("Weight: " + userTokens[0] + " Word: " + userTokens[1]);
		    
		  runMenu();
	}
	
	/**
	 * The runMenu will call the showMenu and will take in
	 * inputs from the user
	 */
	public void runMenu()
	{
		int n = showMenu();
		while (n !=0)
		{
			switch(n)
			{
			case 1:
				System.out.print(">>");
				//Gets prefix from user
				String term = input.next();
				System.out.print("The Weight of "+term+" is: ");
				System.out.println(weightOf(term));
			break;
			case 2:
				System.out.print(">>");
				//Gets prefix from user
				String prefix = input.next();
				System.out.print("The Term of prefix"+prefix+" is: ");
				System.out.println(bestMatch(prefix));
			break;
			case 3:
				System.out.print(">>");
				//Get prefix from user
				String prefixMatch = input.next();
				System.out.print("The K Value is >>");
				//Gets k from user
				int k = input.nextInt();
				System.out.print("The "+k+" Terms of prefix"+prefixMatch+" is: \n");
				Iterator<String> terms = matches(prefixMatch,k).iterator();
				//Prints all objects in terms List
				while(terms.hasNext())
					System.out.println(terms.next());
			break;
			default:
				System.out.println("Sorry Mate, Wrong input");
			break;
			}
			input.nextLine(); //Scanner bug fix
			n = showMenu();
		}
		System.exit(0);
	}
	/**
	 * The showMenu will be called by the runMenu method
	 * and will print out the the user can do
	 */
	public int showMenu()
	{
		//Prints out to the console
		System.out.println("1 - Weight of a Term");
		System.out.println("2 - Best Match of a Prefix");
		System.out.println("3 - k Matches of a Prefix");
		System.out.println("0 - Exit");
		System.out.print(">>");
		return input.nextInt();
	}
	
	/**
	 * The weightOf method will return the weight of a
	 * specified term
	 */
	@Override
	public double weightOf(String term) {
		if (term == null)
			throw new NullPointerException();
		for(Term word : words)
		{
			if(word.getWord().equals(term.toLowerCase().trim()))
				return word.getWeight();
		}
		return 0.0;
	}

	/**
	 * The bestMatch will find the term which best matches the
	 * input given by the user
	 */
	@Override
	public String bestMatch(String prefix) {
		if (prefix == null)
			throw new NullPointerException();
		for(Term word : words)
		{
			if(word.getWord().startsWith(prefix.toLowerCase().trim()))
				return word.getWord();
		}
		return null;
	}

	/**
	 * The Iterable method will take in a prefix and an int (k) and will
	 * return the terms which match the prefix
	 * It will print out K terms, which will be inoputed by
	 * the user
	 */
	@Override
	public Iterable<String> matches(String prefix, int k) {
		if (prefix == null)
			throw new NullPointerException();
		if(k <= 0)
			throw new IllegalArgumentException();
		ArrayList<String> finishList = new ArrayList<String>();
		for(Term word : words)
		{
			//adds k terms to finishList
			if(word.getWord().startsWith(prefix.toLowerCase().trim()) && k>0)
			{
				finishList.add(word.getWord());
				k--;
			}
		}
		return finishList;
	}
	
	
}