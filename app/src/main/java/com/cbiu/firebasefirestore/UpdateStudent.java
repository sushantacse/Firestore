package com.cbiu.firebasefirestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateStudent extends AppCompatActivity {

    private EditText name, phone;
    private Button updatebtn;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phoneno);
        updatebtn = findViewById(R.id.update);
        student = (Student) getIntent().getSerializableExtra("model");
        name.setText(student.getName());
        phone.setText(student.getPhone());

        // to set cursor into edit text in endpoint view
        name.setSelection(name.getText().length());
        phone.setSelection(phone.getText().length());

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatestudentdata();
            }
        });
    }

    private void updatestudentdata() {

        String NAME = name.getText().toString();
        String PHONE = phone.getText().toString();
        Student std = new Student(NAME, PHONE);
        db.collection("student").document(student.getId())
                .update("name", std.getName(),
                        "phone", std.getPhone())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(UpdateStudent.this, "Student Data Updated", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        phone.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(UpdateStudent.this, "Student Data Failed to update", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
