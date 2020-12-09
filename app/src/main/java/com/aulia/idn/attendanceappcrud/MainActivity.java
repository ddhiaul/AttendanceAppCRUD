package com.aulia.idn.attendanceappcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.aulia.idn.attendanceappcrud.R;
import com.aulia.idn.attendanceappcrud.adapter.AttendanceAdapter;
import com.aulia.idn.attendanceappcrud.model.StudentItem;
import com.aulia.idn.attendanceappcrud.remote.APIUtils;
import com.aulia.idn.attendanceappcrud.remote.AttendanceService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnGet;
    ListView rv;
    AttendanceService attendanceService;
    List<StudentItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnGet = findViewById(R.id.btnGet);
        rv = findViewById(R.id.rv);

        attendanceService = APIUtils.getAttendanceService();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        AttendanceActivity.class);
                intent.putExtra("date", "");
                intent.putExtra("name", "");
                intent.putExtra("class", "");
                intent.putExtra("subject", "");
                intent.putExtra("attendance", "");
                startActivity(intent);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserList();
            }
        });

    }

    public void getUserList() {
        Call<List<StudentItem>> call = attendanceService.getAttendance();
        call.enqueue(new Callback<List<StudentItem>>() {
            @Override
            public void onResponse(Call<List<StudentItem>> call, Response<List<StudentItem>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    rv.setAdapter(new AttendanceAdapter(MainActivity.this,
                            R.layout.list_item, list));
                }
            }
            @Override
            public void onFailure(Call<List<StudentItem>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}