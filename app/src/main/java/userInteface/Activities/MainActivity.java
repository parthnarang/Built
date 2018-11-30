package userInteface.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import handlers.VolleyQueue;
import model.URLs;
import parth.com.buiit.R;
import utils.CountryData;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editText;
    private String number;
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.VALIDATE_MOBILE,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    // progressBar.setVisibility(View.GONE);

                    try {
                        //converting response to json object
                       /* JSONObject obj = new JSONObject(response);

                        //if no error in response
                        /*if (!obj.getBoolean("status")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject("user");*/
                       if(response.equals("true")) {
                           finish();
                           startActivity(new Intent(getApplicationContext(), VerifyPhoneActivity.class));
                       }
                       else {
                           Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                           intent.putExtra("phonenumber", number);
                           startActivity(intent);
                       }


                            //Toast.makeText(MainActivity.this, response, 3).show();
                          //  startActivity(new Intent(getApplicationContext(), SignupActivity.class));

                        }
                     catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("phoneno", number);
            return params;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextPhone);
         findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = editText.getText().toString().trim();
                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                VolleyQueue.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
                String phoneNumber = number;
              //  Intent intent = new Intent(MainActivity.this, VerifyPhoneActivity.class);
              //  intent.putExtra("phonenumber", number);
               // startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
    }
