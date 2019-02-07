package database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import model.User;
import model.billtCore.Invoice;

@Dao
public interface InvoiceDao {

    @Query("SELECT * FROM invoice")
    List<Invoice> getAll();

    @Query("SELECT* from invoice where inv_id = :id")
    Invoice validate(String id);

    @Query("SELECT* from invoice where id = :id")
    Invoice getById(int id);

    @Query("SELECT* from invoice where mid = :mid")
    List<Invoice> getInvoiceFromMid(String mid);

    @Query("SELECT COUNT(*) from invoice")
    int countMerchants();

    @Insert
    void insertAll(Invoice... invoices);

    @Insert
    void insert(Invoice invoice);

    @Delete
    void delete(Invoice invoice);
}

