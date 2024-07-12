//package atm_machine;
//
//import java.util.*;
//
//class ATM {
//    private float balance;
//    private int pin = 1234;
//    private List<String> transactionHistory = new ArrayList<>();
//    private float dailyWithdrawalLimit = 5000;
//    private float dailyWithdrawnAmount = 0;
//    private Scanner sc = new Scanner(System.in);
//
//    public void checkPin() {
//        System.out.println("Enter your PIN: ");
//        int enteredPin = sc.nextInt();
//        if (enteredPin == pin) {
//            menu();
//        } else {
//            System.out.println("Invalid PIN. Try again.");
//            checkPin();
//        }
//    }
//
//    public void menu() {
//        System.out.println("Enter Your Choice: ");
//        System.out.println("1. Check A/C Balance");
//        System.out.println("2. Deposit Money");
//        System.out.println("3. Withdraw Money");
//        System.out.println("4. Reset PIN Number");
//        System.out.println("5. View Transaction History");
//        System.out.println("6. EXIT");
//
//        int option = sc.nextInt();
//
//        switch (option) {
//            case 1:
//                checkBalance();
//                break;
//            case 2:
//                depositMoney();
//                break;
//            case 3:
//                withdrawMoney();
//                break;
//            case 4:
//                resetPin();
//                break;
//            case 5:
//                viewTransactionHistory();
//                break;
//            case 6:
//                System.out.println("Thank you for using the ATM.");
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//                menu();
//                break;
//        }
//    }
//
//    public void checkBalance() {
//        System.out.println("Balance: " + balance);
//        if (balance < 1000) {
//            System.out.println("Warning: Your balance is below $1000.");
//        }
//        menu();
//    }
//
//    public void withdrawMoney() {
//        System.out.println("Enter amount to withdraw: ");
//        float amount = sc.nextFloat();
//        if (amount > balance) {
//            System.out.println("Insufficient Balance");
//        } else if (dailyWithdrawnAmount + amount > dailyWithdrawalLimit) {
//            System.out.println("Exceeded daily withdrawal limit. You can withdraw up to " + (dailyWithdrawalLimit - dailyWithdrawnAmount) + " more today.");
//        } else {
//            balance -= amount;
//            dailyWithdrawnAmount += amount;
//            transactionHistory.add("Withdrawn: $" + amount);
//            System.out.println("Money Withdrawn Successfully");
//        }
//        menu();
//    }
//
//    public void depositMoney() {
//        System.out.println("Enter the Amount: ");
//        float amount = sc.nextFloat();
//        balance += amount;
//        transactionHistory.add("Deposited: $" + amount);
//        System.out.println("Money Deposited Successfully");
//        menu();
//    }
//
//    public void resetPin() {
//        System.out.println("Enter new PIN: ");
//        int newPin = sc.nextInt();
//        System.out.println("Confirm new PIN: ");
//        int confirmPin = sc.nextInt();
//        if (newPin == confirmPin) {
//            pin = newPin;
//            System.out.println("PIN Reset Successful. Please enter your new PIN to continue.");
//        } else {
//            System.out.println("PINs do not match. Please try again.");
//        }
//        checkPin();
//    }
//
//    public void viewTransactionHistory() {
//        System.out.println("Transaction History:");
//        for (String transaction : transactionHistory) {
//            System.out.println(transaction);
//        }
//        menu();
//    }
//}
//
//public class ATM_Machine {
//    public static void main(String[] args) {
//        ATM atm = new ATM();
//        atm.checkPin();
//    }
//}

package atm_machine;

import java.util.*;
import java.time.LocalDateTime;

class ATM {
    // Instance variables for the ATM class
    private float balance;  // Current account balance
    private int pin;  // Account PIN
    private String accountNumber;  // Account number
    private String accountHolderName;  // Account holder's name
    private float dailyWithdrawalLimit = 5000;  // Daily withdrawal limit
    private float dailyWithdrawnAmount = 0;  // Amount withdrawn today
    private List<String> transactionHistory = new ArrayList<>();  // List to store transaction history
    private Scanner sc = new Scanner(System.in);  // Scanner for user input

    // HashMap to store multiple users' account information
    private static final HashMap<String, ATM> users = new HashMap<>();

    // Constructor to initialize an ATM object
    public ATM(String accountNumber, String accountHolderName, int pin, float balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = balance;
    }

    // Method to initialize users with predefined data
    public static void initializeUsers() {
        users.put("A21126511040", new ATM("A21126511040", "John Doe", 2002, 5000));
        users.put("A21126511046", new ATM("A21126511046", "Jane Doe", 2003, 5000));
        users.put("A21126511016", new ATM("A21126511016", "Alice Smith", 2000, 5000));
        users.put("A21126511007", new ATM("A21126511007", "Bob Johnson", 2004, 5000));
        users.put("A21126511041", new ATM("A21126511041", "Charlie Brown", 2003, 5000));
    }

    // Method to check the user's PIN and grant access to the ATM
    public void checkPin() {
        System.out.println("Enter your Account Number: ");
        String enteredAccountNumber = sc.next();
        System.out.println("Enter your PIN: ");
        int enteredPin = sc.nextInt();

        // Validate account number and PIN
        if (users.containsKey(enteredAccountNumber) && users.get(enteredAccountNumber).pin == enteredPin) {
            ATM user = users.get(enteredAccountNumber);
            user.menu();  // Show the menu if validation is successful
        } else {
            System.out.println("Invalid Account Number or PIN. Try again.");
            checkPin();  // Retry if validation fails
        }
    }

    // Method to display the main menu and handle user choices
    public void menu() {
        System.out.println("Welcome, " + accountHolderName);
        System.out.println("Enter Your Choice: ");
        System.out.println("1. Check A/C Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Reset PIN Number");
        System.out.println("5. View Transaction History");
        System.out.println("6. EXIT");

        int option = sc.nextInt();

        // Handle user's menu choice
        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                resetPin();
                break;
            case 5:
                viewTransactionHistory();
                break;
            case 6:
                System.out.println("Thank you for using the ATM.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                menu();
                break;
        }
    }

    // Method to check and display the current account balance
    public void checkBalance() {
        System.out.println("Balance: " + balance);
        if (balance < 1000) {
            System.out.println("Warning: Your balance is below $1000.");
        }
        menu();  // Return to the menu after displaying the balance
    }

    // Method to handle money withdrawal
    public void withdrawMoney() {
        System.out.println("Enter amount to withdraw: ");
        float amount = sc.nextFloat();
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else if (dailyWithdrawnAmount + amount > dailyWithdrawalLimit) {
            System.out.println("Exceeded daily withdrawal limit. You can withdraw up to " + (dailyWithdrawalLimit - dailyWithdrawnAmount) + " more today.");
        } else {
            balance -= amount;
            dailyWithdrawnAmount += amount;
            transactionHistory.add("Withdrawn: $" + amount + " at " + LocalDateTime.now());
            System.out.println("Money Withdrawn Successfully");
        }
        menu();  // Return to the menu after the transaction
    }

    // Method to handle money deposit
    public void depositMoney() {
        System.out.println("Enter the Amount: ");
        float amount = sc.nextFloat();
        balance += amount;
        transactionHistory.add("Deposited: $" + amount + " at " + LocalDateTime.now());
        System.out.println("Money Deposited Successfully");
        menu();  // Return to the menu after the transaction
    }

    // Method to handle PIN reset
    public void resetPin() {
        System.out.println("Enter new PIN: ");
        int newPin = sc.nextInt();
        System.out.println("Confirm new PIN: ");
        int confirmPin = sc.nextInt();
        if (newPin == confirmPin) {
            pin = newPin;
            System.out.println("PIN Reset Successful. Please enter your new PIN to continue.");
        } else {
            System.out.println("PINs do not match. Please try again.");
        }
        checkPin();  // Re-check PIN after resetting
    }

    // Method to display transaction history
    public void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        menu();  // Return to the menu after displaying the history
    }
}

public class ATM_Machine {
    public static void main(String[] args) {
        // Initialize the ATM users
        ATM.initializeUsers();
        
        // Create a temporary ATM object to access checkPin method
        ATM atm = new ATM(null, null, 0, 0);
        atm.checkPin();  // Start the ATM process by checking the PIN
    }
}

