package com.example.qradmin;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    private EditText editTextUserPhoneNumber;
    private Button buttonVerify;

    private EditText editTextPhoneNumber;
    private Button buttonAddPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonAddPhoneNumber = findViewById(R.id.buttonAddPhoneNumber);

        buttonAddPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    // Save phone number to Firebase
                    savePhoneNumberToFirebase(phoneNumber);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void savePhoneNumberToFirebase(String phoneNumber) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        // Assuming the admin has a unique userId or you generate one
        String userId = "unique_user_id"; // Replace with a unique ID for each user

        // Save the phone number under the user's node
        usersRef.child(userId).child("phoneNumber").setValue(phoneNumber);

        Toast.makeText(this, "Phone number added successfully", Toast.LENGTH_SHORT).show();
    }
}
