package com.effortlogger.implementation.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.effortlogger.implementation.dto.UserRegisteredDTO;
import com.effortlogger.implementation.model.User;


public interface DefaultUserService extends UserDetailsService{

	User save(UserRegisteredDTO userRegisteredDTO);

	String generateOtp(User user);



	
}
