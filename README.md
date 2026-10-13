# Secure Banking Application

## Description

The Secure Banking Application is a console-based Java application developed to demonstrate Object-Oriented Programming principles and secure coding practices.

The application allows an authenticated user to create a bank account, view the account balance, deposit funds and withdraw funds. User credentials, account information and transaction history are stored in text files for data persistence.

## Student Details

**Name:** Mukai Mukori

**Registration Number:** H250503V

## Features

- User authentication
- Password hashing using SHA-256
- Bank account creation
- Account balance viewing
- Deposit functionality
- Withdrawal functionality
- Prevention of withdrawals greater than the available balance
- Validation of transaction amounts
- Safe handling of invalid numeric input
- Transaction history recording
- Persistent storage using text files
- Transactions linked to account numbers
- Exception handling for file operations
- Object-Oriented Programming principles

## Test Login Credentials

Username:    admin

Password: 1234

The application is submitted without a bank account already created.

After logging in, select:

1. Create Bank Account

to begin testing the application.

## Main Menu

After successful login, the following options are available:

1. Create Bank Account
2. View Balance
3. Deposit
4. Withdraw
5. Exit

## Data Files

The application uses the following text files:

### users.txt

Stores the username and hashed password.

### accounts.txt

Stores the account number and current account balance.

### transactions.txt

Stores transaction records including:

- Account number
- Transaction type
- Amount
- Date and time

## Security Features

The application demonstrates several secure coding practices.

### Password Hashing

Passwords are not stored directly in plain text. SHA-256 hashing is used before storing and comparing passwords.

### Input Validation

The application validates user input and prevents invalid numeric values from crashing the application.

### Transaction Validation

Deposits must be greater than zero.

Withdrawals must be greater than zero and cannot exceed the available account balance.

### Encapsulation

Sensitive data such as usernames, password hashes, account numbers and balances are stored in private class fields.

### Exception Handling

File operations use exception handling to prevent unexpected file errors from terminating the program.

## Project Structure

The project contains the following Java classes:

### Main.java

Controls application execution, console interaction and menu navigation.

### User.java

Represents an authenticated application user.

### BankAccount.java

Represents a bank account and contains deposit and withdrawal business rules.

### Transaction.java

Represents a banking transaction.

### AuthenticationService.java

Handles user authentication.

### PasswordUtil.java

Handles password hashing.

### FileManager.java

Handles reading from and writing to text files.

## Technologies Used

- Java
- JDK 21
- IntelliJ IDEA
- Text file storage
- Git
- GitHub

## How to Run the Application

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Ensure Java JDK 21 or a compatible version is installed.
4. Run `src/Main.java`.
5. Login using the test credentials.
6. Create a bank account.
7. Use the menu to test deposits, withdrawals and balance enquiries.

## Limitations

- The current version supports one application user.
- The current version supports one bank account.
- Data is stored in text files rather than a database.
- The application uses a console-based interface.

## Future Improvements

Possible future improvements include:

- Multiple users and bank accounts
- User registration
- Database integration
- Graphical user interface
- Stronger password hashing such as PBKDF2 or bcrypt
- Account-to-account transfers
- Detailed transaction viewing