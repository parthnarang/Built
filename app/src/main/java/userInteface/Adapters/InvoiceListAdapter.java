package userInteface.Adapters;

import android.arch.persistence.room.util.StringUtil;
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
import java.util.List;

import model.billtCore.Invoice;
import parth.com.buiit.R;
import userInteface.Activities.InvoiceDisplayActivity;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder>
{
    private List<Invoice> invoiceList;
    Context context;
    Intent intent;

    public InvoiceListAdapter(List<Invoice> data,Context context)
    {
        this.invoiceList = data;
        this.context = context;
        intent = new Intent(context,InvoiceDisplayActivity.class);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView invoice_no;
        TextView date_and_time;
        TextView spending;
        TextView address;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.invoice_no = (TextView) itemView.findViewById(R.id.invoice_no);
            this.date_and_time = (TextView) itemView.findViewById(R.id.date_and_time);
            this.spending = (TextView) itemView.findViewById(R.id.spending);
            this.address =(TextView)itemView.findViewById(R.id.store_address);
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
        holder.invoice_no.setText(invoiceList.get(position).getInvId());
        holder.date_and_time.setText(invoiceList.get(position).getDateAndTime());
        holder.address.setText(invoiceList.get(position).getAddress());
        holder.spending.setText(Double.toString(invoiceList.get(position).getNetAmount()) + " Rs");

        final Invoice invoice = invoiceList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                intent.putExtra("id",invoice.getId());
               context.startActivity(intent); //Toast.makeText(, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return invoiceList.size();
    }
}