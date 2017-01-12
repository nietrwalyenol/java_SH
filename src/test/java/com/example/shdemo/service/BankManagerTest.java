package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Fluid;
import com.example.shdemo.domain.Donation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional	
public class BankManagerTest {

  @Autowired
	BankManager bankManager;
	private final String TYPE_1 = "Krew";
	private final String TYPE_2 = "Osocze";
	private final String TYPE_3 = "Limfa";
	private final int VOLUME_1 = 400;
	private final int VOLUME_2 = 200;
	private final int VALUE_1 = 300;
	private final int VALUE_2 = 150;
  
	@Before
	public void setup(){
		if(bankManager.findDonationByPlace(PLACE) == null){
		Fluid fluid = new Fluid();
		fluid.setType(TYPE_1);
		fluid.setVolume(VOLUME_1);
		fluid.setValue(VALUE_1);
		fluid.setInDonation(false);
		
		Donation donation = new Donation();
		donation.setType(TYPE);
		donation.getFluids().add(fluid);
		fluid.setInDonation(true);
		
		bankManager.addDonation(donation);
		}
		Fluid fluid1 = new Fluid();
		fluid1.setType("Krew");
		fluid1.setVolume(100);
		fluid1.setValue(75);
		fluid1.setInDonation(false);
		
		Donation donation1 = new Donation();
		donation1.setPlace("Szpital");
		donation1.getFluids().add(fluid1);
		fluid1.setInDonation(true);
		
		bankManager.addDonation(donation1);
  	}
  
	@Before
	public void setup1(){
		if(bankManager.findDonationByType(TYPE_1) == null){
		Fluid fluid = new Fluid();
		fluid.setType(TYPE_2);
		fluid.setVolume(VOLUME_1);
		fluid.setValue(VALUE_1);
		fluid.setInDonation(false);
		
		Donation donation = new Donation();
		donation.setType(TYPE_3);
		donation.getFluids().add(fluid);
		fluid.setInDonation(true);
		
		bankManager.addDonation(donation);
		}
 	}
	
	@Test
	public void addDonation(){
		Donation donation = new Donation();
		donation.setType(TYPE_1);
		
		bankManager.addDonation(donation);
		Donation recievedDonation = bankManager.findDonationByType(TYPE_1);
		assertEquals(TYPE_1, recievedDonation.getType());
	}
	
	@Test
	public void editDonation(){
		Donation donation = bankManager.findDonationByType(TYPE_2);
		kategoria.setType("Changed");
		Long DonationId = donation.getId();
		bankManager.editDonation(donation);
		
		Donation donation2 = bankManager.findDonationById(DonationId);
		
		assertEquals("Changed", donation2.getType());
	}
	
	@Test
	public void deleteDonation(){
		int DonationCount = bankManager.getAllDonations().size();
		
		Donation donation = bankManager.findDonationByType(TYPE_1);
		Long DonationId = donation.getId();
		bankManager.deleteDonation(donation);
		
		assertEquals(null, bankManager.findDonationById(DonationId));
		assertEquals(DonationCount - 1, bankManager.getAllDonations().size());
	}
	
	@Test
	public void getDonationById(){
		Donation donation = bankManager.findDonationByType(TYPE_1);
		
		Donation byId = bankManager.findDonationById(donation.getId());
		
		assertEquals(donation.getId(), byId.getId());
	}

    
  
}
