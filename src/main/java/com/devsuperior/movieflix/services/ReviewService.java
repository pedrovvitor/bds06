package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    final ReviewRepository reviewRepository;
    final AuthService authService;
    final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, AuthService authService, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.authService = authService;
        this.movieRepository = movieRepository;
    }

    public ReviewDTO postReview(ReviewDTO reviewDTO) {
        User user = authService.authenticated();
        Movie movie = movieRepository.findById(reviewDTO.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException("Movie not found")
        );
        Review review = reviewRepository.save(new Review(null, reviewDTO.getText(), user, movie));
        return new ReviewDTO(review);
    }
}
