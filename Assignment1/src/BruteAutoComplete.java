import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BruteAutoComplete implements AutoComplete {
	private ArrayList<Term> words;
	private static Scanner input;

	
	public static void main (String[]args) throws Exception
	{
		new BruteAutoComplete();
		
	}
	

	public BruteAutoComplete() throws FileNotFoundException
	{
		/*input = new Scanner(System.in);
		File usersFile = new File("C:/College Work/Year 1/Semester 2/Programming/Eclipse workspace/Assignment1/Words/wordList.txt");
		  Scanner inWords = new Scanner(usersFile);
		  words= new ArrayList<Term>();
		  String delims = "[	]"; //Seperates feilds by tab.
		  while (inWords.hasNextLine()) {
		    // Gets the word from the arraylist
		    String word = inWords.nextLine();
		    //gets the other half of the fields that where seperated
		    String[] termArray = word.split(delims);
		    //assigns the weight and words to a variable
		    if (termArray.length > 1) {
		      double weight = Double.parseDouble(termArray[0]);
		      words.add(new Term(weight,termArray[1]));
		    }
		  }*/
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
	
	public void runMenu()
	{
		int n = showMenu();
		while (n !=0)
		{
			switch(n)
			{
			case 1:
				System.out.print(">>");
				String term = input.next();
				System.out.print("The Weight of "+term+" is: ");
				System.out.println(weightOf(term));
			break;
			case 2:
				System.out.print(">>");
				String prefix = input.next();
				System.out.print("The Term of prefix"+prefix+" is: ");
				System.out.println(bestMatch(prefix));
			break;
			case 3:
				System.out.print(">>");
				String prefixMatch = input.next();
				System.out.print("The K Value is >>");
				int k = input.nextInt();
				System.out.print("The "+k+" Terms of prefix"+prefixMatch+" is: \n");
				Iterator<String> terms = matches(prefixMatch,k).iterator();
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
	
	public int showMenu()
	{
		System.out.println("1 - Weight of a Term");
		System.out.println("2 - Best Match of a Prefix");
		System.out.println("3 - k Matches of a Prefix");
		System.out.println("0 - Exit");
		System.out.print(">>");
		return input.nextInt();
	}
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

	@Override
	public Iterable<String> matches(String prefix, int k) {
		if (prefix == null)
			throw new NullPointerException();
		if(k <= 0)
			throw new IllegalArgumentException();
		ArrayList<String> finishList = new ArrayList<String>();
		for(Term word : words)
		{
			if(word.getWord().startsWith(prefix.toLowerCase().trim()) && k>0)
			{
				finishList.add(word.getWord());
				k--;
			}
		}
		return finishList;
	}
	
	
}