public class PantryApp {
    public static void main(String[] args) {
        Pantry pantry = new Pantry();

        pantry.addItem("rice", 5);
        pantry.addItem("eggs", 12);
        pantry.addItem("rice", 3);

        System.out.println("rice: " + pantry.getQuantity("rice"));
        System.out.println("eggs: " + pantry.getQuantity("eggs"));
        System.out.println("milk: " + pantry.getQuantity("milk"));
    }
}