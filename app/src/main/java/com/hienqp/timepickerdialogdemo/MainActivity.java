package com.hienqp.timepickerdialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textViewTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = (TextView) findViewById(R.id.textview_time);
        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTimeDialog();
            }
        });
    }

    private void pickTimeDialog() {
        // Sử dụng Calendar
        calendar = Calendar.getInstance();
        // giờ và phút mặc định sẽ hiển thị khi TimePickerDialog được gọi lần đầu tiên
        int defaultHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int defaultMinute = calendar.get(Calendar.MINUTE);
        // năm, tháng, ngày mặc định dùng để set dữ liệu cho Calendar (Calendar cần 3 tham số này khi set dữ liệu)
        int defaultYear = calendar.get(Calendar.YEAR);
        int defaultMonth = calendar.get(Calendar.MONTH) + 1;
        int defaultDate = calendar.get(Calendar.DAY_OF_MONTH);

        // construct 1 đối tượng TimePickerDialog với các tham số
        // Context:
        // phương thức nặc danh bắt sự kiện user chọn thời gian new TimePickerDialog.OnTimeSetListener()
        // defaultHourOfDay: giờ mặc định của Calendar
        // defaultMinute: phút mặc định của Calendar
        // giá trị boolean is24HourView (chọn View 24h hay không)
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // chọn mẫu định dạng cho hiển thị thời gian
                SimpleDateFormat formattedTime = new SimpleDateFormat("HH:mm:ss");

                // set thời gian cho Calendar trong đó ngoài năm, tháng, ngày mặc định cần thiết
                // ta còn truyền thêm giá trị hourOfDay và minute do user đã chọn
                calendar.set(defaultYear,
                        defaultMonth,
                        defaultDate,
                        hourOfDay,
                        minute);
                // setText cho TextView, dữ liệu được lấy từ phương thức format() thông qua đối tượng của SimpleDateFormat
                textViewTime.setText(formattedTime.format(calendar.getTime()) + "\n");
                textViewTime.append(calendar.get(Calendar.YEAR) + "\n");
                textViewTime.append(calendar.get(Calendar.MONTH) + "\n");
                textViewTime.append(calendar.get(Calendar.DATE) + "\n");

            }
        }, defaultHourOfDay, defaultMinute, true);
        
        // phải gọi show() thì TimePickerDialog mới có thể hiển thị
        timePickerDialog.show();
    }
}