package model.classes;

public class DeliveryTransaction {
    private Transaction transaction;
    private DeliveryDetails details;

    public DeliveryTransaction() {

    }

    public DeliveryTransaction(Transaction transaction, DeliveryDetails details) {
        this.transaction = transaction;
        this.details = details;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public DeliveryDetails getDetails() {
        return details;
    }

    public void setDetails(DeliveryDetails details) {
        this.details = details;
    }
}
