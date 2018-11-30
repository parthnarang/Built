package userInteface.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.PosVendore.DataNote;
import parth.com.buiit.R;
import userInteface.Activities.InvoiceDisplayActivity;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder>
{
    private ArrayList<DataNote> dataList;
    Context context;
    Intent intent;

    public InvoiceListAdapter(ArrayList<DataNote> data,Context context)
    {
        this.dataList = data;
        this.context = context;
        intent = new Intent(context,InvoiceDisplayActivity.class);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView invoice_no;
        TextView date_and_time;
        TextView spending;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.invoice_no = (TextView) itemView.findViewById(R.id.invoice_no);
            this.date_and_time = (TextView) itemView.findViewById(R.id.date_and_time);
            this.spending = (TextView) itemView.findViewById(R.id.spending);
        }
    }

    @Override
    public InvoiceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InvoiceListAdapter.ViewHolder holder, final int position)
    {
        holder.invoice_no.setText(dataList.get(position).getInvoiceNo());
        holder.date_and_time.setText(dataList.get(position).getDateAndTime());
        holder.spending.setText(dataList.get(position).getSpending());


        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               context.startActivity(intent); //Toast.makeText(, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}