package service;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private Map<Integer, Integer> cart = new HashMap<>();
    // productId -> quantity

   public void addToCart(int productId, int quantity) {
    if (cart.containsKey(productId)) {
        cart.put(productId, cart.get(productId) + quantity);
    } else {
        cart.put(productId, quantity);
    }
}


    public void removeFromCart(int productId) {
        cart.remove(productId);
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }
}
