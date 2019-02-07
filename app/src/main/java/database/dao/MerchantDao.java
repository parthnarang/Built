package database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import model.User;
import model.billtCore.Invoice;
import model.billtCore.Merchant;

@Dao
public interface MerchantDao {

    @Query("SELECT * FROM merchant")
    List<Merchant> getAll();

    @Query("SELECT COUNT(*) from merchant")
    int countMerchants();

    @Query("SELECT* from merchant where mid = :id")
    Merchant validate(String id);

    @Query("SELECT* from merchant where id = :id")
    Merchant getById(int id);

    @Insert
    void insertAll(Merchant... merchants);

    @Insert
    void insert(Merchant merchant);

    @Delete
    void delete(Merchant merchant);

    @Query("DELETE FROM merchant")
    void deleteAll();
}

