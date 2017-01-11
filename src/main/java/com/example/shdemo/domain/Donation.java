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

})

public class Donation {

	private Long id;
	private String place = "";
	private Date donationDate = new Date();
  private List<Fluid> fluids = new ArrayList<Fluid>();

	public void setId(Long id) {
		this.id = id;
	}	
  
	public String getPlace() {
		return place;
	}
  
	public void setPlace(String place) {
		this.place = place;
	}
  
	public Date getDonationDate() {
		return donationDate;
	}
  
	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

}
