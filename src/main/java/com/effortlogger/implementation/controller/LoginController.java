package com.effortlogger.implementation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortlogger.implementation.dao.UserRepository;
import com.effortlogger.implementation.dto.UserLoginDTO;
import com.effortlogger.implementation.model.User;
import com.effortlogger.implementation.service.DefaultUserService;



@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private DefaultUserService userService;
	
	@Autowired
	UserRepository userRepo;
    
    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }
    
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping
	public void loginUser(@ModelAttribute("user") 
	UserLoginDTO userLoginDTO) {
		System.out.println("UserDTO"+userLoginDTO);
		 userService.loadUserByUsername(userLoginDTO.getUsername());
	}
	
	@GetMapping("/otpVerification")
	public String otpSent(Model model,UserLoginDTO userLoginDTO) {
		model.addAttribute("otpValue", userLoginDTO);
		return "otpScreen";
		
	}
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/otpVerification")
	public String otpVerification(@ModelAttribute("otpValue") UserLoginDTO userLoginDTO, HttpSession session) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User users = userRepo.findByEmail(user.getUsername());
		//setting user for this session 
		session.setAttribute("user", user);
		if(users.getOtp() == userLoginDTO.getOtp()) {
			System.out.println(users.getRoleUser());
			if(users.getRoleUser().equals("Manager")) return "redirect:/managerPage";
			else return "redirect:/employeePage";
		}
		
		else
			return "redirect:/login/otpVerification?error";
	}
	
}
