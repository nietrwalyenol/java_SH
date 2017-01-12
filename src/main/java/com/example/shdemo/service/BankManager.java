package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Fluid;
import com.example.shdemo.domain.Donation;

public interface BankManager {
	
	void addDonation(Donation donation);
	List<Donation> getAllDonations();
	void deleteDonation(Donation donation);
	Donation findDonationbyId(Long id);
	public Donation findDonationbyPlace(String place);
	public boolean editDonation(Donation donation);
	
	Long addNewFluid(Fluid fluid);
	List<Fluid> getAllFluids();
	void deleteFluid(Fluid fluid);
	Fluid findFluidById(Long id);
	public boolean editFluid(Fluid fluid);
	
	List<Fluid> getOwnedFluids(Donation donation);
	List<Fluid> getFreeFluids();
	void sellFluid(Long donationId, Long fluidId);
	
}
