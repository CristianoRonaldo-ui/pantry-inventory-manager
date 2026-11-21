public class PantryApp {
    public static void main(String[] args) {
        Pantry pantry = new Pantry();

        pantry.addItem("rice", 5);
        pantry.addItem("eggs", 12);
        pantry.addItem("rice", 3);

        System.out.println("rice: " + pantry.getQuantity("rice"));
        System.out.println("eggs: " + pantry.getQuantity("eggs"));
        System.out.println("milk: " + pantry.getQuantity("milk"));

        pantry.useItem("eggs", 4);
        System.out.println("eggs after using 4: " + pantry.getQuantity("eggs"));

        try {
            pantry.useItem("rice", 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        pantry.printInventory();

        pantry.addItem("salt", 1);
        System.out.println("Low stock (<= 3): " + pantry.getLowStockItems(3));

        runTests();

    }

    public static void runTests() {
        System.out.println();
        System.out.println("=== Running tests ===");

        // Test 1: adding the same item twice should accumulate
        Pantry p1 = new Pantry();
        p1.addItem("apple", 2);
        p1.addItem("apple", 3);
        if (p1.getQuantity("apple") == 5) {
            System.out.println("Test 1 PASS: addItem accumulates quantity");
        } else {
            System.out.println("Test 1 FAIL: expected 5, got " + p1.getQuantity("apple"));
        }

        // Test 2: using all of an item should remove it from stock
        Pantry p2 = new Pantry();
        p2.addItem("butter", 2);
        p2.useItem("butter", 2);
        if (p2.getLowStockItems(0).isEmpty()) {
            System.out.println("Test 2 PASS: item removed when quantity reaches 0");
        } else {
            System.out.println("Test 2 FAIL: butter still in stock map");
        }

        // Test 3: ussing more than available should throw exception
        Pantry p3 = new Pantry();
        p3.addItem("flour", 3);
        boolean threw = false;
        try {
            p3.useItem("flour", 5);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        if (threw && p3.getQuantity("flour") == 3) {
            System.out.println("Test 3 PASS: exception thrown and stock unchanged");
        } else {
            System.out.println("Test 3 FAIL: threw=" + threw + ", flour=" + p3.getQuantity("flour"));
        }
    }
}