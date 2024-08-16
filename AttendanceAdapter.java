package com.example.qradmin;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AttendanceAdapter extends BaseAdapter {

    private Context context;
    private List<AttendanceRecord> attendanceRecords;

    public AttendanceAdapter(Context context, List<AttendanceRecord> attendanceRecords) {
        this.context = context;
        this.attendanceRecords = attendanceRecords;
    }

    @Override
    public int getCount() {
        return attendanceRecords.size();
    }

    @Override
    public Object getItem(int position) {
        return attendanceRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.attendance_item, parent, false);
        }

        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView punchInTextView = convertView.findViewById(R.id.punchInTextView);
        TextView punchOutTextView = convertView.findViewById(R.id.punchOutTextView);
        TextView totalWorkingTimeTextView = convertView.findViewById(R.id.totalWorkingTimeTextView);
        TextView statusTextView = convertView.findViewById(R.id.statusTextView); // New TextView for status

        AttendanceRecord record = attendanceRecords.get(position);
        dateTextView.setText(record.getDate());
        punchInTextView.setText("Punch In: " + (record.getPunchInTime() != null ? record.getPunchInTime() : "N/A"));
        punchOutTextView.setText("Punch Out: " + (record.getPunchOutTime() != null ? record.getPunchOutTime() : "N/A"));
        totalWorkingTimeTextView.setText("Total Working Time: " + (record.getTotalWorkingTime() != null ? record.getTotalWorkingTime() : "N/A"));
        statusTextView.setText("Status: " + record.getStatus()); // Display punch status

        return convertView;
    }
}
