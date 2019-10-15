package com.example.foodmann;

public class revies {
    private String reviews;
    private String stars;

    public revies() {
    }

    public revies(String reviews, String stars) {
        this.reviews = reviews;
        this.stars = stars;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
