package testing.asm;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import problem.asm.DesignParser;

@SuppressWarnings("unused")
public class TestPatternFinders {

	@SuppressWarnings("resource")
	@Test
	public void testSingleton() throws IOException {
		String [] args = {"-d", "problem.chocolate.singleton.ChocolateBoilerSingleton"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("\\<\\<Singleton\\>\\>\\")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testEagerSingleton() throws IOException {
		String [] args = {"-d", "problem.chocolate.singleton.ChocolateBoilerEagerSingleton"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("\\<\\<Singleton\\>\\>\\")) {
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
		if (content.contains("\\<\\<Singleton\\>\\>\\")) {
			fail();
		} else {
			// We're all good
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSingletonDesktop() throws IOException {
		String [] args = {"-d", "java.awt.Desktop"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (!content.contains("\\<\\<Singleton\\>\\>\\")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSingletonCalendar() throws IOException {
		String [] args = {"-d", "java.util.Calendar"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("\\<\\<Singleton\\>\\>\\")) {
			fail();
		} else {
			// We're all good
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSingletonStream() throws IOException {
		String [] args = {"-d", "java.io.FilterInputStream"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("\\<\\<Singleton\\>\\>\\")) {
			fail();
			// We're all good
		} else {
			
		}
	}
	
}
