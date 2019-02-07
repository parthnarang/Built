package database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import database.dao.InvoiceDao;
import database.dao.InvoiceItemDao;
import database.dao.MerchantDao;
import model.billtCore.Invoice;
import model.billtCore.InvoiceItem;
import model.billtCore.Merchant;

@Database(entities = {Merchant.class, Invoice.class, InvoiceItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract MerchantDao merchantDao();
    public abstract InvoiceDao invoiceDao();
    public abstract InvoiceItemDao invoiceItemDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
