package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.Fluid;
import com.example.shdemo.domain.Donation;

public interface BankManager {

	void addDonation(Donation donation);
	List<Donation> getAllDonation();
	void deleteDonation(Donation donation);
	Donation findDonationbyId(Long id);
	public Donation findDonationbyPlace(String place);
	public boolean editDonation(Donation donation);

	Long addNewFluid(Fluid fluid);
	List<Fluid> getAllFluid();
	void deleteFluid(Fluid fluid);
	Fluid findFluidById(Long id);
	public boolean editFluid(Fluid fluid);
	
	List<Fluid> getOwnedFluid(Donation donation);
	List<Fluid> getFreeFluid();
	void sellFluid(Long donationId, Long fluidId);
}
