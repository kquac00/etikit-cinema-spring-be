package com.etikitcinema.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.etikitcinema.api.services.MovieService;
import com.etikitcinema.api.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	final UserService userServ;
	final MovieService movieServ;
	public HomeController(UserService userServ, MovieService movieServ) {
		this.userServ = userServ;
		this.movieServ = movieServ;
		
	}
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("user_id") ==null) {
			return "redirect:/admin/login/reg";
		}
		model.addAttribute("allMovie", movieServ.getAll());
		model.addAttribute("loggedInUser", userServ.getUser((Long)session.getAttribute("user_id")));
		return "main/dashboard.jsp";
	}
	
}
