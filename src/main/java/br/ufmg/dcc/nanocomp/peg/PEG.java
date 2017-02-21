package br.ufmg.dcc.nanocomp.peg;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Interface to access PEGjs library
 * @author Jerônimo Nunes Rocha
 *
 */
public class PEG implements Generator {
	
	private static final PEG instance = new PEG();

	private Generator peg;

	private Invocable invocable;

	private PEG() {
		try (InputStream pegIs = PEG.class.getResourceAsStream("/peg-0.10.0.min.js");
				Reader pegReader = new InputStreamReader(pegIs)){
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			invocable = (Invocable) engine;

			Bindings scope = engine.createBindings();
			engine.eval(pegReader,scope);
			engine.eval("peg.options = function(){return {}}",scope);
			peg = invocable.getInterface(scope.get("peg"), Generator.class);
			scope.clear();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static PEG getInstance() {
		return instance;
	}
	
	public Object generate(String grammar) {
		return generate(grammar, options());
	}
	
	@Override
	public Object generate(String grammar, Object options) {
		return peg.generate(grammar, options);
	}
	
	/**
	 * Generates a new {@link Parser} from the given grammar
	 * @param grammar The grammar to parse, as a {@link String}
	 * @param options The options to send to the compiler
	 * @param clazz The interface of {@link Parser}, to cast the generate object
	 * @see <a href="https://pegjs.org/documentation#generating-a-parser-javascript-api">PEGjs doc</a>
	 * @return An instance of clazz, backed by the {@link ScriptEngine}
	 */
	public <T extends Parser<?>> T generate(String grammar, Object options, Class<T> clazz) {
		return invocable.getInterface(generate(grammar, options), clazz);
	}
	
	/**
	 * Generates a new {@link Parser} from the given grammar
	 * @param grammar The grammar to parse, as a {@link String}
	 * @param clazz The interface of {@link Parser}, to cast the generate object
	 * @return An instance of clazz, backed by the {@link ScriptEngine}
	 */
	public <T extends Parser<?>> T generate(String grammar, Class<T> clazz) {
		return generate(grammar, options(), clazz);
	}
	
	/**
	 * Compiles the given grammar and returns the JavaScript source code
	 * @param grammar The grammar to compile
	 * @return A {@link String} with the JavaScript code
	 */
	public String compile(String grammar) {
		Map<String,Object> options = options();
		options.put("output", "source");
		return generate(grammar, options).toString();
	}

	@Override
	public Map<String, Object> options() {
		return peg.options();
	}

}
