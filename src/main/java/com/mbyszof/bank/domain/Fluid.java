package com.mbyszof.bank.domain;

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
	private boolean inDonation = false;
	
	public boolean isInDonation() {
		return inDonation;
	}
	public void setInDonation(boolean inDonation) {
		this.inDonation = inDonation;
	}
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
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
