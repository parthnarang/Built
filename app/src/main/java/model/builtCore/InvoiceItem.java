package model.builtCore;

import com.google.gson.annotations.SerializedName;

public class InvoiceItem {
    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @SerializedName("DESCRIPTION")
    String description;
    @SerializedName("QTY")
    int quantity;
    @SerializedName("RATE")
    double rate;
    @SerializedName("AMOUNT")
    double amount;
}
