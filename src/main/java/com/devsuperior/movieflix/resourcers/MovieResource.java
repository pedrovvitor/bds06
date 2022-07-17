package com.devsuperior.movieflix.resourcers;

import com.devsuperior.movieflix.dto.MovieByGenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Page<MovieByGenreDTO>> findByGenre(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId, Pageable pageable) {
        return ResponseEntity.ok().body(movieService.findByGenre(genreId, pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsByMovieId(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.findReviewsByMovieId(id));
    }
}
