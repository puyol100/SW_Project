package com.example.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class alarm extends AppCompatActivity {

    TextView show_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        show_text = (TextView)findViewById(R.id.show_text);
        show_text.setText("최적의 알람 설정은\n");
        show_text.append(sleepytime_managing.get_time());
    }
}
