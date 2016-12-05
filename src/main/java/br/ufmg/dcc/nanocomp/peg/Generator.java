package br.ufmg.dcc.nanocomp.peg;

import java.util.Map;

public interface Generator {

	public Object generate(String grammar, Object options);
	
	/**
	 * Generates a new option object to be sent on {@link Generator#generate(String, Object)}
	 * @return
	 */
	public Map<String,Object> options();
}
