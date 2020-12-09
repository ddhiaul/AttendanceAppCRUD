package com.aulia.idn.attendanceappcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aulia.idn.attendanceappcrud.R;
import com.aulia.idn.attendanceappcrud.model.StudentItem;
import com.aulia.idn.attendanceappcrud.remote.APIUtils;
import com.aulia.idn.attendanceappcrud.remote.AttendanceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {

    AttendanceService attendanceService;
    EditText edtDate, edtName, edtClass, edtSubject, edtAttendance, edtId;
    Button btnSave, btnDel;
    TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtDate = findViewById(R.id.edt_date);
        edtName = findViewById(R.id.edt_name);
        edtClass = findViewById(R.id.edt_class);
        edtSubject = findViewById(R.id.edt_subject);
        edtAttendance = findViewById(R.id.edt_attendance);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_delete);
        edtId = findViewById(R.id.edt_id);
        txtId = findViewById(R.id.txt_id);

        attendanceService = APIUtils.getAttendanceService();

        Bundle extras = getIntent().getExtras();
        String attendanceDate = extras.getString("date");
        String attendanceName = extras.getString("name");
        String attendanceClass = extras.getString("class");
        String attendanceSubject = extras.getString("subject");
        String attendanceAttendance = extras.getString("attendance");
        final String attendanceID = extras.getString("id");

        edtId.setText(attendanceID);
        edtDate.setText(attendanceDate);
        edtName.setText(attendanceName);
        edtClass.setText(attendanceClass);
        edtSubject.setText(attendanceSubject);
        edtAttendance.setText(attendanceAttendance);

        if (attendanceID != null && attendanceID.trim().length() > 0){
            edtId.setFocusable(false);
        } else {
            txtId.setVisibility(View.INVISIBLE);
            edtId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = edtDate.getText().toString();
                String name = edtName.getText().toString();
                String classs = edtClass.getText().toString();
                String subject = edtSubject.getText().toString();
                String attendance = edtAttendance.getText().toString();

                if (attendanceID != null && attendanceID.trim().length() > 0){
                    updateAttendance(Integer.parseInt(attendanceID), date, name, classs, subject, attendance);
                } else {
                    addAttendance(date, name, classs, subject, attendance);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAttendance(Integer.parseInt(attendanceID));
                Intent intent = new Intent(AttendanceActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

//        @Override
//        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()){
//                case android.R.id.home:
//                    finish();
//                    return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
    }

    private void deleteAttendance(int id) {
        Call<StudentItem> call = attendanceService.deleteAttendance(id);
        call.enqueue(new Callback<StudentItem>() {
            @Override
            public void onResponse(Call<StudentItem> call, Response<StudentItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AttendanceActivity.this, "Attendance deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AttendanceActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<StudentItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void addAttendance(String date, String name, String classs, String subject, String attendance) {
        Call<StudentItem> call = attendanceService.addAttendance(date, name, classs, subject, attendance);
        call.enqueue(new Callback<StudentItem>() {
            @Override
            public void onResponse(Call<StudentItem> call, Response<StudentItem> response) {
                Toast.makeText(AttendanceActivity.this, "attendance added succesfully",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttendanceActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<StudentItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void updateAttendance(int id, String date, String name, String classs, String subject, String attendance) {
        Call<StudentItem> call = attendanceService.updateAttendance(id, date, name, classs, subject, attendance);
        call.enqueue(new Callback<StudentItem>() {
            @Override
            public void onResponse(Call<StudentItem> call, Response<StudentItem> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AttendanceActivity.this, "Attendance Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AttendanceActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<StudentItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}