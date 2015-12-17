package temp;

public class HumidityDisplay implements IDisplay {

	@Override
	public void dataChanged(IWeatherData data) {
		System.out.format("Humidity = %f%n", data.getHumidity());
	}

}
