package com.example.ecomplaint;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



//public class MainActivity extends AppCompatActivity {
//
//    Button btnLogOut;
//    FirebaseAuth mAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnLogOut = findViewById(R.id.btnLogout);
//        mAuth = FirebaseAuth.getInstance();
//
//        btnLogOut.setOnClickListener(view ->{
//            mAuth.signOut();
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        });
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user == null){
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
//    }
//
//
//}

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    // Make sure to use the FloatingActionButton
    // for all the FABs
    FloatingActionButton mAddFab, mLogoutFab, mWriteComplaintFab;

    // These are taken to make visible and invisible along
    // with FABs
    TextView logoutText, writeComplaintText;

    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Register all the FABs with their IDs
        // This FAB button is the Parent
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        mLogoutFab = findViewById(R.id.logout_fab);
        mWriteComplaintFab = findViewById(R.id.write_complaint_fab);

//        creating firebase object
        mAuth = FirebaseAuth.getInstance();

        // Also register the action name text, of all the FABs.
        logoutText = findViewById(R.id.logout_text);
        writeComplaintText = findViewById(R.id.write_complaint_text);

        // Now set all the FABs and all the action name
        // texts as GONE
        mLogoutFab.setVisibility(View.GONE);
        mWriteComplaintFab.setVisibility(View.GONE);
        logoutText.setVisibility(View.GONE);
        writeComplaintText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            mLogoutFab.show();
                            mWriteComplaintFab.show();
                            logoutText.setVisibility(View.VISIBLE);
                            writeComplaintText.setVisibility(View.VISIBLE);

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            mLogoutFab.hide();
                            mWriteComplaintFab.hide();
                            logoutText.setVisibility(View.GONE);
                            writeComplaintText.setVisibility(View.GONE);

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });

        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mWriteComplaintFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, RegisterComplaint.class));
                        Toast.makeText(MainActivity.this, "Add Complaint", Toast.LENGTH_SHORT).show();
                    }
                });

        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mLogoutFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAuth.signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
