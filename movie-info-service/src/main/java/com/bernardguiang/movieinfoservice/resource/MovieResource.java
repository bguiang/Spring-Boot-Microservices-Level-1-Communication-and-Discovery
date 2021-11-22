package com.bernardguiang.movieinfoservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardguiang.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		
		if(movieId.equals("avng1")) {
			return new Movie("avng1", "Avengers", "People in costume fight aliens");
		} else if(movieId.equals("jwick1")) {
			return new Movie("jwick1", "John Wick", "Famous retired man goes back to work");
		} else {
			return new Movie("sprdm1", "SpiderMan", "Boy gets bit by spider, gets all tingly");
		}
		
	}
}
