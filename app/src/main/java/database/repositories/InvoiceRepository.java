package database.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import database.AppDatabase;
import database.dao.InvoiceDao;
import database.dao.InvoiceDao;
import model.billtCore.Invoice;
import model.billtCore.Merchant;

public class InvoiceRepository {


    private InvoiceDao invoiceDao;
    private List<Invoice> invoiceList;

    public InvoiceRepository(Context application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
         invoiceDao = db.invoiceDao();
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAll();
    }

    public List<Invoice> getInvoiceFromMid(String mid){ return invoiceDao.getInvoiceFromMid(mid);}

    public Invoice validateInvoice(String id){ return invoiceDao.validate(id); }

    public Invoice getById(int id){ return invoiceDao.getById(id); }

    public void insert (Invoice invoice) {
        new insertAsyncTask(invoiceDao).execute(invoice);
    }

    private static class insertAsyncTask extends AsyncTask<Invoice, Void, Void> {

        private InvoiceDao mAsyncTaskDao;

        insertAsyncTask(InvoiceDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Invoice... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}