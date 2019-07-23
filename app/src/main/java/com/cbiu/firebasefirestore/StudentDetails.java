package com.cbiu.firebasefirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StudentDetails extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        studentList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        studentAdapter = new StudentAdapter(studentList, StudentDetails.this);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
        viewStudentData();
    }

    private void viewStudentData() {

        db.collection("student").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (queryDocumentSnapshots.isEmpty())
                {
                    Toast.makeText(StudentDetails.this, "No data Found", Toast.LENGTH_SHORT).show();
                }

                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : list)
                {
                    Student student = ds.toObject(Student.class);
                    student.setId(ds.getId());
                    studentList.add(student);

                }
                studentAdapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(),"Failed "+e,Toast.LENGTH_SHORT).show();

            }
        });

    }
}
