package model.builtCore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    public String getDisplayName() {
        return DisplayName;
    }

    public String getAddress() {
        return address;
    }

    public String getGstNo() {
        return gstNo;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getVat() {
        return vat;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    @SerializedName("merchant name")
    String  DisplayName;
    @SerializedName("ADDRESS")
    String  address;
   // @SerializedName("PHONE NO")
   // String  phoneNo;
    @SerializedName("GST NO")
    String  gstNo;
    @SerializedName("DATE")
    String  date;
    @SerializedName("Time")
    String  time;

    public List<InvoiceItem> getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    List<InvoiceItem> invoiceItem;
    @SerializedName("TOTAL AMT")
    Double totalAmount;
    @SerializedName("ADDED VAT")
    Double vat;
    @SerializedName("Net")
    Double netAmount;

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    Date dateAndTime;


}
