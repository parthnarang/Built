package database.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import database.AppDatabase;
import database.dao.InvoiceDao;
import database.dao.InvoiceDao;
import database.dao.InvoiceItemDao;
import model.billtCore.Invoice;
import model.billtCore.InvoiceItem;
import model.billtCore.Merchant;

public class InvoiceItemRepository {


    private InvoiceItemDao invoiceItemDao;
    private List<InvoiceItem> invoiceItems;

    public InvoiceItemRepository(Context application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        invoiceItemDao = db.invoiceItemDao();
    }

    public List<InvoiceItem> getAllInvoices() {
        return invoiceItemDao.getAll();
    }

    public List<InvoiceItem> getInvoiceItemFromInvoiceId(long id){ return invoiceItemDao.getInvoiceItemFromInvoiceId(id);}


    public void insert (InvoiceItem invoiceItem) {
        new insertAsyncTask(invoiceItemDao).execute(invoiceItem);
    }

    private static class insertAsyncTask extends AsyncTask<InvoiceItem, Void, Void> {

        private InvoiceItemDao mAsyncTaskDao;

        insertAsyncTask(InvoiceItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final InvoiceItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}