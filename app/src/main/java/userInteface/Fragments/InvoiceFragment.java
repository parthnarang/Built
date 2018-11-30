package userInteface.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import app.AppController;
import model.Movie;
import model.builtCore.Invoice;
import model.builtCore.InvoiceItem;
import model.builtCore.Merchant;
import parth.com.buiit.R;
import userInteface.Activities.StoreInvoiceListActivity;
import userInteface.Adapters.CustomListAdapter;

import static com.android.volley.VolleyLog.TAG;
import static model.URLs.USER_INVOICE_LIST;

public class InvoiceFragment extends Fragment {

    private static final String url = USER_INVOICE_LIST;
    private ProgressDialog pDialog;
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();
    private HashMap<Long,Merchant> merchantHashMap = new HashMap<>();
    private ArrayList<Merchant> merchantList = new ArrayList<Merchant>() ;
    private ListView listView;
    private CustomListAdapter adapter;
    private Context context;
    private String TAG = InvoiceFragment.class.getName();
    private Gson gson;

    public InvoiceFragment(){

    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.content_user_profile, container, false);
        listView = (ListView)root.findViewById(R.id.list);
        adapter = new CustomListAdapter(this.getActivity(),merchantList);
        listView.setAdapter(adapter);

        final Intent intent = new Intent(getContext(),StoreInvoiceListActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Bundle bundle = new Bundle();
              bundle.putSerializable("merchant",merchantList.get(position));
               intent.putExtra("merchant_invoices",merchantList.get(position));
                startActivity(intent);
            }
        });

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.serializeNulls().create();


        pDialog = new ProgressDialog(this.getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        // Creating volley request obj
        JsonArrayRequest invoiceRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "response received from server :"+ response.toString());
                        hidePDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                long merchantid = Long.parseLong(jsonObject.get("merchantId").toString());

                                String merchantLogo = jsonObject.get("merchantLogo").toString();

                                JSONObject invoiceData = jsonObject.getJSONObject("data");
                                String merchantName = invoiceData.get("merchant name").toString();

                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
                                String time = jsonObject.getString("dateAndTime");
                                Date date =null;
                                if(!time.equals("null")){
                                    date = format.parse(time);
                                }


                                JSONArray invoiceItems = invoiceData.getJSONArray("ITEMS");
                                invoiceItemList = Arrays.asList(gson.fromJson(invoiceItems.toString(),InvoiceItem[].class));

                               Invoice invoice = gson.fromJson(invoiceData.toString(),Invoice.class);
                               invoice.setInvoiceItem(invoiceItemList);
                               invoice.setDateAndTime(date);

                                Merchant merchant= null;

                                if(merchantHashMap.containsKey(merchantid)){
                                   merchant  = merchantHashMap.get(merchantid);


                                } else {
                                    merchant = new Merchant();
                                    merchant.setMid(merchantid);
                                    merchant.setMerchantLogo(merchantLogo);
                                    merchant.setMerchantName(merchantName);
                                    merchantHashMap.put(merchantid,merchant);
                                }
                                merchant.addNewInvoice(invoice);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            catch (ParseException e){
                                e.printStackTrace();
                            }

                        }
                        Collection<Merchant>values = merchantHashMap.values();

                        ArrayList<Merchant> list = new ArrayList<Merchant>(values);
                        merchantList.addAll(list);
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(invoiceRequest);
        return root;
    }

}
