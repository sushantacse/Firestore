package com.cbiu.firebasefirestore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Holder> {

    private List<Student> studentList;
    private Context context;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    StudentAdapter(List<Student> studentList, Context context) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Please Select Your Operation");
                builder.setPositiveButton("DELETE STUDENT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Student student = studentList.get(position);
                        db.collection("student").document(student.getId()).delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(context, "Delete Successfull", Toast.LENGTH_SHORT).show();
                                            studentList.clear();
                                        }
                                        else {

                                            Toast.makeText(context, "Not DELETED", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                    }
                });
                builder.setNegativeButton("CLOSE OPTIONS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("UDPATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            Student student = studentList.get(position);
                            Intent a = new Intent(context,UpdateStudent.class);
                            a.putExtra("model",student);
                            context.startActivity(a);

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

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
