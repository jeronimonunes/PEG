# PEG
PEG.js is a simple parser generator for JavaScript that produces fast parsers with excellent error reporting. You can use it to process complex data or computer languages and build transformers, interpreters, compilers and other tools easily.

This project aims to bring its capabilities to the Java World, an easy to use alternative to projects like Antlr
 

## Getting started
Add the repository in your pom.xml:
```xml
<repositories>
	<repository>
		<id>peg-mvn-repo</id>
		<url>https://raw.github.com/jeronimonunes/PEG/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>
```

Include the dependency:
```xml
<dependency>
	<groupId>br.ufmg.dcc.nanocomp</groupId>
	<artifactId>peg</artifactId>
	<version>0.10.0</version>
</dependency>
```

## Your first parser

```java

public class Main {

	public static interface NumberParser extends Parser<Number> { };

	public static void main(String[] args) {
		PEG peg = PEG.getInstance();
		NumberParser parser = peg.generate("start = [0-9]* { return parseInt(text())}",NumberParser.class);
		System.out.println(parser.parse("421"));
	}

}
```