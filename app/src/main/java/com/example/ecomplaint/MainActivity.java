package com.example.ecomplaint;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    /*public static ArrayList arrayList;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    ArrayList<Expert> expertList;
    FirebaseDatabase rootNode;
    DatabaseReference referenceExpert;*/
    TextView register;
    EditText editTextName;
    EditText editTextPassword;
    Button login;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rootNode = FirebaseDatabase.getInstance();

        editTextName=findViewById(R.id.editTextName);

        editTextPassword=findViewById(R.id.editTextPassword);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent i = new Intent(getApplicationContext(),MainPage.class);
            startActivity(i);
        }

        login=findViewById(R.id.login);


        register=findViewById(R.id.register);

        mAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=editTextName.getText().toString().trim();

                String password=editTextPassword.getText().toString().trim();

                if(email.isEmpty()){
                    editTextName.setError("Enter name");
                    editTextName.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextName.setError("Email is not correct");
                    editTextName.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    editTextPassword.setError("Enter password");
                    editTextPassword.requestFocus();
                    return;
                }

                if(password.length()<6){
                    editTextPassword.setError("password is too short ");
                    editTextPassword.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

//                            if(user.isEmailVerified()){
                            String EAd= new String("admin@gmail.com");
                            String PAd =new String("password");
                            if (email.equals(EAd)) {
                                if(password.equals(PAd))
                                    startActivity(new Intent(MainActivity.this, com.example.ecomplaint.adminPage.class));
                            } else {
                                Intent i = new Intent(getApplicationContext(),MainPage.class);
                                startActivity(i);
                            }

//                            }
//                            else{
//                                user.sendEmailVerification();
//                                Toast.makeText(getApplicationContext(), "Check your email to verify", Toast.LENGTH_SHORT).show();
//                            }



                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }
        });







/*
        tabLayout=findViewById(R.id.tabLayout);

        viewPager=findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter= new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new HomeFragment(),"Complaints");
        vpAdapter.addFragment(new ChatFragment(),"chat");

        viewPager.setAdapter(vpAdapter);*/


    }

}