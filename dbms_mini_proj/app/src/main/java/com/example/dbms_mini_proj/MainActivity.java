package com.example.dbms_mini_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button downloadBtn, registerBtn, deleteAttnDataBtn;
    public static String text = "";
    public static String text1 = "";
    public static String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // spinner2

        Spinner spinner1 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.months, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text1 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), text1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // spinner3

        Spinner spinner2 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.years, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text2 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), text2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        btStList = findViewById(R.id.btnStudentList);

        downloadBtn=(Button) findViewById(R.id.buttonDownload);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DownloadActivity.class));
                Toast.makeText(MainActivity.this, "Download Started!", Toast.LENGTH_SHORT).show();
            }
        });

        registerBtn=(Button) findViewById(R.id.buttonReg);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        deleteAttnDataBtn=(Button) findViewById(R.id.btnDeleteAttendance);

        deleteAttnDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DeletionConsentActivity.class));
                Toast.makeText(MainActivity.this, "Warning! Download Data Before Proceeding", Toast.LENGTH_SHORT).show();
            }
        });


//        btStList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext().this,StudentsActivity));
//            }
//        });
    }
//    public static String text = "";
//    public static String text1 = "";
//    public static String text2 = "";
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        text = parent.getItemAtPosition(position).toString();
//        text1 = parent.getItemAtPosition(position).toString();
//        text2 = parent.getItemAtPosition(position).toString();
////        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//        Toast.makeText(parent.getContext(), text1, Toast.LENGTH_SHORT).show();
////        Toast.makeText(parent.getContext(), text2, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//    // spinner2 handling
//
//
//
    public void showStudents(View view) {
        Intent intent = new Intent(MainActivity.this,StudentsActivity.class);
        Toast.makeText(MainActivity.this, rtnDay(), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, rtnMon(), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, rtnYear(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
////    public void downloadRedirect(View view) {
////        Intent intent = new Intent(MainActivity.this,DownloadActivity.class);
////        Toast.makeText(MainActivity.this, "Download Started!", Toast.LENGTH_SHORT).show();
////        startActivity(intent);
////    }
//
    public static String rtnDay(){
        return text;
    }
    public static String rtnMon(){
        return text1;
    }
    public static String rtnYear(){
        return text2;
    }

}