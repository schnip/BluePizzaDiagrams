package temp;

public class WeatherStation {
	public static void main(String[] args) {
		IWeatherReporter reporter = new WeatherReporter();
		IDisplay tDisp = new TemperatureDisplay();
		IDisplay hDisp = new HumidityDisplay();
		IDisplay pDisp = new PressureDisplay();
		
		reporter.registerDisplay(tDisp);
		reporter.registerDisplay(hDisp);
		reporter.registerDisplay(pDisp);
		
		IWeatherData data = new WeatherData(10, 11, 12);
		reporter.setWeatherData(data);
		data = new WeatherData(20, 20, 20);
		reporter.setWeatherData(data);
	}
}
