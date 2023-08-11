package com.effortlogger.implementation.controller;

import java.sql.Time;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortlogger.implementation.service.DefaultUserService;
import com.effortlogger.implementation.dto.UserRegisteredDTO;
import com.effortlogger.implementation.model.User;

@Controller
@RequestMapping("/createTask")
public class createTaskController {
	
	
	private DefaultUserService userService;

    
	@GetMapping
    public String displayDashboard(Model model){
        return "createTask";
    }
	
	@PostMapping
    public String registerUserAccount(@ModelAttribute("user") 
              UserRegisteredDTO registrationDto) {
        return "redirect:/createTask";
    }
	
	@PostMapping("/stop")
    public String stopTimer(@RequestBody Map<String, Object> payload, HttpSession session) {
        Time elapsedTime = (Time) payload.get("elapsedTime");
        
    
     // Retrieve user object from session
        User user = (User) session.getAttribute("user");
        System.out.println(user.getEmail());
        user.setTime(elapsedTime);
        
        
        return "success";
    }
	

}
