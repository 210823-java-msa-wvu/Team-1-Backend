package com.team1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer review_id;

    private String review;
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Posts post;


    public Reviews() {
    }

    public Reviews(Integer review_id, String review, Integer rating, Users user, Posts post) {
        this.review_id = review_id;
        this.review = review;
        this.rating = rating;
        this.user = user;
        this.post = post;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "review_id=" + review_id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
