package com.bernardguiang.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bernardguiang.moviecatalogservice.models.CatalogItem;
import com.bernardguiang.moviecatalogservice.models.Movie;
import com.bernardguiang.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {

		// Pretend this was grabbed from our database using userId
		List<String> userMoviesIds = new ArrayList<>();
		userMoviesIds.add("avng1");
		userMoviesIds.add("jwick1");
		userMoviesIds.add("sprdm1");
		
		// movie-info-service: Get List of Movie Info with Movie IDs
		List<Movie> movies = new ArrayList<>();
		for(String movieId : userMoviesIds) {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + movieId, Movie.class);
			movies.add(movie);
		}
		
		List<CatalogItem> catalogItems = new ArrayList<>();
		
		// ratings-data-service: Get Movie Ratings by id
		for(Movie movie : movies) {
			Rating rating = restTemplate.getForObject("http://ratings-data-service/ratings/" + movie.getMovieId(), Rating.class);
			CatalogItem c = new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
			catalogItems.add(c);
		}
		
		return catalogItems;
	}
}
