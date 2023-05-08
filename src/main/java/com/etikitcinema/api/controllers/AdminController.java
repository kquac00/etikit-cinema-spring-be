package com.etikitcinema.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etikitcinema.api.models.LoginUser;
import com.etikitcinema.api.models.User;
import com.etikitcinema.api.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserService userServ;
	
public AdminController(UserService userServ) {
		this.userServ = userServ;
	}
	@GetMapping("/login/reg")
	public String loginReg(@ModelAttribute("newUser")User user, @ModelAttribute("loginUser") LoginUser loginUser){
		return "user/loginreg.jsp";
	}
	@PostMapping("/process/register")
	public String processRegisterUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model, HttpSession session) {
		
		if(!user.getPassword().equals(user.getConfirm())){
			result.rejectValue("password", "Match", "confirm password must match password");
		}
		if(userServ.getUser(user.getEmail())!=null) {
			result.rejectValue("email", "Unique", "email is already in use");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "user/loginreg.jsp";
		}
		User newUser = userServ.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/";
	}
	@PostMapping("/process/login")
	public String processLoginUser(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User loggingUser = userServ.login(loginUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "user/loginreg.jsp";
		}
		session.setAttribute("user_id", loggingUser.getId());
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login/reg";
	}
}

	