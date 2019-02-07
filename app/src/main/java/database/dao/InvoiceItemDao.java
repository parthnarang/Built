package database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import model.billtCore.Invoice;
import model.billtCore.InvoiceItem;

@Dao
public interface InvoiceItemDao {

    @Query("SELECT * FROM invoice_item")
    List<InvoiceItem> getAll();

    @Query("SELECT * FROM invoice_item where invoice_id =:id")
    List<InvoiceItem> getInvoiceItemFromInvoiceId(long id);

    @Insert
    void insertAll(InvoiceItem... invoiceItems);

    @Insert
    void insert(InvoiceItem invoiceItem);

    @Delete
    void delete(InvoiceItem invoiceItem);
}
