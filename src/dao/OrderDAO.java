package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Order;
import model.OrderItem;
import util.DBUtil;

public class OrderDAO {

    public int createOrder(Order order) {
        int orderId = 0;
        try (Connection con = DBUtil.getConnection()) {

            // Insert order
            String orderSql =
                "INSERT INTO orders VALUES (orders_seq.NEXTVAL, ?, SYSDATE, ?)";

            PreparedStatement ps = con.prepareStatement(
                orderSql, new String[] {"order_id"});

            ps.setString(1, order.getBuyerEmail());
            ps.setDouble(2, order.getTotalAmount());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert items
            String itemSql =
                "INSERT INTO order_items VALUES (order_items_seq.NEXTVAL, ?, ?, ?, ?)";

            for (OrderItem item : order.getItems()) {
                PreparedStatement ips = con.prepareStatement(itemSql);
                ips.setInt(1, orderId);
                ips.setInt(2, item.getProductId());
                ips.setInt(3, item.getQuantity());
                ips.setDouble(4, item.getPrice());
                ips.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }
}
