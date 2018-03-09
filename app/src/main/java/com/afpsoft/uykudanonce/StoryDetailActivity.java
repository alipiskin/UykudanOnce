package com.afpsoft.uykudanonce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class StoryDetailActivity extends AppCompatActivity {

    String info;
    String title;
    String body;
    String storyid;
    String image;
    String storyHitFromFB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        Intent intent = getIntent();

        info = intent.getStringExtra("info");
        title = intent.getStringExtra("title");
        body = intent.getStringExtra("body");
        storyid = intent.getStringExtra("id");
        image = intent.getStringExtra("image");

        TextView storyTitleText = findViewById(R.id.textView);
        TextView storyBodyText = findViewById(R.id.textView2);
        TextView storyIDText = findViewById(R.id.textView3);
        storyBodyText.setMovementMethod(new ScrollingMovementMethod());
        ImageView imageView = findViewById(R.id.imageView);

        System.out.println("@@@@@ TITLE:" + title);
        System.out.println("@@@@@ BODY" + body);
        System.out.println("@@@@@ IMAGE URL" + image);
        System.out.println("@@@@@ ID URL" + storyid);

        storyTitleText.setText(title);
        storyBodyText.setText(body);
        storyBodyText.setText(body);
        storyIDText.setText(storyid);

        Picasso.with(this).load(image).into(imageView);

        System.out.println("@@@@@ ON CREATE ICINDE, INCREASE HIT ONCESI ");

        IncreaseHit();


    }

    public void IncreaseHit() {

        System.out.println("@@@@@ INCREASE HIT İÇİ ");

        FirebaseDatabase firebaseDatabase;
        DatabaseReference newReference;


        firebaseDatabase= FirebaseDatabase.getInstance();
        newReference = firebaseDatabase.getReference("Stories");

        newReference.child(storyid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("@@@@@ onDataChange HIT İÇİ ");

                System.out.println("@@@@@ 0" + dataSnapshot.getValue());

                HashMap<String,String> hasMap= (HashMap<String, String>) dataSnapshot.getValue();
                storyHitFromFB= hasMap.get("hit");

                System.out.println("@@@@@ 1  !!::!! =  " + storyHitFromFB);

                Integer storyHitFromFBtoINT = Integer.valueOf(storyHitFromFB);

                System.out.println("@@@@@ 2   !!::!! =  " + storyHitFromFB);

                storyHitFromFBtoINT++;

                System.out.println("@@@@@ 3   !!::!! =  " + storyHitFromFB);

                storyHitFromFB= Integer.toString(storyHitFromFBtoINT);

                System.out.println("@@@@@ 4   !!::!! =  " + storyHitFromFB);






                // User user = dataSnapshot.getValue(User.class);

                // Log.d(TAG, "User name: " + user.getName() + ", email " + user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println("DATA READ PROBLEM !!::!!");
            }
        });

        System.out.println("@@@@@ NEW HIT SETLENIYOR !!::!! =  " + storyHitFromFB);

        newReference.child(storyid).child("hit").setValue(storyHitFromFB);

        System.out.println("@@@@@ NEW HIT SETLENDI !!::!! =  " + storyHitFromFB);

        // myRef.child(userId).child("name").setValue(name);
        // myRef.child("Stories").child(storyid).child("hit").setValue(storyHitFromFB);


    }
}
