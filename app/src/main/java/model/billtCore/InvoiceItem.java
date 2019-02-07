package model.billtCore;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;



@Entity(tableName = "invoice_item",foreignKeys = @ForeignKey(entity = Invoice.class, parentColumns = "id",childColumns = "invoice_id", onDelete = ForeignKey.CASCADE))
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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getId() {
        return id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    @ColumnInfo(name = "invoice_id")
    private int invoice_id;



    @SerializedName("description")
    @ColumnInfo(name="description")
    String description;

    @ColumnInfo(name ="quantity")
    @SerializedName("QTY")
    int quantity;

    @ColumnInfo(name = "rate")
    @SerializedName("rate")
    double rate;

    @ColumnInfo(name ="amount")
    @SerializedName("amount")
    double amount;
}
