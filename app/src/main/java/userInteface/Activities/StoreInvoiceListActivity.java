package userInteface.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import database.AppDatabase;
import database.repositories.InvoiceRepository;
import database.repositories.MerchantRepository;
import model.billtCore.Invoice;
import model.billtCore.Merchant;
import parth.com.buiit.R;
import userInteface.Adapters.InvoiceListAdapter;
import userInteface.Fragments.InvoiceFragment;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class StoreInvoiceListActivity extends BaseDrawerActivity {

    private String TAG = StoreInvoiceListActivity.class.getName();
    private TextView mTextViewEmpty;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    private RecyclerView mRecyclerView;
    private InvoiceListAdapter mListadapter;
    private AppDatabase appDatabase;
    private List<Invoice> invoiceList;
    private MerchantRepository merchantRepository;
    private InvoiceRepository invoiceRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store_invoice_list);
        merchantRepository = new MerchantRepository(this);
        invoiceRepository = new InvoiceRepository(this);
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id",0);
        Merchant merchant = merchantRepository.getById(id);
        invoiceList = invoiceRepository.getInvoiceFromMid(merchant.getMid());

        Log.d(TAG,"invoice list size= " + invoiceList.size() + " mid = " + merchant.getMid());

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mProgressBarLoading = (ProgressBar)findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
       // mLayoutManager.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(layoutManager);

        mListadapter = new InvoiceListAdapter(invoiceList,this);
        mRecyclerView.setAdapter(mListadapter);

    }
}
