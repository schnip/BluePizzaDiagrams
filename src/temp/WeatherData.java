package temp;

public class WeatherData implements IWeatherData {

	private double temperature;
	private double humidity;
	private double pressure;
	
	public WeatherData(double temperature, double humidity, double pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
	}
	
	@Override
	public double getTemperature() {
		return this.temperature;
	}

	@Override
	public double getHumidity() {
		return this.humidity;
	}

	@Override
	public double getPressure() {
		return this.pressure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(humidity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pressure);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(temperature);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherData other = (WeatherData) obj;
		if (Double.doubleToLongBits(humidity) != Double
				.doubleToLongBits(other.humidity))
			return false;
		if (Double.doubleToLongBits(pressure) != Double
				.doubleToLongBits(other.pressure))
			return false;
		if (Double.doubleToLongBits(temperature) != Double
				.doubleToLongBits(other.temperature))
			return false;
		return true;
	}
}