package com.example.ecomplaint;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterComplaint extends AppCompatActivity {

    // creating variables for
    // EditText and buttons.
    private EditText title;
    private EditText regno;
    private EditText name;
    private EditText incident;
    private EditText phone;
    private Button sendDatabtn;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    ComplaintDetails complaintInfo= new ComplaintDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);

        // initializing our edittext and button
        title = findViewById(R.id.title);
        phone = findViewById(R.id.phno);
        name = findViewById(R.id.name);
        incident = findViewById(R.id.name1);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("ComplaintInfo");

        // initializing our object
        // class variable.
        complaintInfo = new ComplaintDetails();

        sendDatabtn = findViewById(R.id.submitbtn);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String Title = title.getText().toString();
                String Phone = phone.getText().toString();
                String Name = name.getText().toString();
                String Incident = incident.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(Phone) && TextUtils.isEmpty(Title) && TextUtils.isEmpty(Incident)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(RegisterComplaint.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(Title,Phone,Name,Incident);
                    //move to home fragment
                    startActivity(new Intent(RegisterComplaint.this, HomeFragment.class));
                }
            }
        });
    }

    private void addDatatoFirebase(String title, String phone, String name, String incident) {
        // below 3 lines of code is used to set
        // data in our object class.
        complaintInfo.setTitle(title);
        complaintInfo.setPhoneNumber(phone);
        complaintInfo.setName(name);
        complaintInfo.setComplaint(incident);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(complaintInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(RegisterComplaint.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(RegisterComplaint.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
