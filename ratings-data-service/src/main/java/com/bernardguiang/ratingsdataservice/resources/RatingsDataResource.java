package com.bernardguiang.ratingsdataservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardguiang.ratingsdataservice.models.Rating;

@RestController
@RequestMapping("/ratings")
public class RatingsDataResource {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
}
