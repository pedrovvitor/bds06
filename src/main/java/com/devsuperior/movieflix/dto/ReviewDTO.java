package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import java.io.Serializable;

public class ReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String text;
    private String userName;
    private Long movieId;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text, String userName, Long movieId) {
        this.id = id;
        this.text = text;
        this.userName = userName;
        this.movieId = movieId;
    }

    public ReviewDTO(Review entity) {
        id = entity.getId();
        text = entity.getText();
        movieId = entity.getMovie().getId();
        userName = entity.getUser().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getText() {
        return text;
    }

    public void setText(java.lang.String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
