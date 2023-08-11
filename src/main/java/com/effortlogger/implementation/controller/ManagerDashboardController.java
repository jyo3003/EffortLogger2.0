package com.effortlogger.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortlogger.implementation.dao.UserRepository;
import com.effortlogger.implementation.dto.UserLoginDTO;
import com.effortlogger.implementation.model.User;


@Controller
@RequestMapping("/managerPage")
public class ManagerDashboardController {
	@Autowired
	UserRepository userRepo;
	@GetMapping
    public String displayDashboard(Model model){
        return "managerPage";
    }
}
