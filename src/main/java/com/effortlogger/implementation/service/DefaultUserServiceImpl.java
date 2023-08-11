package com.effortlogger.implementation.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.effortlogger.implementation.dao.RoleRepository;
import com.effortlogger.implementation.dao.UserRepository;
import com.effortlogger.implementation.dto.UserRegisteredDTO;
import com.effortlogger.implementation.model.Role;
import com.effortlogger.implementation.model.User;


@Service
public class DefaultUserServiceImpl implements DefaultUserService{
   @Autowired
	private UserRepository userRepo;
	
   @Autowired
  	private RoleRepository roleRepo;
  	
   @Autowired
	 JavaMailSender javaMailSender;
   
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		User user = userRepo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public User save(UserRegisteredDTO userRegisteredDTO) {
		User temp_user = userRepo.findByEmail(userRegisteredDTO.getEmail_id());
		User user = new User();
		if(temp_user == null) {
			
			user.setEmail(userRegisteredDTO.getEmail_id());
			user.setName(userRegisteredDTO.getName());
			user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
			user.setRole(userRegisteredDTO.getRole());
		}
		else {
			throw new UsernameNotFoundException("User already Exists, Please logIn.");
		}
		System.out.println(user.getRoleUser());
		return userRepo.save(user);
		
	}

	@Override
	public String generateOtp(User user) {
		try {
			int randomPIN = (int) (Math.random() * 9000) + 1000;
			user.setOtp(randomPIN);
			userRepo.save(user);
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("f20170290h@alumni.bits-pilani.ac.in");
			msg.setTo(user.getEmail());

			msg.setSubject("Welcome To The EffortLogger");
			msg.setText("Hello \n\n" +"Your Login OTP :" + randomPIN + ".Please Verify. \n\n"+"Regards \n"+"ABC");

			javaMailSender.send(msg);
			
			return "success";
			}catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
	}

}
