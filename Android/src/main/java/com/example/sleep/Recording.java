package com.example.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import java.lang.String;

import java.lang.InterruptedException;

import java.net.Socket;



public class Recording extends AppCompatActivity {

    //Socekt programming
    private Socket socket;
    private String ip = "192.168.0.11";            // IP 번호
    private int port = 9998;                          // port 번호
    DataOutputStream dos;

    // AudioRecord Parameter
    private final int PERMISSIONS_RECORD_AUDIO = 1;
    private int mAudioSource = MediaRecorder.AudioSource.MIC;
    private int mSampleRate = 44100;
    private int mChannelCount = AudioFormat.CHANNEL_IN_MONO;
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;

    // 수정한 부분
    private int mBufferSize = 17620;
    //private int mBufferSize = AudioTrack.getMinBufferSize(mSampleRate, mChannelCount, mAudioFormat);

    public AudioRecord mAudioRecord = null;
    public AudioTrack mAudioTrack = null;

    // Record에 필요한 변수
    public Thread mRecordThread = null;
    public boolean isRecording = false;


    // PCM Data가 저장될 위치
    String mFilepath = Environment.getExternalStorageDirectory().getAbsolutePath();
    String mFilename = "/record";
    String mFileext = ".pcm";

    // Widget ID
    public Button BtnRecord = null;


    // 타이머 상수
    // 44100Hz로 Sampling시 17620byte 크기의 버퍼로 가져올시 5번 가져와야 1초 분량의 소리를 가져올 수 있다.
    // 5번 read로 1초이므로 1분은 300번 read해야 한다.
    //public int TimeLimit = 60; //11초
    public int TimeLimit = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        BtnRecord = (Button) findViewById(R.id.BtnRecord);



        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.RECORD_AUDIO},
                    PERMISSIONS_RECORD_AUDIO
            );
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MODE_PRIVATE
            );
        }
        // Recording Ready
        mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannelCount, mAudioFormat, mBufferSize);
        mAudioRecord.startRecording();
    }

    public void onRecord(View view) {
        if(isRecording == true) {
            isRecording = false;
            //BtnRecord.setText("측정 시작");
        }else {
            isRecording = true;
            BtnRecord.setText("측정 종료");

            if(mAudioRecord == null) {
                mAudioRecord = new AudioRecord(mAudioSource, mSampleRate, mChannelCount, mAudioFormat, mBufferSize);
                mAudioRecord.startRecording();
            }

            // PCM Data를 가져오는 부분에서 UI가 멈춰보이지 않도록 하기 위해 Thread로 분리
            mRecordThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Recording(); // Thread는 자신의 run() 메소드가 모두 실행되면 자동적으로 종료된다.
                }
            });
            mRecordThread.start();
        }
    }


    public void Recording() {
        ///////////////////////////////////////Socket 연결//////////////////////////
        try {
            if (socket == null) {
                socket = new Socket(ip,port);
            }
            dos  = new DataOutputStream(socket.getOutputStream());
            Log.w("서버 접속됨", "서버 접속됨");
        } catch (IOException e1) {
            Log.w("서버접속못함", "서버접속못함");
            e1.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////Recording///////////////////////////
        int cnt = 0;
        int f_cnt = 1;
        byte[] readData = new byte[mBufferSize];
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(mFilepath + mFilename + f_cnt + mFileext);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(isRecording) {
            if(cnt != TimeLimit) {
                // AudioRecord의 read 함수를 통해서 PCM Data를 읽음
                int ret = mAudioRecord.read(readData, 0, mBufferSize);

                try {
                    // 읽은 PCM Data를 파일로 저장
                    fos.write(readData, 0, mBufferSize);
                    dos.write(readData);
                }catch (IOException e) {
                    e.printStackTrace();
                }
                cnt++;
            }
            // 수정한 부분
            // Sample Rate에 따라서 BufferSize를 조절하여 시간의 경과를 알아낼 수 있었다
            // 추가로, 30초 동안 새로운 파일을 생성하여 신호 정보를 저장하는 코드 구현
            else{
                cnt = 0;
                f_cnt++;
                try {
                    fos.close();
                    dos.writeUTF("N");
                    fos = new FileOutputStream(mFilepath + mFilename + f_cnt + mFileext);
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mAudioRecord.stop();
        mAudioRecord.release();
        mAudioRecord = null;
        try {
            fos.close();
            dos.close();//이 자식하면 서버의 if not newbuf문으로 들어가게
            /*
            String send_value = "Search";
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(send_value);
             */
            socket.close();
            Intent intent = new Intent(getApplicationContext(), Subactivity.class);
            startActivity(intent);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}