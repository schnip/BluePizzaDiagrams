package testing.asm;

import static org.junit.Assert.assertEquals;
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
	
	@SuppressWarnings("resource")
	@Test
	public void testSingletonRuntime() throws IOException {
		String [] args = {"-d", "java.lang.Runtime"};
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("\\<\\<Singleton\\>\\>\\")) {
			//fail();
		} else {
			// We're all good
			fail();
		}
	}
	
	// Testing Decorator Pattern finding:
	
	@SuppressWarnings("resource")
	@Test
	public void testDecoratorSingleComponent() throws IOException {
		String [] args = {"-d", "headfirst.decorator.io.InputTest", "headfirst.decorator.io.LowerCaseInputStream", "headfirst.decorator.starbuzz.Beverage", 
				"headfirst.decorator.starbuzz.CondimentDecorator", "headfirst.decorator.starbuzz.DarkRoast", "headfirst.decorator.starbuzz.Decaf", 
				"headfirst.decorator.starbuzz.Espresso", "headfirst.decorator.starbuzz.HouseBlend", "headfirst.decorator.starbuzz.Milk", 
				"headfirst.decorator.starbuzz.Mocha", "headfirst.decorator.starbuzz.Soy", "headfirst.decorator.starbuzz.StarbuzzCoffee", "headfirst.decorator.starbuzz.Whip"
				};
	
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("\\<\\<component\\>\\>\\")) {
			//fail();
		} else {
			// We're all good
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testDecoratorfourtimes() throws IOException {
		String [] args = {"-d", "headfirst.decorator.io.InputTest", "headfirst.decorator.io.LowerCaseInputStream", "headfirst.decorator.starbuzz.Beverage", 
				"headfirst.decorator.starbuzz.CondimentDecorator", "headfirst.decorator.starbuzz.DarkRoast", "headfirst.decorator.starbuzz.Decaf", 
				"headfirst.decorator.starbuzz.Espresso", "headfirst.decorator.starbuzz.HouseBlend", "headfirst.decorator.starbuzz.Milk", 
				"headfirst.decorator.starbuzz.Mocha", "headfirst.decorator.starbuzz.Soy", "headfirst.decorator.starbuzz.StarbuzzCoffee", "headfirst.decorator.starbuzz.Whip"
				};
	
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		String checkString = "<decorator";
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
	
	@SuppressWarnings("resource")
	@Test
	public void testDecoratorColoring() throws IOException {
		String [] args = {"-d", "headfirst.decorator.io.InputTest", "headfirst.decorator.io.LowerCaseInputStream", "headfirst.decorator.starbuzz.Beverage", 
				"headfirst.decorator.starbuzz.CondimentDecorator", "headfirst.decorator.starbuzz.DarkRoast", "headfirst.decorator.starbuzz.Decaf", 
				"headfirst.decorator.starbuzz.Espresso", "headfirst.decorator.starbuzz.HouseBlend", "headfirst.decorator.starbuzz.Milk", 
				"headfirst.decorator.starbuzz.Mocha", "headfirst.decorator.starbuzz.Soy", "headfirst.decorator.starbuzz.StarbuzzCoffee", "headfirst.decorator.starbuzz.Whip"
				};
	
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		String checkString = "fillcolor=green";
		int lastIndex = 0;
		int count = 0;
		while(lastIndex != -1){

		    lastIndex = content.indexOf(checkString,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += checkString.length();
		    }
		}
		assertEquals(5, count);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testDecoratesArrow() throws IOException {
		String [] args = {"-d", "headfirst.decorator.io.InputTest", "headfirst.decorator.io.LowerCaseInputStream", "headfirst.decorator.starbuzz.Beverage", 
				"headfirst.decorator.starbuzz.CondimentDecorator", "headfirst.decorator.starbuzz.DarkRoast", "headfirst.decorator.starbuzz.Decaf", 
				"headfirst.decorator.starbuzz.Espresso", "headfirst.decorator.starbuzz.HouseBlend", "headfirst.decorator.starbuzz.Milk", 
				"headfirst.decorator.starbuzz.Mocha", "headfirst.decorator.starbuzz.Soy", "headfirst.decorator.starbuzz.StarbuzzCoffee", "headfirst.decorator.starbuzz.Whip"
				};
	
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		if (content.contains("headfirstdecoratorstarbuzzStarbuzzCoffee -> headfirstdecoratorstarbuzzEspresso")) {
			if (content.contains("edge [ arrowhead = \"vee\", style = \"none\"\n, label = \"decorates\" ]"));
			
		} else {
			// We're all good
			fail();
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testOnlyFourDecoratesArrows() throws IOException {
		String [] args = {"-d", "headfirst.decorator.io.InputTest", "headfirst.decorator.io.LowerCaseInputStream", "headfirst.decorator.starbuzz.Beverage", 
				"headfirst.decorator.starbuzz.CondimentDecorator", "headfirst.decorator.starbuzz.DarkRoast", "headfirst.decorator.starbuzz.Decaf", 
				"headfirst.decorator.starbuzz.Espresso", "headfirst.decorator.starbuzz.HouseBlend", "headfirst.decorator.starbuzz.Milk", 
				"headfirst.decorator.starbuzz.Mocha", "headfirst.decorator.starbuzz.Soy", "headfirst.decorator.starbuzz.StarbuzzCoffee", "headfirst.decorator.starbuzz.Whip"
				};
	
		DesignParser.main(args);
		String content = new Scanner(new File("dot/out.dot")).useDelimiter("\\Z").next();
		String checkString = "decorates";
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
