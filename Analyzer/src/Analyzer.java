public class Analyzer {
	
	/**
	 * Construct an Analyzer.
	 */
	public Analyzer(){
	}

	/**
	 * Analyze a block and return it with indentation and scope info.
	 * @param input the block to analyze
	 */
	public String analyze(String input) {
		// TODO: This dummy version produces the correct output for the
		// sample input but totally ignores its actual input -- it would
		// produce the same output for any input.  You should strip it out
		// and replace it with a real version.
		return 
				"begin\n" +
				"  pass\n" +
				"  declare x {declaration 1}\n" + 
				"  use y {illegal undeclared use}\n" +
				"  declare y {declaration 2}\n" +
				"  begin\n" +
				"    use x {references declaration 1}\n" +
				"    declare x {declaration 3}\n" +
				"    use x {references declaration 3}\n" +
				"    declare x {illegal redeclaration}\n" +
				"    use x {references declaration 3}\n" +
				"    declare y {declaration 4}\n" + 
				"  end\n" +
				"  use x {references declaration 1}\n" +
				"end\n";
	}

}
