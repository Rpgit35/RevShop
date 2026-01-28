package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import util.DBUtil;

public class ReviewDAO {

    public boolean addReview(Review r) {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO reviews VALUES (reviews_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)")) {

            ps.setInt(1, r.getProductId());
            ps.setString(2, r.getBuyerEmail());
            ps.setInt(3, r.getRating());
            ps.setString(4, r.getComment());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Review> getReviewsByProduct(int productId) {
        List<Review> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM reviews WHERE product_id = ?")) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Review r = new Review();
                r.setReviewId(rs.getInt("review_id"));
                r.setProductId(rs.getInt("product_id"));
                r.setBuyerEmail(rs.getString("buyer_email"));
                r.setRating(rs.getInt("rating"));
                r.setComment(rs.getString("review_comment"));

                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
