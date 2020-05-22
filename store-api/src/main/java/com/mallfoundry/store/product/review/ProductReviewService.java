package com.mallfoundry.store.product.review;

public interface ProductReviewService {

    ProductReview createReview();

    void addReview(ProductReview review) throws ProductReviewException;

    void approveReview(String reviewId) throws ProductReviewException;

    void disapproveReview(String reviewId) throws ProductReviewException;
}
