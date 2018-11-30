package userInteface.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import model.PosVendore.DataNote;
import model.PosVendore.DataNoteInformation;
import parth.com.buiit.R;
import userInteface.Adapters.InvoiceListAdapter;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class StoreInvoiceListActivity extends BaseDrawerActivity {

    private TextView mTextViewEmpty;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    private RecyclerView mRecyclerView;
    private InvoiceListAdapter mListadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store_invoice_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mProgressBarLoading = (ProgressBar)findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
       // mLayoutManager.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(layoutManager);
        ArrayList data = new ArrayList<DataNote>();
        for (int i = 0; i < DataNoteInformation.id.length; i++)
        {
            data.add(
                    new DataNote
                            (
                                    DataNoteInformation.textArray[i],
                                    DataNoteInformation.dateArray[i],
                                    DataNoteInformation.id[i]
                            ));
        }

        mListadapter = new InvoiceListAdapter(data,this);
        mRecyclerView.setAdapter(mListadapter);
    }
}
