package com.cbiu.firebasefirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText name,phone;
    private Button savebtn;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phoneno);
        savebtn = findViewById(R.id.save);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveStudentData();
            }
        });

    }

    private void saveStudentData() {

        String getname = name.getText().toString();
        String getphoneno = phone.getText().toString();
        Student student = new Student(getname,getphoneno);
        CollectionReference reference = db.collection("student");
        reference.add(student).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(),"Data Saved Successfull",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        phone.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"Data Saved Failed",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void gotoview(View view) {

        startActivity(new Intent(getApplicationContext(),StudentDetails.class));
    }
}
