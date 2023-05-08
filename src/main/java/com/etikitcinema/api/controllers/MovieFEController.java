package com.etikitcinema.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.etikitcinema.api.models.Movie;
import com.etikitcinema.api.services.MovieService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class MovieFEController {
	public final MovieService movieServ;
	
	public MovieFEController(MovieService movieServ) {
		this.movieServ = movieServ;
	}
	// Create method	
		@GetMapping("/create")
		public String createMovie(@ModelAttribute("movie") Movie movie, HttpSession session) {
			if(session.getAttribute ("user_id")== null) {
				return "redirect:/admin/login/reg";
			}
			return "movie/create.jsp";
		}
		@PostMapping("process/create")
		public String movieProcessCreate(@Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
			if(result.hasErrors()) {
				return "movie/create.jsp";
			}
			movieServ.create(movie);
			return "redirect:/";
		}
	// getAll method
		@GetMapping("/display/movie")
		public String allMovie(Model model, @ModelAttribute("movie") Movie movie) {
			model.addAttribute("allMovie", movieServ.getAll());
			return "/movie/showAll.jsp";
		}
	// findOne /edit method
		@GetMapping("/display/{id}")
		public String oneMovie(@PathVariable("id") Long id, Model model) {
			model.addAttribute("movie", movieServ.findOne(id));
			return "movie/display.jsp";
		}	
		
	// edit method
		@GetMapping("/edit/{id}")
		public String editMovie(Model model, @PathVariable("id") Long id, HttpSession session) {
			if(session.getAttribute("user_id") ==null) {
				return "redirect:/admin/login/reg";
			}
			model.addAttribute("movie", movieServ.findOne(id));
			return "/movie/edit.jsp";
		}
		@PutMapping("/process/edit/{id}")
		public String processEditMovie(@Valid @ModelAttribute("movie") Movie movie, 
				BindingResult result, @PathVariable("id") Long id) {
					
					if(result.hasErrors()) {
						return "movie/edit.jsp";
					}
					movieServ.edit(movie);
					return "redirect:/";
				}
		
		@DeleteMapping("/delete/{id}")
		public String deleteMovie(@PathVariable("id") Long id) {
			movieServ.deleteMovie(id);
			return "redirect:/";
		}
	}