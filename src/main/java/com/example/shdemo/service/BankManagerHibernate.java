package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Fluid;
import com.example.shdemo.domain.Donation;

@Component
@Transactional
public class BankManagerHibernate implements BankManager {

}
