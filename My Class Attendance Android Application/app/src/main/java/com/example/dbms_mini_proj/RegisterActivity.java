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

public class RegisterActivity extends AppCompatActivity {

    EditText nameid, rollid, yearid, deptid;

    Button submitBt, dashBt;

    boolean isAllFieldsChecked = false;

    private static final String url="https://theprolevel.000webhostapp.com/register_students.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameid=findViewById(R.id.nameid);
        rollid=findViewById(R.id.rollid);
        yearid=findViewById(R.id.yearid);
        deptid=findViewById(R.id.dept);

        submitBt=findViewById(R.id.register);
        dashBt=findViewById(R.id.dashBd);

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                if(isAllFieldsChecked) {

                    register_user(nameid.getText().toString(), rollid.getText().toString(), yearid.getText().toString(), deptid.getText().toString());
                }
            }
        });

        dashBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }


    public void register_user(final String name, final String roll, final String year, final String dept){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                nameid.setText("");
                rollid.setText("");
                yearid.setText("");
                deptid.setText("");

                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                if (response.equals("inserted")){
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                nameid.setText("");
                rollid.setText("");
                yearid.setText("");
                deptid.setText("");
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("roll",roll);
                map.put("year",year);
                map.put("dept",dept);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }


    private boolean CheckAllFields(){
        if(nameid.length()==0){
            nameid.setError("Name is required!");
            return false;
        }if(rollid.length()==0){
            rollid.setError("Roll no is required!");
            return false;
        }if(yearid.length()==0){
            yearid.setError("Year required!");
            return false;
        }if(deptid.length()==0){
            deptid.setError("Department required!");
            return false;
        }
        return true;
    }

}