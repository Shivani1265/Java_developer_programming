
import java.util.*;

class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class User {
    String username;
    String password;
    List<Product> cart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<>();
    }
}

public class ECommerce {
    static Map<String, User> users = new HashMap<>();
    static Map<String, Product> products = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize products
        products.put("product1", new Product("Product 1", 10.99));
        products.put("product2", new Product("Product 2", 9.99));
        products.put("product3", new Product("Product 3", 12.99));

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }

    static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("Login successful!");
            productMenu(scanner, username);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    static void productMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("1. View products");
            System.out.println("2. Add to cart");
            System.out.println("3. View cart");
            System.out.println("4. Checkout");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addToCart(scanner, username);
                    break;
                case 3:
                    viewCart(username);
                    break;
                case 4:
                    checkout(username);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void viewProducts() {
        for (Product product : products.values()) {
            System.out.println(product.name + ": $" + product.price);
        }
    }

    static void addToCart(Scanner scanner, String username) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        if (products.containsKey(productName)) {
            users.get(username).cart.add(products.get(productName));
            System.out.println("Added to cart!");
        } else {
            System.out.println("Product not found.");
        }
    }

    static void viewCart(String username) {
        System.out.println("Your cart:");
        for (Product product : users.get(username).cart) {
            System.out.println(product.name + ": $" + product.price);
        }
    }

    static void checkout(String username) {
        double total = 0;
        for (Product product : users.get(username).cart) {
            total += product.price;
        }
        System.out.println("Total: $" + total);
        users.get(username).cart.clear();
        System.out.println("Checkout successful!");
    }
}
