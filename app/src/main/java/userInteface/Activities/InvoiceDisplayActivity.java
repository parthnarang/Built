package userInteface.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import database.repositories.InvoiceItemRepository;
import database.repositories.InvoiceRepository;
import model.billtCore.Invoice;
import model.billtCore.InvoiceItem;
import parth.com.buiit.R;

import butterknife.BindView;
import parth.com.buiit.R;
import tasks.NotificationTasks;

public class InvoiceDisplayActivity extends AppCompatActivity {

    @BindView(R.id.merchant_name)
    TextView merchant_name;
    @BindView(R.id.merchant_address)
    TextView merchant_address;
    @BindView(R.id.gst_no)
    TextView gst_no;
    @BindView(R.id.bill_no)
    TextView bill_no;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.item_table)
    TableLayout item_table;
    @BindView(R.id.subtotal)
    TextView subtotal;
    @BindView(R.id.vat)
    TextView vat;
    @BindView(R.id.discount)
    TextView discount;
    @BindView(R.id.total)
    TextView total;


    TextView description,quantity,rate,amount;

    private InvoiceRepository invoiceRepository;
    private InvoiceItemRepository invoiceItemRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice);
        NotificationTasks notificationTasks = new NotificationTasks(this);
        notificationTasks.showNewInvoiceNotification(new Invoice());
        invoiceItemRepository = new InvoiceItemRepository(this);
        invoiceRepository = new InvoiceRepository(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id",0);
        Invoice invoice = invoiceRepository.getById(id);

        if(invoice!=null){
            merchant_name.setText(invoice.getDisplayName());
            merchant_address.setText(invoice.getAddress());
           // gst_no.setText(invoice.getGstNo());
            bill_no.setText(invoice.getInvId());
            date.setText(invoice.getDate());
            time.setText(invoice.getTime());
            subtotal.setText(Double.toString(invoice.getNetAmount())+" Rs");
            vat.setText(Double.toString(invoice.getVat()));
            total.setText(Double.toString(invoice.getTotalAmount())+" Rs");

            List<InvoiceItem> invoiceItemList = invoiceItemRepository.getInvoiceItemFromInvoiceId(invoice.getId());


            for(InvoiceItem invoiceItem : invoiceItemList){

                TableRow row= new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                row.setPadding(10,10,10,10);
                description = new TextView(this);
                description.setTextColor(getResources().getColor(R.color.heading_color));
                description.setText(invoiceItem.getDescription());
                row.addView(description);
                quantity = new TextView(this);
                quantity.setTextColor(getResources().getColor(R.color.heading_color));
               quantity.setText(Integer.toString(invoiceItem.getQuantity()));
                row.addView(quantity);
                rate = new TextView(this);
                rate.setTextColor(getResources().getColor(R.color.heading_color));
                rate.setText(Double.toString(invoiceItem.getRate()));
                row.addView(rate);
                amount = new TextView(this);
                amount.setTextColor(getResources().getColor(R.color.heading_color));
                amount.setText(Double.toString(invoiceItem.getAmount()));
                row.addView(amount);
                item_table.addView(row, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));


            }


        }


    }
}
