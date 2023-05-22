package com.example.dbms_mini_proj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;
    private List<studentlist_activity> studentList;

//    String t1;

//    static MainActivity obj1 = new MainActivity();
    String dval = MainActivity.rtnDay();
    String mval = MainActivity.rtnMon();
    String yval = MainActivity.rtnYear();

//    private static final String apiurl = "https://theprolevel.000webhostapp.com/users.php";
    private final String apiurl_dval = "https://theprolevel.000webhostapp.com/testing_version.php" + "?t1=" + dval + "&t2=" + mval +"&t3=" + yval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        recyclerView = findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentList = new ArrayList<>();

        LoadAllStudents();
    }

    private void LoadAllStudents(){

//        apiurl_dval += "?t1=" + dval;

        JsonArrayRequest request = new JsonArrayRequest(apiurl_dval, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                for (int i=0; i<array.length(); i++){
                    try {
                        JSONObject object = array.getJSONObject(i);
                        String sl = object.getString("id").trim();
                        String roll = object.getString("roll").trim();
                        String name = object.getString("name").trim();
                        String status = object.getString("dvalue").trim();

                        studentlist_activity students = new studentlist_activity();
                        students.setSl(sl);
                        students.setName(name);
                        students.setRoll(roll);
//                        students.setStatus(status);
                        if(status.equals("1")){
                            students.setStatus("Present");
                        }else {
                            students.setStatus("Absent");
                        }
                        studentList.add(students);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                studentsAdapter = new StudentsAdapter(StudentsActivity.this,studentList);
                recyclerView.setAdapter(studentsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(StudentsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(StudentsActivity.this);
        requestQueue.add(request);

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiurl_dval, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(StudentsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> params = new HashMap<>();
//                params.put("dvalue",dvalue);
//                return params;
//            }
//        };
//        RequestQueue queuex = Volley.newRequestQueue(StudentsActivity.this);
//        queuex.add(stringRequest);
//
//        JsonArrayRequest requestx = new JsonArrayRequest(apiurl_dval, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray array) {
//                for (int i=0; i<array.length(); i++){
//                    try {
//                        JSONObject object = array.getJSONObject(i);
//                        String status = object.getString("dvalue").trim();
//
//                        studentlist_activity students = new studentlist_activity();
//                        students.setStatus(status);
//                        studentList.add(students);
//
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                    }
//                }
//                studentsAdapter = new StudentsAdapter(StudentsActivity.this,studentList);
//                recyclerView.setAdapter(studentsAdapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(StudentsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue requestQueuex = Volley.newRequestQueue(StudentsActivity.this);
//        requestQueuex.add(requestx);


//        String qry="?t1="+dval.trim();
//
//        class dbprocess extends AsyncTask<String,Void,String>
//        {
//
//            protected void onPostExecute(JSONArray jsonArray) {
//                super.onPostExecute(String.valueOf(jsonArray));
//                // Do something with the JSON array
//            }
//            @Override
//            protected String doInBackground(String... params)
//            {
//                String furl=params[0];
//
//                try
//                {
//                    URL url=new URL(furl);
//                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                    return br.readLine();
//
//                }catch (Exception ex)
//                {
//                    return ex.getMessage();
//                }
//            }
//
//
//        }
//
//        dbprocess obj=new dbprocess();
//        obj.execute(apiurl_dval+qry);


    }
    // background tasks for retreiving the present status from php background

//    String qry="?t1="+dval.trim();
//
//    protected class dbprocess extends AsyncTask<String,Void,String>
//    {
//        @Override
//        protected  void onPostExecute(String data)
//        {
//
//        }
//        @Override
//        protected String doInBackground(String... params)
//        {
//            String furl=params[0];
//
//            try
//            {
//                URL url=new URL(furl);
//                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                return br.readLine();
//
//            }catch (Exception ex)
//            {
//                return ex.getMessage();
//            }
//        }
//    }
//
//    dbprocess obj=new dbprocess();
//        obj.execute(apiurl_dval+qry);

    // end of background status check task




}