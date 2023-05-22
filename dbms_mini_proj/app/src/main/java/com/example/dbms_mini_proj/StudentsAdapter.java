package com.example.dbms_mini_proj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsHolder> {
    Context context;
    List<studentlist_activity> studentsList;

    private static final String dayAPI = "https://theprolevel.000webhostapp.com/mark_present.php";

    String dayVal = MainActivity.rtnDay();
    String monVal = MainActivity.rtnMon();
    String yearVal = MainActivity.rtnYear();

    public StudentsAdapter(Context context, List<studentlist_activity> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public StudentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View studentLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list,parent,false);
        return new StudentsHolder(studentLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsHolder holder, int position) {
        studentlist_activity students = studentsList.get(position);

        holder.sl.setText(students.getSl());
        holder.name.setText(students.getName());
        holder.roll.setText(students.getRoll());
        if (students.getStatus().equals("Present")){
            holder.status.setText(students.getStatus());
            holder.status.setTextColor(Color.GREEN);
        }else {
            holder.status.setText(students.getStatus());
            holder.status.setTextColor(Color.RED);
        }
//        holder.status.setText(students.getStatus());
        holder.markPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("MARK PRESENT-> "+students.getName());
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String rollno = students.getRoll().trim();
                        Toast.makeText(context, dayVal, Toast.LENGTH_SHORT).show();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, dayAPI, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("present")){
                                    int position = holder.getAdapterPosition();
                                    if (position != RecyclerView.NO_POSITION) {
                                        removeItem(position);
                                    }
                                }

                                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){

                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> params = new HashMap<>();
                                params.put("dcol",dayVal);
                                params.put("roll",rollno);
                                params.put("mon",monVal);
                                params.put("yr",yearVal);
                                return params;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(stringRequest);
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    // remove after present

    public void removeItem(int position) {
        studentsList.remove(position);
        notifyItemRemoved(position);
    }

    //

    public class StudentsHolder extends RecyclerView.ViewHolder{

        TextView sl,name,roll,status;
        Button markPresent;

        public StudentsHolder(@NonNull View itemView) {
            super(itemView);
            sl = itemView.findViewById(R.id.rcy_slno);
            name = itemView.findViewById(R.id.rcy_name);
            roll= itemView.findViewById(R.id.rcy_roll);
            markPresent = itemView.findViewById(R.id.btnPresent);
            status = itemView.findViewById(R.id.tvStatus);

        }
    }
}
