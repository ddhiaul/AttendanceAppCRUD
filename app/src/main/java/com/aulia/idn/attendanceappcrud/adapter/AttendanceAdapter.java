package com.aulia.idn.attendanceappcrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aulia.idn.attendanceappcrud.AttendanceActivity;
import com.aulia.idn.attendanceappcrud.R;
import com.aulia.idn.attendanceappcrud.model.StudentItem;

import java.util.List;

public class AttendanceAdapter extends ArrayAdapter<StudentItem>  {
    private Context context;
    private List<StudentItem> studentItem;

    public AttendanceAdapter(@NonNull Context context,
                             int resources,
                             @NonNull List<StudentItem> objects) {
        super(context, resources, objects);
        this.context = context;
        this.studentItem = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item,
                parent, false);

        TextView txtIdAttendance = v.findViewById(R.id.txt_attendance_id);
        TextView txtDateAttendance = v.findViewById(R.id.txt_attendance_date);
        TextView txtNameAttendance = v.findViewById(R.id.txt_attendance_name);
        TextView txtClassAttendance = v.findViewById(R.id.txt_attendance_class);
        TextView txtSubjectAttendance = v.findViewById(R.id.txt_attendance_subject);
        TextView txtStudentAttendance = v.findViewById(R.id.txt_attendance_student);

        txtIdAttendance.setText(String.valueOf(studentItem.get(position).getId()));
        txtDateAttendance.setText(String.valueOf(studentItem.get(position).getDate()));
        txtNameAttendance.setText(String.valueOf(studentItem.get(position).getName()));
        txtClassAttendance.setText(String.valueOf(studentItem.get(position).getClass()));
        txtSubjectAttendance.setText(String.valueOf(studentItem.get(position).getSubject()));
        txtStudentAttendance.setText(String.valueOf(studentItem.get(position).getAttendance()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AttendanceActivity.class);
                intent.putExtra("id", String.valueOf(studentItem.get(position).getId()));
                intent.putExtra("date", studentItem.get(position).getName());
                intent.putExtra("name", studentItem.get(position).getName());
                intent.putExtra("class", studentItem.get(position).getClasss());
                intent.putExtra("subject", studentItem.get(position).getSubject());
                intent.putExtra("attendance", studentItem.get(position).getAttendance());
                context.startActivity(intent);
            }
        });

        return v;
    }
}
