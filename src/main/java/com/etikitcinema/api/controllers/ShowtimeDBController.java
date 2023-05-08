package com.etikitcinema.api.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.etikitcinema.api.models.Movie;
import com.etikitcinema.api.models.Showtime;
import com.etikitcinema.api.models.Ticket;
import com.etikitcinema.api.services.ShowtimeService;
import com.etikitcinema.api.services.TicketService;

@RestController
@RequestMapping("/")
@CrossOrigin("http://localhost:3000")
public class ShowtimeDBController {
	private final ShowtimeService showtimeServ;
	private final TicketService ticketServ;
	
	public ShowtimeDBController(ShowtimeService showtimeServ, TicketService ticketServ) {
		this.showtimeServ = showtimeServ;
		this.ticketServ = ticketServ;
	}
	@GetMapping("/showtime/data")
	public String showtimeOption(Model model){
		List<String> startTime = Arrays.asList("8:00AM", "10:00AM", "2:00PM", "4:00PM", "6:00PM", "8:00PM");
		model.addAttribute("startTime", startTime);
		return "showtime-option";
	}
	
	@PostMapping("/showtime/data/all")
	public Showtime createShowtime
	(@RequestParam("startTime") Date startTime) {
		Showtime newShowtime = new Showtime(startTime);
		return showtimeServ.create(newShowtime);
	}
	
	@GetMapping("/showtime/data/{id}")
	public Showtime findOneShowtime(@PathVariable("id") Long id) {
		return showtimeServ.findOne(id);
	}
	@PutMapping("/showtime/data/{id}")
	public Showtime editOneShowtime(
		@PathVariable("id") Long id,
		@RequestParam("startTime") Date startTime){
		Showtime foundShowtime = showtimeServ.findOne(id);
		if(foundShowtime == null) {
			return foundShowtime;
		}
		foundShowtime.setStartTime(startTime);
		return showtimeServ.edit(foundShowtime);
	}
	@DeleteMapping("/showtime/all/data/{id}")
	public String deleteShowtime(@PathVariable("id") Long id) {
		showtimeServ.deleteShowtime(id);
		return id + " has been deleted √";
	}
}