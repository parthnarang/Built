package userInteface.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.AppController;
import database.AppDatabase;
import database.dao.InvoiceDao;
import database.dao.InvoiceItemDao;
import database.repositories.InvoiceItemRepository;
import database.repositories.InvoiceRepository;
import database.repositories.MerchantRepository;
import model.billtCore.Invoice;
import model.billtCore.InvoiceItem;
import model.billtCore.Merchant;
import parth.com.buiit.R;
import tasks.NotificationTasks;
import userInteface.Activities.StoreInvoiceListActivity;
import userInteface.Activities.UserProfileActivity;
import userInteface.Adapters.CustomListAdapter;

import static model.URLs.MERCHANT_LOGO;
import static model.URLs.USER_INVOICE_LIST;

public class InvoiceFragment extends Fragment {

    private static final String url = USER_INVOICE_LIST;
    private ProgressDialog pDialog;
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();
    private HashMap<Long,Merchant> merchantHashMap = new HashMap<>();
    private List<Merchant> merchantList = new ArrayList<Merchant>() ;
    private ListView listView;
    private CustomListAdapter adapter;
    private Context context;
    private String TAG = InvoiceFragment.class.getName();
    private Gson gson;
    private MerchantRepository merchantRepository;
    private InvoiceRepository invoiceRepository;
    private InvoiceItemRepository invoiceItemRepository;
    private Merchant merchant;


    public InvoiceFragment(){

    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.content_user_profile, container, false);
        listView = (ListView)root.findViewById(R.id.list);
        merchantRepository = new MerchantRepository(getContext());
        invoiceRepository = new InvoiceRepository(getContext());
        invoiceItemRepository = new InvoiceItemRepository(getContext());
        adapter = new CustomListAdapter(this.getActivity(),merchantRepository.getMerchantList());
        listView.setAdapter(adapter);

        final Intent intent = new Intent(getContext(),StoreInvoiceListActivity.class);




        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.serializeNulls().create();


        pDialog = new ProgressDialog(this.getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");

        final StringRequest merchantLogoRequest = new StringRequest(Request.Method.GET,MERCHANT_LOGO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "response received from server for merchantLogoRequest :" + response.toString());




                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error for merchant Logo Request: " + error.getMessage());
                hidePDialog();

            }
        });
        Log.d("abc",MERCHANT_LOGO);
        /*JsonObjectRequest invoiceRequest = new JsonObjectRequest(MERCHANT_LOGO,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                     /* for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);

                               Invoice invoice = gson.fromJson(jsonObject.toString(), Invoice.class);

                                if (invoiceRepository.validateInvoice(invoice.getInvId()) == null) {

                                    Merchant merchant = merchantRepository.validateInvoice(invoice.getMid());
                                    if(merchant==null){
                                        String vid = jsonObject.get("vid").toString();
                                       // String merchantName = invoiceData.get("merchant name").toString();
                                        merchant = new Merchant(invoice.getMid(),invoice.getDisplayName(),null,vid);
                                         merchantRepository.insert(merchant);

                                    }
                                    if(merchant.getMerchantLogo()== null){
                                      //  AppController.getInstance().addToRequestQueue(merchantLogoRequest);
                                    }

                                        String time = jsonObject.getString("billtDate");

                                        JSONArray invoiceItems = jsonObject.getJSONArray("invoiceItems");
                                        invoiceItemList = Arrays.asList(gson.fromJson(invoiceItems.toString(), InvoiceItem[].class));

                                        invoice.setInvoiceItem(invoiceItemList);
                                        invoice.setDateAndTime(time);
                                        invoice.setMerchantId(merchant.getId());
                                        invoiceRepository.insert(invoice);

                                        for(InvoiceItem invoiceItem : invoiceItemList){

                                            invoiceItem.setInvoice_id(invoice.getId());
                                            invoiceItemRepository.insert(invoiceItem);

                                    }
                                }
                         }

                        catch(JSONException e){
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), R.string.server_data_error, Toast.LENGTH_LONG).show();
                                }

                    }

                          adapter.swapItems(merchantRepository.getMerchantList());

                }
                }
                ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if ( error instanceof NoConnectionError) {
                    Log.d("abcd","hahaha");
                }
                VolleyLog.d("abcd", "Error abc : " + error.getMessage());
                hidePDialog();

            }
        });*/



        // Adding request to request queue
        if(merchantList.size() == 0) {
            pDialog.show();
        }

        Log.d("checkp","i am here");
           AppController.getInstance().addToRequestQueue(merchantLogoRequest);
        Log.d("checkp","i am here1");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent.putExtra("id",position+1);
                startActivity(intent);
            }
        });

        return root;
    }



}
