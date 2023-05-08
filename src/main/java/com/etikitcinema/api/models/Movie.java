package com.etikitcinema.api.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name = "movies") 
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generates an auto incrementing 
	private Long id;
	
	@NotBlank(message = "Title is required!") // validation for strings
	private String title;
	
	@NotNull(message = "Description is required!")
	@Min(value = 15, message="Duration must be longer than 15 minutes!")
	private int duration;
	
	@NotNull
	@Min(value= 10, message="Price must be 10 dollars or higher")
	private int price;
	
	@NotNull
	@Min(value=1, message="pick a room between 1 to 10" )
	@Max(value=10, message="pick a room between 1 to 10")
	private int room;
	
	@NotBlank(message="Select a row and seat number")
	private String seat;
		
	@NotBlank(message = "Poster image is required!") 
	private String posterImage;
	
	@OneToOne(mappedBy = "movie",  cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Ticket ticket;
	
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	private List<Showtime> showtime;

    @Column(updatable = false)
	private Date createdAt;

	private Date updatedAt;

    // empty constructor
	public Movie() {
	}
    
	public Movie(String title, int duration, int price, int room, String seat, String posterImage) {
		this.title = title;
		this.duration = duration;
		this.price = price;
		this.room = room;
		this.seat = seat;
		this.posterImage = posterImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist // adds the created at date and time to sql on creation 
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // add the updated at date and time when being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}