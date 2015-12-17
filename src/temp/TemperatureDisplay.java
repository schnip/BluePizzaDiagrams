package temp;

public class TemperatureDisplay implements IDisplay {

	@Override
	public void dataChanged(IWeatherData data) {
		System.out.format("Temperature = %f%n", data.getTemperature());
	}

}
