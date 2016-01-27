package problem.factory.pizzas;

public class CheesePizza extends Pizza {
	@SuppressWarnings("unchecked")
	public CheesePizza() {
		name = "Cheese Pizza";
		dough = "Regular Crust";
		sauce = "Marinara Pizza Sauce";
		toppings.add("Fresh Mozzarella");
		toppings.add("Parmesan");
	}
}
