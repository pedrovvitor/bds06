package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieByGenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    final MovieRepository movieRepository;
    final ReviewRepository reviewRepository;
    final GenreRepository genreRepository;
    final UserRepository userRepository;

    public MovieService(MovieRepository movieRepository, ReviewRepository reviewRepository, GenreRepository genreRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    public Page<MovieByGenreDTO> findByGenre(Long genreId, Pageable pageable) {
        if(genreId == 0L) {
            return movieRepository.findAllByOrderByTitle(pageable).map(MovieByGenreDTO::new);
        }
        return movieRepository.findByGenreIdOrderByTitle(genreId, pageable).map(MovieByGenreDTO::new);
    }

    public MovieDTO findById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        genreRepository.findAll();
        return new MovieDTO(movie);

    }

    public List<ReviewDTO> findReviewsByMovieId(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId).stream().map(ReviewDTO::new).collect(Collectors.toList());
    }
}
