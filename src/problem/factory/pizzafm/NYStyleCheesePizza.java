package problem.factory.pizzafm;

public class NYStyleCheesePizza extends Pizza {

	@SuppressWarnings("unchecked")
	public NYStyleCheesePizza() { 
		name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
 
		toppings.add("Grated Reggiano Cheese");
	}
}
