package com.effortlogger.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortlogger.implementation.dao.UserRepository;
import com.effortlogger.implementation.dto.UserRegisteredDTO;

@Controller
@RequestMapping("/editTask")
public class editTaskController {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping
    public String displayDashboard(Model model){
        return "editTask";
    }

	@PostMapping
    public String registerUserAccount(@ModelAttribute("user") 
              UserRegisteredDTO registrationDto) {
        return "redirect:/editTask";
    }

}
