package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import util.DBUtil;

public class ProductDAO {

    private static final String INSERT_PRODUCT =
        "INSERT INTO products VALUES (products_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

    private static final String SELECT_ALL =
        "SELECT * FROM products";

    public boolean addProduct(Product p) {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrice());
            ps.setDouble(4, p.getMrp());
            ps.setString(5, p.getCategory());
            ps.setInt(6, p.getStock());
            ps.setString(7, p.getSellerEmail());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setMrp(rs.getDouble("mrp"));
                p.setCategory(rs.getString("category"));
                p.setStock(rs.getInt("stock"));
                p.setSellerEmail(rs.getString("seller_email"));

                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
