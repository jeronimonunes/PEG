package br.ufmg.dcc.nanocomp.peg;

import org.junit.Test;

import junit.framework.Assert;

public class PEGTest {
	
	@Test
	public void testLibraryLoading() {
		PEG peg = PEG.getInstance();
		Assert.assertNotNull(peg);
	}
	
	@Test
	public void testParserGeneration() {
		PEG peg = PEG.getInstance();
		Object parser = peg.generate("start = 'a'");
		Assert.assertNotNull(parser);
	}
	
	@Test
	public void testSyntaxError() {
		PEG peg = PEG.getInstance();
		try {
			peg.generate("start = 'a");
			throw new AssertionError("Wrong grammar was accepted");
		} catch (Exception e) {
			//success
		}
	}
	
	public static interface NumberParser extends Parser<Number> { };
	
	@Test
	public void testParserCast() {
		PEG peg = PEG.getInstance();
		NumberParser parser = peg.generate("start = [0-9]* { return parseInt(text())}",NumberParser.class);
		Assert.assertNotNull(parser);
	}
	
	@Test
	public void testNumberParser() {
		PEG peg = PEG.getInstance();
		NumberParser parser = peg.generate("start = [0-9]* { return parseInt(text())}",NumberParser.class);
		Assert.assertEquals(421, parser.parse("421").intValue());
	}

}
