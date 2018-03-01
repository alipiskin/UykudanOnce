package com.afpsoft.uykudanonce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newStories(View view){

        Intent intent= new Intent(getApplicationContext(),StoryListing.class);
        startActivity(intent);
    }

    public void sendStory(View view){

        Intent intent= new Intent(getApplicationContext(),UploadAcitivity.class);
        startActivity(intent);
    }

    public void loginUser(View view){

        System.out.println("LOGIN BUTONA TIKLANDI");

        Intent intent= new Intent(getApplicationContext(),LoginScreen.class);

        System.out.println("INTENT DEĞİŞKENİ OLUŞTURULDU");

        startActivity(intent);

        System.out.println("STARTACTIVITY ÇALIŞTI");
    }



}
