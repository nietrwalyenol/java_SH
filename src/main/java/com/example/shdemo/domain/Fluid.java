package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "fluid.all", query = "Select f from Fluid f"),
	@NamedQuery(name = "fluid.byId", query = "Select f from Fluid f where f.id = :id"),
})

public class Fluid {
  
	private Long id;
	private String type;
	private int volume;
	private int value;
	private Boolean availability = false;
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
  
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getAvailability() {
		return Availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

}
