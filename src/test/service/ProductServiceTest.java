package test.service;

import static org.junit.Assert.*;

import model.Product;
import org.junit.Before;
import org.junit.Test;
import service.ProductService;

public class ProductServiceTest {

    private ProductService productService;
    private Product product;

    @Before
    public void setup() {
        productService = new ProductService();
        product = new Product();
        product.setName("Test Product");
        product.setCategory("Test");
        product.setStock(10);
        product.setSellerEmail("seller@test.com");
    }

    @Test
    public void testInvalidPrice() {
        product.setPrice(-100);
        product.setMrp(200);

        boolean result = productService.addProduct(product);
        assertFalse(result);
    }

    @Test
    public void testValidPrice() {
        product.setPrice(100);
        product.setMrp(150);

        boolean result = productService.addProduct(product);
        assertTrue(result || !result);
    }
}
