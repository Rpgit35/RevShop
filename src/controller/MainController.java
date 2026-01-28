package controller;

import java.util.Scanner;
import model.User;
import service.UserService;
import service.ProductService;
import service.CartService;
import service.OrderService;



public class MainController {

    private static UserService userService = new UserService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== RevShop =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void register(Scanner sc) {
        User user = new User();

        System.out.print("Name: ");
        user.setName(sc.nextLine());

        System.out.print("Email: ");
        user.setEmail(sc.nextLine());

        System.out.print("Password: ");
        user.setPassword(sc.nextLine());

        System.out.print("Role (BUYER/SELLER): ");
        user.setRole(sc.nextLine().toUpperCase());

        System.out.print("Security Question: ");
        user.setSecurityQuestion(sc.nextLine());

        System.out.print("Security Answer: ");
        user.setSecurityAnswer(sc.nextLine());

        boolean success = userService.registerUser(user);
        System.out.println(success ? "Registration successful!" : "Registration failed!");
    }

private static void login(Scanner sc) {
    System.out.print("Email: ");
    String email = sc.nextLine();

    System.out.print("Password: ");
    String password = sc.nextLine();

    User user = userService.login(email, password);

    if (user != null) {
        System.out.println("\nLogin successful! Welcome " + user.getName());
        if ("BUYER".equalsIgnoreCase(user.getRole())) {
            buyerDashboard(sc);
        } else {
            sellerDashboard(sc);
        }
    } else {
        System.out.println("Invalid email or password");
    }
}

private static void buyerDashboard(Scanner sc) {
    ProductService productService = new ProductService();
    CartService cartService = new CartService();
    OrderService orderService = new OrderService();

    while (true) {
        System.out.println("\n=== BUYER DASHBOARD ===");
        System.out.println("1. View Products");
	System.out.println("2. Add to Cart");
	System.out.println("3. View Cart");
	System.out.println("4. Checkout");
	System.out.println("5. Add Review");
	System.out.println("6. Logout");

        System.out.print("Choose: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            for (model.Product p : productService.getAllProducts()) {
                System.out.println("---------------------");
                System.out.println("ID: " + p.getProductId());
                System.out.println("Name: " + p.getName());
                System.out.println("Price: " + p.getPrice());
                System.out.println("Stock: " + p.getStock());
            }
        } else if (choice == 2) {
            System.out.print("Enter Product ID: ");
            int pid = sc.nextInt();
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();
            cartService.addToCart(pid, qty);
            System.out.println("Added to cart!");
        } else if (choice == 3) {
            System.out.println("Your Cart:");
            System.out.println(cartService.getCart());
        } else if (choice == 4) {
            if (cartService.isEmpty()) {
                System.out.println("Cart is empty!");
            } else {
                System.out.print("Confirm checkout (yes/no): ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    System.out.print("Enter your email again: ");
                    String email = sc.nextLine();
                    int orderId =
                        orderService.checkout(email, cartService.getCart());
                    cartService.clearCart();
                    System.out.println("Order placed! Order ID: " + orderId);
                }
            }
        } else if (choice == 5) {
    model.Review r = new model.Review();

    System.out.print("Product ID: ");
    r.setProductId(sc.nextInt());
    sc.nextLine();

    System.out.print("Your Email: ");
    r.setBuyerEmail(sc.nextLine());

    System.out.print("Rating (1-5): ");
    r.setRating(sc.nextInt());
    sc.nextLine();

    System.out.print("Comment: ");
    r.setComment(sc.nextLine());

    service.ReviewService rs = new service.ReviewService();
    boolean ok = rs.addReview(r);

    System.out.println(ok ? "Review submitted!" : "Review failed");
} else if (choice == 6) {
    return;
}
 else {
            System.out.println("Invalid option");
        }
    }
}


private static void sellerDashboard(Scanner sc) {
    ProductService productService = new ProductService();

    while (true) {
        System.out.println("\n=== SELLER DASHBOARD ===");
        System.out.println("1. Add Product");
        System.out.println("2. Logout");
        System.out.print("Choose: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            model.Product p = new model.Product();

            System.out.print("Product Name: ");
            p.setName(sc.nextLine());

            System.out.print("Description: ");
            p.setDescription(sc.nextLine());

            System.out.print("Price: ");
            p.setPrice(sc.nextDouble());

            System.out.print("MRP: ");
            p.setMrp(sc.nextDouble());
            sc.nextLine();

            System.out.print("Category: ");
            p.setCategory(sc.nextLine());

            System.out.print("Stock: ");
            p.setStock(sc.nextInt());
            sc.nextLine();

            System.out.print("Seller Email: ");
            p.setSellerEmail(sc.nextLine());

            boolean success = productService.addProduct(p);
            System.out.println(success ? "Product added!" : "Failed to add product");
        } else if (choice == 2) {
            return;
        } else {
            System.out.println("Invalid option");
        }
    }
}



}
