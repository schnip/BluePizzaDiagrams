package testing.asm;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestPatternFinders {

	@SuppressWarnings("resource")
	@Test
	public void testSingleton() throws IOException {
		String [] args = {"-d", "problem.chocolate.singleton.ChocolateBoilerSingleton"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("<<Singleton>>")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testNotSingleton() throws IOException {
		String [] args = {"-d", "problem.chocolate.singleton.ChocolateBoiler"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("<<Singleton>>")) {
			fail();
		} else {
			// We're all good
		}
	}
	
}
