package com.example.ecomplaint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    EditText name,password,age,address,contact,username;
    Button register;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        name=findViewById(R.id.cname);
        age=findViewById(R.id.age);
        address=findViewById(R.id.address);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        contact=findViewById(R.id.contact);

        database = FirebaseDatabase.getInstance();


        register=findViewById(R.id.signupbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nameValue=name.getText().toString().trim();

                String usernameValue=username.getText().toString().trim();

                String passwordValue=password.getText().toString().trim();

                String ageValue=age.getText().toString().trim();
                String addressValue=address.getText().toString().trim();
                String contactValue=contact.getText().toString().trim();

                if(nameValue.isEmpty()){
                    name.setError("Enter name");
                    name.requestFocus();
                    return;
                }
                if(usernameValue.isEmpty()){
                    username.setError("Enter username");
                    username.requestFocus();
                    return;
                }
                if(usernameValue.isEmpty()){
                    contact.setError("Enter phone no");
                    contact.requestFocus();
                    return;
                }
                if(addressValue.isEmpty()){
                    contact.setError("Enter address:");
                    contact.requestFocus();
                    return;
                }
                if(passwordValue.isEmpty()){
                    password.setError("Enter password");
                    password.requestFocus();
                    return;
                }


                if(password.length()<2){
                    password.setError("password is too short ");
                    password.requestFocus();
                    return;
                }

                public void DAOUser() {
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    database = db.getReference(User.class.getSimpleName()).getDatabase();

                }
                public Task<void> add(User u){
                    if(emp==null)
                    databaseReference.push().setValue(emp);
                }

            });
        }


    }
}