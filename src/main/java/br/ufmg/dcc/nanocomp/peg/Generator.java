package br.ufmg.dcc.nanocomp.peg;

import java.util.Map;

/**
 * A Parser Expression Generator
 * It allows you to generate parsers for a given grammar
 * @author Jeronimo Nunes Rocha
 *
 */
public interface Generator {

	/**
	 * Generates a new parser and returns it as a {@link Object}
	 * @param grammar The grammar to parse, as a string
	 * @param options The compiler options
	 * You have to create the options object using {@link #options()}
	 * and then set the necessary properties
	 * https://pegjs.org/documentation#generating-a-parser-javascript-api
	 * @return
	 */
	public Object generate(String grammar, Object options);
	
	/**
	 * Generates a new option object to be sent on {@link Generator#generate(String, Object)}
	 * @return A JavasScript object as a Java {@link Map}
	 */
	public Map<String,Object> options();
}
