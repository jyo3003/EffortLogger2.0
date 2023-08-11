package com.effortlogger.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortlogger.implementation.dao.UserRepository;

@Controller
@RequestMapping("/deleteTask")
public class deleteTaskController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping
    public String displayDashboard(Model model){
        return "deleteTask";
    }


}
