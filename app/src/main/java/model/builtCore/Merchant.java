package model.builtCore;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Merchant {

    public long getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public void setMid(long mid) {
        this.mid = mid;
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



    @SerializedName("merchantId")
    long mid;
    @SerializedName("merchant name")
    String merchantName;

    @SerializedName("merchantLogo")
    String merchantLogo;

    ArrayList<Invoice> invoices = new ArrayList<>();




}
