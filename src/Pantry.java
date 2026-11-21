import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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

    public void printInventory() {
        System.out.println("=== Pantry Inventory ===");
        if (stock.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public ArrayList<String> getLowStockItems(int threshold) {
        ArrayList<String> lowItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() <= threshold) {
                lowItems.add(entry.getKey());
            }
        }
        return lowItems;
    }
}