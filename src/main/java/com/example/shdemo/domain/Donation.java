package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "donation.all", query = "Select d from Donation d"),
	@NamedQuery(name = "donation.byId", query = "Select d from Donation d where d.id = :id"),
	@NamedQuery(name = "donation.byPlace", query = "Select d from Donation d where d.place = :place")
})

public class Donation {

	private Long id;
	private String place = "";
	private List<Fluid> fluids = new ArrayList<Fluid>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}	
	
	public void setId(Long id) {
		this.id = id;
	}	
  
	public String getPlace() {
		return place;
	}
  
	public void setPlace(String place) {
		this.place = place;
	}
  
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Fluid> getFluids() {
		return fluids;
	}
	public void setFluids(List<Fluid> fluids) {
		this.fluids = fluids;
	}

}
