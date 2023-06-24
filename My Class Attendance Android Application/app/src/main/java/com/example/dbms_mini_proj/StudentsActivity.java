package com.example.dbms_mini_proj;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;
    private List<studentlist_activity> studentList;

    String dval = MainActivity.rtnDay();
    String mval = MainActivity.rtnMon();
    String yval = MainActivity.rtnYear();

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

    }

}