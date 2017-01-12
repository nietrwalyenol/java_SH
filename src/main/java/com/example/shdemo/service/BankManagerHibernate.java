package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Donation;
import com.example.shdemo.domain.Fluid;


@Component
@Transactional
public class BankManagerHibernate implements BankManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory _sessionFactory){
		this.sessionFactory = _sessionFactory;
	}

	@Override
	public void addDonation(Donation donation) {
		donation.setId(null);
		sessionFactory.getCurrentSession().persist(donation);	
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Donation> getAllDonation() {
		return sessionFactory.getCurrentSession().getNamedQuery("donation.all").list();
	}

	@Override
	public void deleteDonation(Donation donation) {
		donation = (Donation) sessionFactory.getCurrentSession().get(Donation.class, donation.getId());
		for(Fluid fluid : donation.getFluids()){
			sessionFactory.getCurrentSession().delete(fluid);
		}
		sessionFactory.getCurrentSession().delete(donation);	
	}

	@Override
	public Donation findDonationbyId(Long id) {
		return (Donation) sessionFactory.getCurrentSession().get(Donation.class, id);
	}

	@Override
	public Donation findDonationbyPlace(String place) {
		List<Donation> donations =  sessionFactory.getCurrentSession().getNamedQuery("donation.byPlace").setString("place", place).list();
		if(donations.size() == 0){
			return null;
		}else{
			return donations.get(0);
		}
	}
	@Override
	public boolean editDonation(Donation donation) {
		try{
			sessionFactory.getCurrentSession().update(donation);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public Long addNewFluid(Fluid fluid) {
		fluid.setId(null);
		return (Long)sessionFactory.getCurrentSession().save(fluid);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Fluid> getAllFluid() {
		return sessionFactory.getCurrentSession().getNamedQuery("fluid.all").list();
	}

	@Override
	public void deleteFluid(Fluid fluid) {
		Fluid _fluid = (Fluid) sessionFactory.getCurrentSession().get(Fluid.class, fluid.getId());
		
		List<Donation> donation = getAllDonation();
		for(Donation d : donation){
			for(Fluid f : d.getFluids()){
				if(f.getId() == _fluid.getId()){
					d.getFluids().remove(f);
					sessionFactory.getCurrentSession().update(d);
					break;
				}
			}
		}
		sessionFactory.getCurrentSession().delete(_fluid);
	}

	@Override
	public Fluid findFluidById(Long id) {
		return (Fluid) sessionFactory.getCurrentSession().get(Fluid.class, id);
	}

	@Override
	public boolean editFluid(Fluid fluid) {
		try{
			sessionFactory.getCurrentSession().update(fluid);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public List<Fluid> getOwnedFluid(Donation donation) {
		donation = (Donation) sessionFactory.getCurrentSession().get(Donation.class, donation.getId());
		List<Fluid> fluid = new ArrayList<Fluid>(donation.getFluids());
		return fluid;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Fluid> getFreeFluid() {
		return sessionFactory.getCurrentSession().getNamedQuery("fluid.notSold").list();
	}

	@Override
	public void sellFluid(Long donationId, Long fluidId) {
		Donation donation = (Donation) sessionFactory.getCurrentSession().get(Donation.class, donationId);
		Fluid fluid = (Fluid) sessionFactory.getCurrentSession().get(Fluid.class, fluidId);
		fluid.setInDonation(true);
		donation.getFluids().add(fluid);
	}

	
}

