import java.io.*;

public class FileManager {

    // =========================
    // USER FILE METHODS
    // =========================

    public static void saveUser(User user) {

        try (PrintWriter writer =
                     new PrintWriter("users.txt")) {

            writer.println(
                    user.getUsername() + "," +
                            user.getPasswordHash()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error: Unable to save user data."
            );
        }
    }


    public static User loadUser() {

        File file = new File("users.txt");

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file)
                     )) {

            String line = reader.readLine();

            if (line == null ||
                    line.trim().isEmpty()) {

                return null;
            }

            String[] data = line.split(",");

            if (data.length < 2) {

                System.out.println(
                        "Warning: User data file is invalid."
                );

                return null;
            }

            String username = data[0];
            String passwordHash = data[1];

            return new User(
                    username,
                    passwordHash
            );

        } catch (IOException e) {

            System.out.println(
                    "Error: Unable to load user data."
            );

            return null;
        }
    }


    // =========================
    // ACCOUNT FILE METHODS
    // =========================

    public static void saveAccount(
            BankAccount account
    ) {

        try (PrintWriter writer =
                     new PrintWriter("accounts.txt")) {

            writer.println(
                    account.getAccountNumber()
                            + ","
                            + account.getBalance()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error: Unable to save account data."
            );
        }
    }


    public static BankAccount loadAccount() {

        File file =
                new File("accounts.txt");

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file)
                     )) {

            String line =
                    reader.readLine();

            if (line == null ||
                    line.trim().isEmpty()) {

                return null;
            }

            String[] data =
                    line.split(",");

            if (data.length < 2) {

                System.out.println(
                        "Warning: Account data file is invalid."
                );

                return null;
            }

            String accountNumber =
                    data[0];

            double balance =
                    Double.parseDouble(data[1]);

            if (balance < 0) {

                System.out.println(
                        "Warning: Invalid account balance."
                );

                return null;
            }

            BankAccount account =
                    new BankAccount(
                            accountNumber
                    );

            account.setBalance(balance);

            return account;

        } catch (IOException e) {

            System.out.println(
                    "Error: Unable to load account data."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: Account balance is invalid."
            );
        }

        return null;
    }


    // =========================
    // TRANSACTION FILE METHODS
    // =========================

    public static void saveTransaction(
            Transaction transaction
    ) {

        try (FileWriter writer =
                     new FileWriter(
                             "transactions.txt",
                             true
                     )) {

            writer.write(
                    transaction.toFileString()
            );

            writer.write(
                    System.lineSeparator()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error: Unable to save transaction."
            );
        }
    }
}