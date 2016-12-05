package br.ufmg.dcc.nanocomp.peg;

/**
 * Represents an expression Parser
 * @author Jeronimo Nunes Rocha
 *
 */
public interface Parser<T> {

	/**
	 * Parses a given String and returns the evaluate object
	 * @param in The string to be parsed
	 * @return The parsed object
	 */
	public T parse(CharSequence in);
	
}
