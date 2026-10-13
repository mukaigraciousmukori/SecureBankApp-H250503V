import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        // =========================
        // LOAD DEFAULT USER
        // =========================

        User user =
                FileManager.loadUser();

        if (user == null) {

            user = new User(
                    "admin",
                    PasswordUtil.hashPassword(
                            "1234"
                    )
            );

            FileManager.saveUser(user);
        }


        // =========================
        // LOGIN
        // =========================

        AuthenticationService auth =
                new AuthenticationService(
                        user
                );

        System.out.println(
                "=== SECURE BANK APP ==="
        );

        System.out.print(
                "Username: "
        );

        String username =
                scanner.nextLine().trim();

        System.out.print(
                "Password: "
        );

        String password =
                scanner.nextLine();


        if (!auth.login(
                username,
                password
        )) {

            System.out.println(
                    "Login failed. Invalid username or password."
            );

            scanner.close();
            return;
        }


        System.out.println(
                "Login successful!"
        );


        // =========================
        // LOAD ACCOUNT
        // =========================

        BankAccount account =
                FileManager.loadAccount();


        // =========================
        // MAIN MENU
        // =========================

        int choice;

        do {

            System.out.println(
                    "\n=== MAIN MENU ==="
            );

            System.out.println(
                    "1. Create Bank Account"
            );

            System.out.println(
                    "2. View Balance"
            );

            System.out.println(
                    "3. Deposit"
            );

            System.out.println(
                    "4. Withdraw"
            );

            System.out.println(
                    "5. Exit"
            );

            choice =
                    readInteger(
                            scanner,
                            "Choose an option: "
                    );


            switch (choice) {

                // =========================
                // CREATE ACCOUNT
                // =========================

                case 1:

                    if (account != null) {

                        System.out.println(
                                "You already have a bank account."
                        );

                        break;
                    }

                    System.out.print(
                            "Enter account number: "
                    );

                    String accountNumber =
                            scanner.nextLine().trim();


                    if (accountNumber.isEmpty()) {

                        System.out.println(
                                "Account number cannot be empty."
                        );

                        break;
                    }


                    if (!accountNumber.matches(
                            "[A-Za-z0-9]+"
                    )) {

                        System.out.println(
                                "Account number must contain only letters and numbers."
                        );

                        break;
                    }


                    account =
                            new BankAccount(
                                    accountNumber
                            );

                    FileManager.saveAccount(
                            account
                    );

                    System.out.println(
                            "Bank account created successfully."
                    );

                    break;


                // =========================
                // VIEW BALANCE
                // =========================

                case 2:

                    if (account == null) {

                        System.out.println(
                                "Please create a bank account first."
                        );

                        break;
                    }

                    System.out.println(
                            "Account Number: "
                                    + account.getAccountNumber()
                    );

                    System.out.printf(
                            "Balance: %.2f%n",
                            account.getBalance()
                    );

                    break;


                // =========================
                // DEPOSIT
                // =========================

                case 3:

                    if (account == null) {

                        System.out.println(
                                "Please create a bank account first."
                        );

                        break;
                    }


                    double depositAmount =
                            readPositiveAmount(
                                    scanner,
                                    "Enter deposit amount: "
                            );


                    if (account.deposit(
                            depositAmount
                    )) {

                        FileManager.saveAccount(
                                account
                        );

                        FileManager.saveTransaction(
                                new Transaction(
                                        account.getAccountNumber(),
                                        "DEPOSIT",
                                        depositAmount
                                )
                        );

                        System.out.println(
                                "Deposit successful."
                        );

                        System.out.printf(
                                "New balance: %.2f%n",
                                account.getBalance()
                        );

                    } else {

                        System.out.println(
                                "Deposit failed."
                        );
                    }

                    break;


                // =========================
                // WITHDRAW
                // =========================

                case 4:

                    if (account == null) {

                        System.out.println(
                                "Please create a bank account first."
                        );

                        break;
                    }


                    double withdrawalAmount =
                            readPositiveAmount(
                                    scanner,
                                    "Enter withdrawal amount: "
                            );


                    if (withdrawalAmount >
                            account.getBalance()) {

                        System.out.println(
                                "Withdrawal failed. Insufficient funds."
                        );

                        break;
                    }


                    if (account.withdraw(
                            withdrawalAmount
                    )) {

                        FileManager.saveAccount(
                                account
                        );

                        FileManager.saveTransaction(
                                new Transaction(
                                        account.getAccountNumber(),
                                        "WITHDRAWAL",
                                        withdrawalAmount
                                )
                        );

                        System.out.println(
                                "Withdrawal successful."
                        );

                        System.out.printf(
                                "New balance: %.2f%n",
                                account.getBalance()
                        );

                    } else {

                        System.out.println(
                                "Withdrawal failed."
                        );
                    }

                    break;


                // =========================
                // EXIT
                // =========================

                case 5:

                    System.out.println(
                            "Logged out successfully."
                    );

                    break;


                default:

                    System.out.println(
                            "Invalid option. Please choose between 1 and 5."
                    );
            }

        } while (choice != 5);


        scanner.close();
    }


    // =========================
    // SAFE INTEGER INPUT
    // =========================

    private static int readInteger(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(
                    message
            );

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(
                        input
                );

            } catch (
                    NumberFormatException e
            ) {

                System.out.println(
                        "Invalid input. Please enter a whole number."
                );
            }
        }
    }


    // =========================
    // SAFE MONEY INPUT
    // =========================

    private static double readPositiveAmount(
            Scanner scanner,
            String message
    ) {

        while (true) {

            System.out.print(
                    message
            );

            String input =
                    scanner.nextLine().trim();

            try {

                double amount =
                        Double.parseDouble(
                                input
                        );


                if (!Double.isFinite(amount)) {

                    System.out.println(
                            "Invalid amount."
                    );

                    continue;
                }


                if (amount <= 0) {

                    System.out.println(
                            "Amount must be greater than zero."
                    );

                    continue;
                }


                return amount;

            } catch (
                    NumberFormatException e
            ) {

                System.out.println(
                        "Invalid amount. Please enter a numeric value."
                );
            }
        }
    }
}