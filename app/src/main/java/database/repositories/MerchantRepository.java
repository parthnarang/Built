package database.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import database.AppDatabase;
import database.dao.MerchantDao;
import model.billtCore.Invoice;
import model.billtCore.Merchant;

public class MerchantRepository {

        private MerchantDao merchantDao;
        private List<Merchant> merchantList;

     public MerchantRepository(Context application) {
            AppDatabase db = AppDatabase.getAppDatabase(application);
            merchantDao = db.merchantDao();
          //  mer = mWordDao.getAllWords();
        }

        public List<Merchant> getMerchantList() {
            return merchantDao.getAll();
        }

    public Merchant validateInvoice(String id){ return merchantDao.validate(id); }

    public Merchant getById(int id){ return merchantDao.getById(id);}

        public void insert (Merchant merchant) {
            new insertAsyncTask(merchantDao).execute(merchant);
        }

        private static class insertAsyncTask extends AsyncTask<Merchant, Void, Void> {

            private MerchantDao mAsyncTaskDao;

            insertAsyncTask(MerchantDao dao) {
                mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Merchant... params) {
                mAsyncTaskDao.insert(params[0]);
                return null;
            }
        }


    }

