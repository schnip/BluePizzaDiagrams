package testing.asm;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestDiagramOutputer {
	
	@SuppressWarnings("resource")
	@Test
	public void testImplements() throws IOException {
		String [] args = {"problem.asm.outputer.ConsoleOutputer", "problem.asm.outputer.IOutputData"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("edge [ arrowhead = \"empty\", style = \"dashed\" ]\nproblemasmoutputerConsoleOutputer -> problemasmoutputerIOutputData")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testExtends() throws IOException {
		String [] args = {"problem.food.Desert", "problem.food.IceCream"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("edge [ arrowhead = \"empty\", style = \"none\" ]\nproblemfoodIceCream -> problemfoodDesert")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testAssociate() throws IOException {
		String [] args = {"problem.observer.AppLauncher", "problem.observer.AppReporter"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("edge [ arrowhead = \"vee\", style = \"none\" ]\nproblemobserverAppLauncher -> problemobserverAppReporter")) {
			// We're all good
		} else {
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testUses() throws IOException {
		String [] args = {"problem.observer.IAppData", "problem.observer.CreateHandler"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		System.out.println(content);
		if (content.contains("edge [ arrowhead = \"vee\", style = \"dashed\" ]\nproblemobserverCreateHandler -> problemobserverIAppData")) {
			// We're all good
		} else {
			fail();
		}
	}

}
