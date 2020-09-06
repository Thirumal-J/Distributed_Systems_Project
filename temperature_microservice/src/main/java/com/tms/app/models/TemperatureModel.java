package com.tms.app.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "temperaturetable")
public class TemperatureModel {

	@Id
	@Column(name="createdTime", unique=true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-dd-MM HH:mm:ss",timezone = "Europe/Berlin")
	private Date createdTime;
	private String place;
	private String country;
	private String unit;
	private String value;
	
	public TemperatureModel() {
	}

	public TemperatureModel(Date createdTime, String place, String country, String unit, String value) {
		super();
		this.createdTime = createdTime;
		this.place = place;
		this.country = country;
		this.unit = unit;
		this.value = value;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemperatureModel [createdTime=");
		builder.append(createdTime);
		builder.append(", place=");
		builder.append(place);
		builder.append(", country=");
		builder.append(country);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
	

}