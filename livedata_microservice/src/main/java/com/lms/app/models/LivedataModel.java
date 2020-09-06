package com.lms.app.models;

public class LivedataModel {

	private String createdTime;
	private String country;
	private String place;
	private HumidityModel humidity;
	private TemperatureModel temperature;
	private PressureModel pressure;
	
	public LivedataModel() {
		super();
	}
	public LivedataModel(String createdTime, String country, String place, HumidityModel humidity,
			TemperatureModel temperature, PressureModel pressure) {
		super();
		this.createdTime = createdTime;
		this.country = country;
		this.place = place;
		this.humidity = humidity;
		this.temperature = temperature;
		this.pressure = pressure;
	}
	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the humidity
	 */
	public HumidityModel getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(HumidityModel humidity) {
		this.humidity = humidity;
	}
	/**
	 * @return the temperature
	 */
	public TemperatureModel getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(TemperatureModel temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the pressure
	 */
	public PressureModel getPressure() {
		return pressure;
	}
	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(PressureModel pressure) {
		this.pressure = pressure;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LivedataModel [createdTime=");
		builder.append(createdTime);
		builder.append(", country=");
		builder.append(country);
		builder.append(", place=");
		builder.append(place);
		builder.append(", humidity=");
		builder.append(humidity);
		builder.append(", temperature=");
		builder.append(temperature);
		builder.append(", pressure=");
		builder.append(pressure);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}