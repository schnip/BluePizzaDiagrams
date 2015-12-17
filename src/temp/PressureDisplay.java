package temp;

public class PressureDisplay implements IDisplay {

	@Override
	public void dataChanged(IWeatherData data) {
		System.out.format("Pressure = %f%n", data.getPressure());
	}

}
