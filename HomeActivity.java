package com.example.qradmin;



import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


    public class HomeActivity extends AppCompatActivity {

        private ListView listViewAttendance;
        private DatabaseReference databaseReference;
        private List<AttendanceRecord> attendanceRecords;
        private AttendanceAdapter attendanceAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            // Initialize views
            listViewAttendance = findViewById(R.id.listViewAttendance);

            // Initialize Firebase reference
            databaseReference = FirebaseDatabase.getInstance().getReference("Attendance");

            // Initialize attendance records list
            attendanceRecords = new ArrayList<>();

            // Set adapter for the ListView
            attendanceAdapter = new AttendanceAdapter(this, attendanceRecords);
            listViewAttendance.setAdapter(attendanceAdapter);

            // Load attendance data from Firebase
            fetchAttendanceData();
        }

        private void fetchAttendanceData() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    attendanceRecords.clear();
                    for (DataSnapshot dateSnapshot : snapshot.getChildren()) {
                        String date = dateSnapshot.getKey();
                        String punchInTime = dateSnapshot.child("punchInTime").getValue(String.class);
                        String punchOutTime = dateSnapshot.child("punchOutTime").getValue(String.class);
                        String totalWorkingTime = dateSnapshot.child("totalWorkingTime").getValue(String.class);
                        String status = dateSnapshot.child("status").getValue(String.class); // Retrieve status

                        if (date != null && status != null) {
                            AttendanceRecord record = new AttendanceRecord(date, punchInTime, punchOutTime, totalWorkingTime, status);
                            attendanceRecords.add(record);
                        }
                    }
                    attendanceAdapter.notifyDataSetChanged(); // Refresh the ListView
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(HomeActivity.this, "Error loading attendance data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }