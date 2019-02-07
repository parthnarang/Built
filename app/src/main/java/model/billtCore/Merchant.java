package model.billtCore;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


@Entity(tableName = "merchant",indices = {@Index(value = {"id"},
        unique = true)})
public class Merchant {

    public Merchant(String mid, String merchantName, String merchantLogo,String vid) {
        //this.id =id;
        this.mid = mid;
        this.vid = vid;
        this.merchantName = merchantName;
        this.merchantLogo = merchantLogo;
    }

    public String getMid() {
        return mid;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public String setMid(String mid) {
        this.mid = mid;
        return mid;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addNewInvoice(Invoice invoice){
        invoices.add(invoice);
    }

    public int getId() {
        return id;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id=1;

    public void setId(int id) {
        this.id = id;
    }


    @ColumnInfo(name = "mid")
    @SerializedName("mid")
    String mid;

    @ColumnInfo(name = "vid")
    @SerializedName("vid")
    public String vid;

    @ColumnInfo(name = "merchant_name")
    @SerializedName("merchant name")
    String merchantName;

    @ColumnInfo(name ="merchant_logo")
    @SerializedName("merchantLogo")
    String merchantLogo;

    @Ignore
    ArrayList<Invoice> invoices = new ArrayList<>();

}
