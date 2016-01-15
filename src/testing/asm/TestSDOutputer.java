package testing.asm;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestSDOutputer {
	
	@SuppressWarnings("resource")
	@Test
	public void testSDbasic() throws IOException {
		String [] args = {"-s --class=java.util.Collections --method=shuffle -ajava.util.Collections --depth=2"};
		DesignParser.main(args);
		String content = new Scanner(new File("sd/out.sd")).useDelimiter("\\Z").next();
		if (content.contains("java.util.Collections:void=java/util/Random.<init>\njava.util.Collections:void=java/util/Collections.shuffle")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSDbasic1() throws IOException {
		String [] args = {"-s --class=java.util.Collections --method=shuffle -ajava.util.Collections --depth=2"};
		DesignParser.main(args);
		String content = new Scanner(new File("sd/out.sd")).useDelimiter("\\Z").next();
		if (content.contains("java/util/Collections:java.util.ListIterator=java/util/List.listIterator\n"
				+ "java/util/Collections:java.lang.Object=java/util/ListIterator.next\n"
				+ "java/util/Collections:void=java/util/ListIterator.set\n"
				+ "java.util.Collections:java.util.ListIterator=java/util/List.listIterator\n"
				+ "java.util.Collections:java.lang.Object=java/util/ListIterator.next\n"
				+ "java.util.Collections:void=java/util/ListIterator.set")) {
			// We're all good
		} else {
			fail();
		}
	}

}
