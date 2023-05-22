package com.example.dbms_mini_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DeletionConsentActivity extends AppCompatActivity {

    EditText nameid;

    Button submitBt;

//    boolean isAllFieldsChecked = false;

    private static final String url="https://theprolevel.000webhostapp.com/delete_attendance_data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletion);

        nameid=findViewById(R.id.enterConsent);


        submitBt=findViewById(R.id.buttonDeletion);

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_data(nameid.getText().toString());
            }
        });

//        dashBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            }
//        });

    }


    public void delete_data(final String name){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                nameid.setText("");
//                emailET.setText("");
//                paymentET.setText("");
//                payRecNameEt.setText("");

                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                if (response.equals("successfull")){
//                    startActivity(new Intent(getApplicationContext(),SpotPaymentActivity.class));
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                nameid.setText("");

//                emailET.setText("");
//                paymentET.setText("");
//                payRecNameEt.setText("");
//
                Toast.makeText(getApplicationContext(),"Action Failed!",Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
//                map.put("email",email);
//                map.put("transacinfo",transacinfo);
//                map.put("payreceiver",payReceiver);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }


//    private boolean CheckAllFields(){
//        if(!nameid.equals("Yes I Confirm")) {
//            nameid.setError("Please confirm!");
//            return false;
//        }
////        if(emailET.length()==0){
////            emailET.setError("Email id required!");
////            return false;
////        }if(paymentET.length()==0){
////            paymentET.setError("UPI / CASH info required!");
////            return false;
////        }if(payRecNameEt.length()==0){
////            payRecNameEt.setError("Payment receiver name required!");
////            return false;
////        }
//        return true;
//    }

}