import java.util.HashMap;

public class Pantry {
    // key = item name, value = quantity in stock
    private HashMap<String, Integer> stock;

    public Pantry() {
        this.stock = new HashMap<>();
    }

    public void addItem(String name, int quantity) {
        int current = stock.getOrDefault(name, 0);
        stock.put(name, current + quantity);
    }

    public void useItem(String name, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        int current = stock.getOrDefault(name, 0);
        if (quantity > current) {
            throw new IllegalArgumentException(
                    "Not enough " + name + ": have " + current + ", need " + quantity);
        }

        int remaining = current - quantity;
        if (remaining == 0) {
            stock.remove(name);
        } else {
            stock.put(name, remaining);
        }
    }

    public int getQuantity(String name) {
        return stock.getOrDefault(name, 0);
    }
}