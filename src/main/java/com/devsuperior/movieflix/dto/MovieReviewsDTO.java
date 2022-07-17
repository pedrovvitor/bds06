package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieReviewsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private List<ReviewDTO> reviews = new ArrayList<ReviewDTO>();

    public MovieReviewsDTO() {
    }

    public MovieReviewsDTO(Movie entity) {
        id = entity.getId();
        entity.getReviews().forEach((review -> reviews.add(new ReviewDTO(review))));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }
}
