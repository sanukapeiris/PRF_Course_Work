import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String[] usernames = { "admin", "user" };
    private static String[] passwords = { "password1", "password2" };
    private static String loggedInUser = "";
    private static final int MAX_SUPPLIERS = 100;
    private static String[] supplierId = new String[MAX_SUPPLIERS];
    private static String[] supplierNames = new String[MAX_SUPPLIERS];
    private static int supplierCount = 0;

    private static final int MAX_ITEMS = 100;
    private static String[] itemIds = new String[MAX_ITEMS];
    private static String[] itemNames = new String[MAX_ITEMS];
    private static String[] itemCategories = new String[MAX_ITEMS];
    private static String[] itemSuppliers = new String[MAX_ITEMS];
    private static double[] itemPrices = new double[MAX_ITEMS];
    private static int itemCount = 0; // Initialize the item count to 0

    public static void main(String[] args) {
        login();
    }

    private static void login() {
        System.out.println("===== Stock Management System =====");
        System.out.println("Login to the system");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean isValidLogin = checkCredentials(username, password);

        if (isValidLogin) {
            loggedInUser = username;
            System.out.println("\nLogin successful. Welcome, " + username + "!");
            homePage();
        } else {
            System.out.println("\nInvalid username or password. Please try again.\n");
            login();
        }
    }

    private static void homePage() {
        System.out.println("\n===== Home Page =====");
        System.out.println("1. Change Password");
        System.out.println("2. Supplier Management");
        System.out.println("3. Stock Management");
        System.out.println("4. Exit");
        System.out.println("5. Log out");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                changePassword();
                break;
            case 2:
                supplierManagement();
                break;
            case 3:
                stockManagement();
                break;
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            case 5:
                loggedInUser = "";
                System.out.println("\nLogged out successfully.");
                login();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                homePage();
                break;
        }
    }

    private static void supplierManagement() {
        System.out.println("\n===== Supplier Management =====");
        System.out.println("1. Add Supplier");
        System.out.println("2. Update Supplier");
        System.out.println("3. Delete Supplier");
        System.out.println("4. View Suppliers");
        System.out.println("5. Search Supplier");
        System.out.println("6. Home Page");

        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addSupplier();
                break;
            case 2:
                updateSupplier();
                break;
            case 3:
                deleteSupplier();
                break;
            case 4:
                viewSuppliers();
                break;
            case 5:
                searchSupplier();
                break;
            case 6:
                homePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                supplierManagement();
                break;
        }
    }

    private static void addSupplier() {
        if (supplierCount == MAX_SUPPLIERS) {
            System.out.println("Cannot add more suppliers. Maximum limit reached.");
            supplierManagement();
            return;
        }

        System.out.println("\n===== Add Supplier =====");
        System.out.print("Enter supplier ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter supplier name: ");
        String name = scanner.nextLine();

        supplierId[supplierCount] = id;
        supplierNames[supplierCount] = name;
        supplierCount++;

        System.out.println("\nSupplier added successfully.");
        System.out.print("\nDo yu want to add another supplier (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            addSupplier();
        } else {
            supplierManagement();
        }
    }

    private static void updateSupplier() {
        System.out.println("\n===== Update Supplier =====");
        System.out.print("Enter supplier ID to update: ");
        String id = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < supplierCount; i++) {
            if (supplierId[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Supplier not found.");
            supplierManagement();
            return;
        }

        System.out.print("Enter new supplier name: ");
        String newName = scanner.nextLine();

        supplierNames[index] = newName;

        System.out.println("\nSupplier updated successfully.");
        System.out.print("\nDo you need to update supplyer? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            updateSupplier();
        } else {
            supplierManagement();
        }
    }

    private static void deleteSupplier() {
        System.out.println("\n===== Delete Supplier =====");
        System.out.print("Enter supplier ID to delete: ");
        String id = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < supplierCount; i++) {
            if (supplierId[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Supplier not found.");
            supplierManagement();
            return;
        }

        // Shift the elements to the left
        for (int i = index; i < supplierCount - 1; i++) {
            supplierId[i] = supplierId[i + 1];
            supplierNames[i] = supplierNames[i + 1];
        }

        supplierCount--;

        System.out.println("\nSupplier deleted successfully.");
        System.out.print("\nDo you want to delete a another supplier (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            deleteSupplier();
        } else {
            supplierManagement();
        }
    }

    private static void viewSuppliers() {
        System.out.println("\n===== View Suppliers =====");
        System.out.println("Supplier ID\tSupplier Name");

        for (int i = 0; i < supplierCount; i++) {
            System.out.println(supplierId[i] + "\t\t" + supplierNames[i]);
        }

        System.out.println();
        supplierManagement();
    }

    private static void searchSupplier() {
        System.out.println("\n===== Search Supplier =====");
        System.out.print("Enter supplier ID to search: ");
        String id = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < supplierCount; i++) {
            if (supplierId[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Supplier not found.");
        } else {
            System.out.println("Supplier ID\tSupplier Name");
            System.out.println(supplierId[index] + "\t\t" + supplierNames[index]);
        }

        System.out.print("\nDo you want to search suppler? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            searchSupplier();
        } else {
            supplierManagement();
        }
    }

    private static void stockManagement() {
        System.out.println("\n===== Stock Management =====");
        System.out.println("1. Manage Item Categories");
        System.out.println("2. Add Item");
        System.out.println("3. list  the items");
        System.out.println("4. View Items");
        System.out.println("5. list the item"); // sorted by using unit price in ascending order.
        System.out.println("6. Home Page");

        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                manageItemCategories() ;
                break;
            case 2:
                addItem();
                break;
            case 3:
                searchItem();
                break;
            case 4:
                viewItems();
                break;
            case 5:
                //listItem();
                break;
            case 6:
                homePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                stockManagement();
                break;
        }
    }
    private static void manageItemCategories() {
        System.out.println("\n===== Manage Item Categories =====");
        System.out.println("1. Add Category");
        System.out.println("2. Delete Category");
        System.out.println("3. Update Category");
        System.out.println("4. Home Page");

        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                addCategory();
                break;
            case 2:
                deleteCategory();
                break;
            case 3:
                updateCategory();
                break;
            case 4:
                stockManagement();
                break;
            default:
                System.out.println("\nInvalid choice. Please try again.\n");
                manageItemCategories();
                break;
        }
    }

    private static void addCategory() {
        System.out.println("\n===== Add Category =====");
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        // Add the category to the itemCategories array
        itemCategories[itemCount] = categoryName;
        System.out.println("\nCategory added successfully.");
        System.out.print("\nDo you want to add another category? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            addCategory();
        } else {
            manageItemCategories();
        }
    }

    private static void deleteCategory() {
        System.out.println("\n===== Delete Category =====");
        System.out.print("Enter category name to delete: ");
        String categoryName = scanner.nextLine();

        boolean categoryFound = false;
        for (int i = 0; i < itemCount; i++) {
            if (itemCategories[i].equals(categoryName)) {
                // Delete the category by shifting the elements to the left
                for (int j = i; j < itemCount - 1; j++) {
                    itemCategories[j] = itemCategories[j + 1];
                }
                itemCount--;
                categoryFound = true;
                break;
            }
        }

        if (categoryFound) {
            System.out.println("\nCategory deleted successfully.");
        } else {
            System.out.println("\nCategory not found.");
        }
        System.out.print("\nDo you want to delete anothe category? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            deleteCategory();
        } else {
            manageItemCategories();
        }
    }

    private static void updateCategory() {
        System.out.println("\n===== Update Category =====");
        System.out.print("Enter category name to update: ");
        String categoryName = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < itemCount; i++) {
            if (itemCategories[i].equals(categoryName)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("\nCategory not found.");
        } else {
            System.out.print("Enter new category name: ");
            String newCategoryName = scanner.nextLine();
            itemCategories[index] = newCategoryName;
            System.out.println("\nCategory updated successfully.");
        }
        System.out.print("\nDo you want ot update a catagory (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            updateCategory();
        } else {
            manageItemCategories();
        }
    }
    private static void addItem() {
        System.out.println("\n===== Add Item =====");
        if (itemCount < MAX_ITEMS) {
            System.out.print("Enter item ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();
            System.out.print("Enter item category: ");
            String category = scanner.nextLine();
            System.out.print("Enter item supplier: ");
            String supplier = scanner.nextLine();
            System.out.print("Enter item price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            itemIds[itemCount] = id;
            itemNames[itemCount] = name;
            itemCategories[itemCount] = category;
            itemSuppliers[itemCount] = supplier;
            itemPrices[itemCount] = price;
            itemCount++;

            System.out.println("\nItem added successfully.");
        } else {
            System.out.println("\nMaximum number of items reached. Cannot add more items.");
        }

        System.out.print("\n do you want to add another item (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            addItem();
        } else {
            stockManagement();
        }
    }

    private static void viewItems() {
        System.out.println("\n===== View Items =====");
        System.out.println("Item ID\tItem Name\tItem Category\tItem Supplier\tItem Price");

        for (int i = 0; i < itemCount; i++) {
            System.out.println(
                    itemIds[i] + "\t" + itemNames[i] + "\t" + itemCategories[i] + "\t" + itemSuppliers[i] + "\t"
                            + itemPrices[i]);
        }

        System.out.println();
        stockManagement();
    }

    private static void searchItem() {
        System.out.println("\n===== Search Item =====");
        System.out.print("Enter item ID to search: ");
        String id = scanner.nextLine();

        int index = -1;
        for (int i = 0; i < itemCount; i++) {
            if (itemIds[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Item not found.");
        } else {
            System.out.println("Item ID\tItem Name\tItem Category\tItem Supplier\tItem Price");
            System.out.println(
                    itemIds[index] + "\t" + itemNames[index] + "\t" + itemCategories[index] + "\t" + itemSuppliers[index]
                            + "\t" + itemPrices[index]);
        }

        System.out.print("\ndo you wat to search another item (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            searchItem();
        } else {
            stockManagement();
        }
    }

    private static void listItems() {

        stockManagement();
    }



    private static boolean checkCredentials(String username, String password) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username) && passwords[i].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static void changePassword() {
        System.out.println("\n===== Change Password =====");
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (!currentPassword.equals(passwords[getIndex(loggedInUser)])) {
            System.out.println("Incorrect password. Please try again.");
            changePassword();
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        passwords[getIndex(loggedInUser)] = newPassword;

        System.out.println("\nPassword changed successfully.");
        homePage();
    }

    private static int getIndex(String username) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username)) {
                return i;
            }
        }
        return -1;
    }
}


