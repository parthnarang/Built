package userInteface.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import handlers.VolleyQueue;
import model.URLs;
import model.User;
import parth.com.buiit.R;
import userInteface.Activities.MainActivity;
import utils.EmailValidator;

import static model.URLs.MERCHANT_LOGO;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPhoneNo, fullname;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    String phoneNumber="";
    String email="";
    String name ="";
    JSONObject jsonArray = new JSONObject();
    String mRequestBody=null;

    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.SIGNUP,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("parthhey","onrespose");
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), VerifyPhoneActivity.class);
                    intent.putExtra("phonenumber", phoneNumber);
                    startActivity(intent);

                    try {
                        //converting response to json object
                       JSONObject obj = new JSONObject(response);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("parthhey","checking error "+ error.toString());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    int  statusCode = error.networkResponse.statusCode;
                    NetworkResponse response = error.networkResponse;

                    Log.d("testerror",""+statusCode+" "+response.data);
                }
            }) {
        @Override
        public String getBodyContentType() {
            Log.d("parthhey","checking content");
            return "application/json; charset=utf-8";
        }
        @Override
        public byte[] getBody() throws AuthFailureError {
            try{
                Log.d("parthhey","checking");
            jsonArray.put("fullname", name);
            jsonArray.put("email", email);
            jsonArray.put("mobile", phoneNumber);
            mRequestBody = jsonArray.toString();
                return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
            }
            catch (Exception e){
                Log.d("parthhey",e.toString());
                return null;
            }

        }


    };

    final StringRequest merchantLogoRequest = new StringRequest(Request.Method.GET,MERCHANT_LOGO,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("parthhey", "response received from server for merchantLogoRequest :" + response.toString());
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if(error  instanceof NoConnectionError)
                Log.d("parthhey","abc");



        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       phoneNumber = getIntent().getStringExtra("phonenumber");

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

     //   btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.buttonSignUp);
        inputEmail = (EditText) findViewById(R.id.emailaddress);
        inputPhoneNo = (EditText) findViewById(R.id.phone_no);
        fullname =(EditText)findViewById(R.id.full_name);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        inputPhoneNo.setText(phoneNumber);
        inputPhoneNo.setFocusable(false);

        VolleyQueue.getInstance(SignupActivity.this).addToRequestQueue(merchantLogoRequest);


     /*   btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 email = inputEmail.getText().toString().trim();
                 name = fullname.getText().toString().trim();
            //    String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter enter all details!", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean valid = EmailValidator.validateEmail(email);
                if(!valid){
                    Toast.makeText(getApplicationContext(), "Invalid Email !", Toast.LENGTH_SHORT).show();
                    return;
                }

              VolleyQueue.getInstance(SignupActivity.this).addToRequestQueue(stringRequest);


             //   Intent intent = new Intent(SignupActivity.this, VerifyPhoneActivity.class);
             //   intent.putExtra("phonenumber", phoneNumber);
             //   startActivity(intent);
            /*    if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                progressBar.setVisibility(View.VISIBLE);
                //create user
              /*  auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });*/

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}