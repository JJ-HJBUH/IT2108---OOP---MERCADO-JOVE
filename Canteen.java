import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu items and prices
        String[] menu = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] price = {80.00, 120.00, 100.00, 70.00, 90.00};

        // Summary of Orders
        int totalQuantity = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        // Menu
        System.out.println("===== M E N U =====");
        for (int i = 0; i < menu.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", (i + 1), menu[i], price[i]);
        }
        System.out.println();

        char orderAgain;

        // Order Code
        do {
            System.out.print("Enter item number: ");
            int itemNum = scanner.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            // Validate order inputs
            if (itemNum < 1 || itemNum > menu.length || quantity < 1 || quantity > 10) {
                System.out.println("\nInvalid order! Please enter a valid item and quantity.\n");
                
                // Prompt to order again when an invalid entry is encountered
                System.out.print("Do you want to order again? (Y/N): ");
                orderAgain = scanner.next().toUpperCase().charAt(0);
                System.out.println();
                continue; // Skip the remaining processing for this loop iteration
            }

            // Checking for discounts
            System.out.print("Are you a student? (Y/N): ");
            char isStudentChar = scanner.next().toUpperCase().charAt(0);
            boolean isStudent = (isStudentChar == 'Y');

            // Order calculations
            double itemPrice = price[itemNum - 1];
            double subtotal = itemPrice * quantity;

            // Determine discount percentage based on requirements
            double discountRate = 0.0;
            if (isStudent && subtotal >= 500) {
                discountRate = 0.15; // 15% discount for student with order >= $500
            } else if (isStudent) {
                discountRate = 0.10; // 10% discount for student
            } else if (subtotal >= 500) {
                discountRate = 0.05; // 5% discount for order >= $500
            }

            double discountAmount = subtotal * discountRate;
            double orderTotal = subtotal - discountAmount;

            // Grand total
            totalQuantity += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discountAmount;

            // Display current order details
            System.out.printf("%nSubtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discountAmount);
            System.out.printf("Order total: $%.2f%n%n", orderTotal);

            // Prompt to order again
            System.out.print("Do you want to order again? (Y/N): ");
            orderAgain = scanner.next().toUpperCase().charAt(0);
            System.out.println();

        } while (orderAgain == 'Y');

        // Display Final Summary
        double finalAmount = totalBeforeDiscount - totalDiscount;
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalQuantity);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        scanner.close();
    }
}
