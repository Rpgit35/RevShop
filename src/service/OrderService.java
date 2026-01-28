package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderItem;
import model.Product;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private ProductDAO productDAO = new ProductDAO();

    public int checkout(String buyerEmail, Map<Integer, Integer> cart) {

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int productId = entry.getKey();
            int qty = entry.getValue();

            for (Product p : productDAO.getAllProducts()) {
                if (p.getProductId() == productId) {
                    OrderItem item = new OrderItem();
                    item.setProductId(productId);
                    item.setQuantity(qty);
                    item.setPrice(p.getPrice());
                    items.add(item);
                    total += p.getPrice() * qty;
                }
            }
        }

        Order order = new Order();
        order.setBuyerEmail(buyerEmail);
        order.setItems(items);
        order.setTotalAmount(total);

        return orderDAO.createOrder(order);
    }
}
