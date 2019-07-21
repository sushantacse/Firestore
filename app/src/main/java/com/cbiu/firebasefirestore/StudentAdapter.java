package com.cbiu.firebasefirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Holder> {

    private List<Student> studentList;
    private Context context;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_student,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        holder.STUDENTNAME.setText(studentList.get(position).getName());
        holder.STUDENTPHONENO.setText(studentList.get(position).getPhone());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(context, "Clicked On "+name, Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView STUDENTNAME,STUDENTPHONENO;
        Holder(@NonNull View itemView) {
            super(itemView);
            STUDENTNAME = itemView.findViewById(R.id.name);
            STUDENTPHONENO = itemView.findViewById(R.id.phone);
        }
    }
}
