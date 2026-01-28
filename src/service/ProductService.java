package service;

import java.util.List;
import dao.ProductDAO;
import model.Product;

public class ProductService {

    private ProductDAO dao = new ProductDAO();

    public boolean addProduct(Product p) {
        if (p.getPrice() <= 0) {
            System.out.println("Price must be positive");
            return false;
        }
        return dao.addProduct(p);
    }

    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }
}
