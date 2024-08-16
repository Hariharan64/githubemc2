package com.example.qradmin;




public class AttendanceRecord {
    private String date;
    private String punchInTime;
    private String punchOutTime;
    private String totalWorkingTime;
    private String status;

    public AttendanceRecord(String date, String punchInTime, String punchOutTime, String totalWorkingTime, String status) {
        this.date = date;
        this.punchInTime = punchInTime;
        this.punchOutTime = punchOutTime;
        this.totalWorkingTime = totalWorkingTime;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public String getPunchInTime() {
        return punchInTime;
    }

    public String getPunchOutTime() {
        return punchOutTime;
    }

    public String getTotalWorkingTime() {
        return totalWorkingTime;
    }

    public String getStatus() {
        return status;
    }
}
