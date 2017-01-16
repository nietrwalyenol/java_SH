package com.mbyszof.bank.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mbyszof.bank.domain.Fluid;
import com.mbyszof.bank.domain.Donation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional	
public class BankManagerTest {
	
	@Autowired
	BankManager bankManager;
	private final String PLACE_1 = "Szpital";
	private final String PLACE_2 = "Piwnica";
	private final String TYPE_1 = "Krew";
	private final String TYPE_2 = "Osocze";
	private final String TYPE_3 = "Limfa";
	private final int VOLUME_1 = 400;
	private final int VOLUME_2 = 200;
	private final int VALUE_1 = 300;
	private final int VALUE_2 = 150;

	@Before
	public void setup(){
		if(bankManager.findDonationbyPlace(PLACE_1) == null){
		Fluid fluid = new Fluid();
		fluid.setType(TYPE_2);
		fluid.setVolume(VOLUME_1);
		fluid.setValue(VALUE_1);
		fluid.setInDonation(false);
		
		Donation donation = new Donation();
		donation.setPlace(PLACE_1);
		donation.getFluids().add(fluid);
		fluid.setInDonation(true);
		
		bankManager.addDonation(donation);
		}
		Fluid fluid1 = new Fluid();
		fluid1.setType("Trombocyty");
		fluid1.setVolume(300);
		fluid1.setValue(179);
		fluid1.setInDonation(false);
		
		Donation donation1 = new Donation();
		donation1.setPlace("Przychodnia");
		donation1.getFluids().add(fluid1);
		fluid1.setInDonation(true);
		
		bankManager.addDonation(donation1);
	}
	@Before
	public void setup1(){
		if(bankManager.findDonationbyPlace(PLACE_2) == null){
		Fluid fluid = new Fluid();
		fluid.setType(TYPE_3);
		fluid.setVolume(VOLUME_1);
		fluid.setValue(VALUE_1);
		fluid.setInDonation(false);
		
		Donation donation = new Donation();
		donation.setPlace(PLACE_2);
		donation.getFluids().add(fluid);
		fluid.setInDonation(true);
		
		bankManager.addDonation(donation);
		}
	}
	
	// DONATION TESTS 
	
	@Test
	public void addDonation(){
		Donation donation = new Donation();
		donation.setPlace(PLACE_2);
		
		bankManager.addDonation(donation);
		Donation recievedDonation = bankManager.findDonationbyPlace(PLACE_2);
		assertEquals(PLACE_2, recievedDonation.getPlace());
	}
	@Test
	public void editDonation(){
		Donation donation = bankManager.findDonationbyPlace(PLACE_1);
		donation.setPlace("Changed");
		Long DonationId = donation.getId();
		bankManager.editDonation(donation);
		
		Donation donation2 = bankManager.findDonationbyId(DonationId);
		
		assertEquals("Changed", donation2.getPlace());
	}
	@Test
	public void deleteDonation(){
		int DonationCount = bankManager.getAllDonation().size();
		
		Donation donation = bankManager.findDonationbyPlace(PLACE_1);
		Long DonationId = donation.getId();
		bankManager.deleteDonation(donation);
		
		assertEquals(null, bankManager.findDonationbyId(DonationId));
		assertEquals(DonationCount - 1, bankManager.getAllDonation().size());
	}
	@Test
	public void getDonationbyId(){
		Donation donation = bankManager.findDonationbyPlace(PLACE_1);
		
		Donation byId = bankManager.findDonationbyId(donation.getId());
		
		assertEquals(donation.getId(), byId.getId());
	}

	@Test
	public void getOwnedFluid(){
		Donation donation = bankManager.findDonationbyPlace("Szpital");
		List<Fluid> ownedFluid = bankManager.getOwnedFluid(donation);
		
		assertEquals("Osocze", ownedFluid.get(0).getType());
		assertEquals(400, ownedFluid.get(0).getVolume());
		assertEquals(300, ownedFluid.get(0).getValue());	
	}
	
	//FLUID TESTS
	
	@Test
	public void addFluid(){
		Fluid fluid = new Fluid();
		fluid.setType(TYPE_3);
		fluid.setVolume(VOLUME_2);
		fluid.setValue(VALUE_2);
		fluid.setInDonation(false);
		
		Long fluidId = bankManager.addNewFluid(fluid);
		Fluid retrievedFluid = bankManager.findFluidById(fluidId);
		
		assertEquals(TYPE_3, retrievedFluid.getType());
		assertEquals(VOLUME_2, retrievedFluid.getVolume());
		assertEquals(VALUE_2, retrievedFluid.getValue());
	}
	@Test
	public void getFluidId(){
		Donation donation = bankManager.findDonationbyPlace(PLACE_2);
		Fluid fluid = donation.getFluids().get(0);
		Fluid byId = bankManager.findFluidById(donation.getFluids().get(0).getId());
		
		assertEquals(fluid.getId(), byId.getId());
	}
	@Test
	public void editFluid(){
		Donation donation = bankManager.findDonationbyPlace(PLACE_2);
		Fluid fluid = donation.getFluids().get(0);
		fluid.setType("Limfocyty");
		fluid.setVolume(30);
		fluid.setValue(370);
		Long DonationId = donation.getId();
		bankManager.editFluid(fluid);
		
		Donation donation2 = bankManager.findDonationbyId(DonationId);
		
		assertEquals("Limfocyty", donation2.getFluids().get(0).getType());
		assertEquals(30, donation2.getFluids().get(0).getVolume());
		assertEquals(370, donation2.getFluids().get(0).getValue());
	}
	@Test
	public void deleteFluid(){
		int DonationCount = bankManager.getAllDonation().size();
		int FluidCount = bankManager.getAllFluid().size();
		
		Donation donation = bankManager.findDonationbyPlace(PLACE_1);
		int DonationForFluid = donation.getFluids().size();
		Fluid fluid = donation.getFluids().get(0);
		bankManager.deleteFluid(fluid);
		
		donation = bankManager.findDonationbyPlace(PLACE_1);
		
		assertEquals(DonationCount, bankManager.getAllDonation().size());
		assertEquals(FluidCount - 1, bankManager.getAllFluid().size());
		assertEquals(DonationForFluid - 1, donation.getFluids().size());
	}
	
}
