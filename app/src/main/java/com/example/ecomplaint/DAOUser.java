package com.example.ecomplaint;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUser {
    private DatabaseReference databaseReference;
    public DAOUser()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference=db.getReference(User.class.getSimpleName());
    }
    public Task<Void> add(User u)
    {
        if (u == null) //throw exception
        return databaseReference.push().setValue(emp);
    }
}
