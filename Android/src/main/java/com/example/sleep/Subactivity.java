package com.example.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.content.Intent;


public class Subactivity extends AppCompatActivity{
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);
        btn1 = (Button)findViewById(R.id.Sleep_apnea_Button);
        btn2 = (Button)findViewById(R.id.Record_Button);
        btn3 = (Button)findViewById(R.id.logoutbutton);
        btn4 = (Button)findViewById(R.id.psqi_Button);
        btn5 = (Button)findViewById(R.id.sleepytime_Button);
        btn6 = (Button)findViewById(R.id.graph_Button);
        btn7 = (Button)findViewById(R.id.alarm);

        this.SetListener();
    }
    public void SetListener()
    {
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),Check_Apnea.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),Recording.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                SaveSharedPreference.clearUserName(Subactivity.this);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),psqi_main.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),sleepytime.class);
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),gettingphoto.class);
                SaveSharedPreference.clearUserName(Subactivity.this);
                startActivity(intent);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),alarm.class);
                SaveSharedPreference.clearUserName(Subactivity.this);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}