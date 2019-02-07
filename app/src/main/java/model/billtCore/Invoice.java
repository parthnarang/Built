package model.billtCore;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


@Entity(tableName = "invoice",foreignKeys = @ForeignKey(entity = Merchant.class, parentColumns = "id",childColumns = "merchant_id", onDelete = ForeignKey.CASCADE),indices = {@Index(value = {"id"},
        unique = true)})
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

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id=1;

    public int getId() {
        return id;
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    @ColumnInfo(name = "inv_id")
    @SerializedName("transID")
    private String invId;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @ColumnInfo(name = "mid")
    @SerializedName("mid")
    private String mid;

    public int getMerchantId() {
        return merchantId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    @ColumnInfo(name = "merchant_id")
    private int merchantId;

    @SerializedName("merchantName")
    @ColumnInfo(name="merchant_name")
    String  DisplayName;

    @SerializedName("address")
    @ColumnInfo(name ="address")
    String  address;
   // @SerializedName("PHONE NO")
   // String  phoneNo;
    @SerializedName("gst")
    @ColumnInfo(name  ="gst")
    String  gstNo;

    @SerializedName("date")
    @ColumnInfo(name ="date")
    String  date;

    @SerializedName("time")
    @ColumnInfo(name ="time")
    String  time;

    public List<InvoiceItem> getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(List<InvoiceItem> invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    @Ignore
    List<InvoiceItem> invoiceItem;

    @SerializedName("totalAmt")
    @ColumnInfo(name ="total_amt")
    Double totalAmount;

    @SerializedName("vat")
    @ColumnInfo(name ="vat")
    Double vat;

    @SerializedName("net")
    @ColumnInfo(name ="net")
    Double netAmount;

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @ColumnInfo(name ="billtdate")
    @SerializedName("billt_date")
    String dateAndTime;


}
