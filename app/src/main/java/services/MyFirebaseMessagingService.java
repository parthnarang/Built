package services;


import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;

import model.billtCore.Invoice;


//class extending FirebaseMessagingService
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private Gson gson;

    public MyFirebaseMessagingService(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.serializeNulls().create();
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("newToken", s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size() > 0){
            //handle the data message here
        }

        JSONObject json = new JSONObject(remoteMessage.getData());
        Invoice invoice = gson.fromJson(json.toString(), Invoice.class);

        try {
            Log.d("heyabc  =  ", invoice.getAddress());
        }
        catch (Exception e){
            
        }
        //Invoice newInvoice = gson.fromJson(remoteMessage.getData().toString(), Invoice.class);

       // Log.d("heyabc",newInvoice.toString());
        //then here we can use the title and body to build a notification
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }
}