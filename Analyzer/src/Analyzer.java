import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Analyzer is dependent on Java 1.7 to run
 * @author ielletso
 *
 */
public class Analyzer {

	/**
	 * Block indent spacing
	 */
	final static String INDENT = "  ";
	final static String SPACE = " ";
	/**
	 * The keywords of the language
	 */
	final static String[] KEYWORDS_ARRAY = new String[] { "begin", "end",
			"pass", "declare", "use" };
	/**
	 * The keywords of the language in Set form for easy membership checking
	 */
	// Learned how to implement from:
	// http://stackoverflow.com/questions/2041778/initialize-java-hashset-values-by-construction
	final static Set<String> KEYWORDS = new HashSet<String>(
			Arrays.asList(KEYWORDS_ARRAY));
	final static int BEGIN = 0;
	final static int END = 1;
	final static int PASS = 2;
	final static int DECLARE = 3;
	final static int USE = 4;

	/**
	 * Construct an Analyzer.
	 */
	public Analyzer() {
	}

	/**
	 * Analyze a block and return it with indentation and scope info.
	 * 
	 * @param input
	 *            the block to analyze
	 */
	public String analyze(String input) {
		Scanner scr = new Scanner(input);
		StringBuilder sb = new StringBuilder();
		ScopedMap<String, Integer> sm = new MaStScopedMap<String, Integer>();
		int declareCounter = 1;
		int blockLevel = 0; // TODO: change all instances to getNestingLevel();

		// Read input into ScopedMap assuming case insensitivity
		// TODO: convert to switch
		while (scr.hasNext()) {
			String next = scr.next().toLowerCase();
			
			switch (next) {
				
			}
			
			if (next.equals(KEYWORDS_ARRAY[BEGIN])) {
				sm.enterScope();
				sb.append(multiplyString(INDENT, blockLevel) + next + "\n");
				++blockLevel;
			} else if (next.equals(KEYWORDS_ARRAY[END])) {
				sm.exitScope();
				--blockLevel;
				sb.append(multiplyString(INDENT, blockLevel) + next + "\n");
			} else if (next.equals(KEYWORDS_ARRAY[PASS])) {
				sb.append(multiplyString(INDENT, blockLevel) + next + "\n");
			} else if (next.equals(KEYWORDS_ARRAY[DECLARE])) {
				sb.append(multiplyString(INDENT, blockLevel) + next + SPACE);
				next = scr.next();
				if (KEYWORDS.contains(next)) {
					throw new IllegalArgumentException();
				} else {
					// TODO: make finals
					String declaration = "";
					if (sm.isLocal(next)) {
						declaration = "illegal redeclaration";
					} else {
						declaration = "declaration" + SPACE + declareCounter;
						sm.put(next, declareCounter);
						++declareCounter;
					}
					sb.append(next + SPACE + "{" + declaration + "}" + "\n");
				}
			} else if (next.equals(KEYWORDS_ARRAY[USE])) {
				sb.append(multiplyString(INDENT, blockLevel) + next + SPACE);
				next = scr.next();
				if (KEYWORDS.contains(next)) {
					throw new IllegalArgumentException();
				} else {
					String useable = "";
					if (sm.get(next) == null) {
						useable = "illegal undeclared use";
					} else {
						String decNum = sm.get(next).toString();
						useable = "references declaration" + SPACE + decNum;
					}
					sb.append(next + SPACE + "{" + useable + "}" + "\n");
				}
			}
		}

		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * This allows python-like string multiplication
	 * 
	 * @param str
	 *            - the string to be multiplied
	 * @param count
	 *            - how many times to repeat the string
	 * @return a multiplied string
	 */
	// I got algorithm help from:
	// http://stackoverflow.com/questions/2255500/can-i-multiply-strings-in-java-to-repeat-sequences
	private String multiplyString(String str, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; ++i) {
			sb.append(str);
		}
		return sb.toString();
	}

}
