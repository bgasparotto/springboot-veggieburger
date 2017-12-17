package com.bgasparotto.springboot.veggieburger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity that represents an address of the system.
 * 
 * @author Bruno Gasparotto
 *
 */
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 8,
		  max = 128,
		  message = "Adress line 1 size must be between {min} and {max}")
	private String addressLine1;
	
	@Size(max = 128, message = "Maximum adress line 2 size is {max}")
	private String addressLine2;
	
	@Size(min = 2,
		  max = 16,
		  message = "Post code size must be between {min} and {max}")
	private String postCode;
	
	@Size(min = 2,
		  max = 32,
		  message = "City name size must be between {min} and {max}")
	private String cityName;
	
	@Size(min = 2,
		  max = 32,
		  message = "State name length must be between {min} and {max}")
	private String stateName;
	
	@Valid
	@NotNull(message = "Country must be set")
	@ManyToOne
	private Country country;

	/**
	 * Constructor.
	 *
	 */
	public Address() {
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            The Address' {@code id}
	 * @param addressLine1
	 *            The Address' {@code addressLine1}
	 * @param addressLine2
	 *            The Address' {@code addressLine2}
	 * @param postCode
	 *            The Address' {@code postCode}
	 * @param cityName
	 *            The Address' {@code cityName}
	 * @param stateName
	 *            The Address' {@code stateName}
	 * @param country
	 *            The Address' {@code country}
	 */
	public Address(Long id, 
			String addressLine1, 
			String addressLine2,
			String postCode, 
			String cityName, 
			String stateName,
			Country country) {
		
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.postCode = postCode;
		this.cityName = cityName;
		this.stateName = stateName;
		this.country = country;
	}

	/**
	 * Gets the Address's {@code id}.
	 *
	 * @return The Address's {@code id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the Address's {@code addressLine1}.
	 *
	 * @return The Address's {@code addressLine1}
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Gets the Address's {@code addressLine2}.
	 *
	 * @return The Address's {@code addressLine2}
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Gets the Address's {@code postCode}.
	 *
	 * @return The Address's {@code postCode}
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * Gets the Address's {@code cityName}.
	 *
	 * @return The Address's {@code cityName}
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Gets the Address's {@code stateName}.
	 *
	 * @return The Address's {@code stateName}
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * Gets the Address's {@code country}.
	 *
	 * @return The Address's {@code country}
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the Address's {@code id}.
	 *
	 * @param id The Address's {@code id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the Address's {@code addressLine1}.
	 *
	 * @param addressLine1 The Address's {@code addressLine1} to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Sets the Address's {@code addressLine2}.
	 *
	 * @param addressLine2 The Address's {@code addressLine2} to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Sets the Address's {@code postCode}.
	 *
	 * @param postCode The Address's {@code postCode} to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * Sets the Address's {@code cityName}.
	 *
	 * @param cityName The Address's {@code cityName} to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Sets the Address's {@code stateName}.
	 *
	 * @param stateName The Address's {@code stateName} to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * Sets the Address's {@code country}.
	 *
	 * @param country The Address's {@code country} to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", addressLine1=");
		builder.append(addressLine1);
		builder.append(", addressLine2=");
		builder.append(addressLine2);
		builder.append(", postCode=");
		builder.append(postCode);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", stateName=");
		builder.append(stateName);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}
}