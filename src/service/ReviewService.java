package service;

import java.util.List;
import dao.ReviewDAO;
import model.Review;

public class ReviewService {

    private ReviewDAO dao = new ReviewDAO();

    public boolean addReview(Review r) {
        if (r.getRating() < 1 || r.getRating() > 5) {
            System.out.println("Rating must be between 1 and 5");
            return false;
        }
        return dao.addReview(r);
    }

    public List<Review> getReviews(int productId) {
        return dao.getReviewsByProduct(productId);
    }
}
