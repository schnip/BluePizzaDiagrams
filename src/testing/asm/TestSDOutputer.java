package testing.asm;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestSDOutputer {
	
	@SuppressWarnings("resource")
	@Test
	public void testSDbasic() throws IOException {
		String [] args = {"-s", "--class=java.util.Collections", "--method=shuffle", "-ajava.util.Collections", "--depth=2"};
		DesignParser.main(args);
		String content = new Scanner(new File("sd/out.sd")).useDelimiter("\\Z").next();
		if (content.contains("java.util.Collections:void=java/util/Random.<init>(java.util.List)\njava.util.Collections:void=java/util/Collections.shuffle(java.util.List)")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSDEdge2() throws IOException {
		String [] args = {"-s", "--class=java.util.Collections", "--method=shuffle", "-ajava.util.Collections", "--depth=2"};
		DesignParser.main(args);
		String content = new Scanner(new File("sd/out.sd")).useDelimiter("\\Z").next();
		if (content.contains("java/util/Collections:void=java/util/Collections.swap(java.util.List, java.util.Random)\n"
				+ "java/util/Collections:java.util.ListIterator=java/util/List.listIterator(java.util.List, java.util.Random)\n"
				+ "java/util/Collections:java.lang.Object=java/util/ListIterator.next(java.util.List, java.util.Random)\n"
				+ "java/util/Collections:void=java/util/ListIterator.set(java.util.List, java.util.Random)\n"
				+ "java.util.Collections:java.util.ListIterator=java/util/List.listIterator(java.util.List, java.util.Random)\n"
				+ "java.util.Collections:java.lang.Object=java/util/ListIterator.next(java.util.List, java.util.Random)\n"
				+ "java.util.Collections:void=java/util/ListIterator.set(java.util.List, java.util.Random)")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSD2Edge1() throws IOException {
		String [] args = {"-s", "--class=java.util.Collections", "--method=shuffle", "-ajava.util.Collections", "--depth=2"};
		DesignParser.main(args);
		String content = new Scanner(new File("sd/out.sd")).useDelimiter("\\Z").next();
		if (content.contains("java.util.Collections:java.util.Collections\njava/util/Collections:java/util/Collections\njava/util/List:java/util/List\n"
				+ "java/util/ListIterator:java/util/ListIterator\n"
				+ "java/util/Random:java/util/Random")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSD2ContainsCorrectEdges() throws IOException {
		String [] args = {"-s", "--class=java.util.Collections", "--method=shuffle", "-ajava.util.Collections", "--depth=2"};
		DesignParser.main(args);
		String content = new Scanner(new File("sd/out.sd")).useDelimiter("\\Z").next();
		String checkString = "List.size(java.util.List, java.util.Random)";
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = content.indexOf(checkString,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += checkString.length();
		    }
		}
		assertEquals(4, count);
	}

}
