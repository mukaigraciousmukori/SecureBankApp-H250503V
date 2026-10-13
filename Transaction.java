import java.time.LocalDateTime;

public class Transaction {

    private String accountNumber;
    private String type;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(
            String accountNumber,
            String type,
            double amount
    ) {

        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String toFileString() {

        return accountNumber + "," +
                type + "," +
                amount + "," +
                dateTime;
    }
}