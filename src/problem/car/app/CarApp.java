package problem.car.app;

import java.util.Arrays;

import problem.car.api.ICar;
import problem.car.api.ICarPart;
import problem.car.impl.Body;
import problem.car.impl.Car;
import problem.car.impl.Engine;
import problem.car.impl.Wheel;

public class CarApp {

	public static void main(String[] args) {
		ICarPart body = new Body("coupe", "alumunium");
		ICarPart engine = new Engine(6, 4);
		ICarPart wheel1 = new Wheel("Good Year", 7, 5);
		ICarPart wheel2 = new Wheel("Good Year", 7, 5);
		ICarPart wheel3 = new Wheel("Good Year", 7, 5);
		ICarPart wheel4 = new Wheel("Good Year", 7, 5);
		
		ICar car = new Car("123456789", 
				"Ford",
				"Escort Zx2",
				Arrays.asList(body, 
						engine, 
						wheel1,
						wheel2,
						wheel3,
						wheel4));
		
		System.out.println(car);
	}
}
