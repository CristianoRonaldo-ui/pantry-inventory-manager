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

    public int getQuantity(String name) {
        return stock.getOrDefault(name, 0);
    }
} 