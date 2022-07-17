package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByGenreIdOrderByTitle(Long genreId, Pageable pageable);
    Page<Movie> findAllByOrderByTitle(Pageable pageable);
    Optional<Movie> findById(Long id);
}
