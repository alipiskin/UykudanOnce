package com.afpsoft.uykudanonce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class StoryDetailActivity extends AppCompatActivity {

    String info;
    String title;
    String body;
    String storyid;
    String image;
    Integer hit;
    Integer likeCount;
    Integer dislikeCount;
    Integer storyHitFromFB;
    Integer storyLikeCountFromFB;
    Integer storyDislikeCountFromFB;
    StoryClass storyinstance;
    Boolean isHit=false;
    Boolean isLike=false;
    Boolean isDislike=false;
    TextView storyHitText;
    TextView storyLikeCountText;
    TextView storyDislikecountText;



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

        hit = intent.getIntExtra("hit",0);
        likeCount = intent.getIntExtra("likeCount",0);
        dislikeCount = intent.getIntExtra("dislikeCount",0);


        TextView storyTitleText = findViewById(R.id.textViewTitle);
        TextView storyBodyText = findViewById(R.id.textViewBody);
        // TextView storyIDText = findViewById(R.id.textViewID);
        storyHitText = findViewById(R.id.textViewHit);
        storyLikeCountText = findViewById(R.id.textViewLike);
        storyDislikecountText = findViewById(R.id.textViewDislike);
        storyBodyText.setMovementMethod(new ScrollingMovementMethod());
        ImageView imageView = findViewById(R.id.imageViewThumb);

        System.out.println("@@@@@ B TITLE:" + title);
        System.out.println("@@@@@ B BODY" + body);
        System.out.println("@@@@@ B IMAGE URL" + image);
        System.out.println("@@@@@ B ID " + storyid);
        //System.out.println("@@@@@ B HIT " + hit);

        storyTitleText.setText(title);
        storyBodyText.setText(body);
        storyBodyText.setText(body);
        // storyIDText.setText(storyid);
        storyHitText.setText("Görüntülenme: "+hit.toString());
        storyLikeCountText.setText(likeCount.toString());
        storyDislikecountText.setText(dislikeCount.toString());


        Picasso.with(this).load(image).into(imageView);

        System.out.println("@@@@@ ON CREATE ICINDE, INCREASE HIT ONCESI ");

        IncreaseHit();

    }

    public void IncreaseHit() {


        FirebaseDatabase firebaseDatabase;
        final DatabaseReference newReference;


        firebaseDatabase= FirebaseDatabase.getInstance();
        newReference = firebaseDatabase.getReference("Stories/"+ storyid);

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                storyinstance=  dataSnapshot.getValue(StoryClass.class);

                storyHitFromFB = storyinstance.getHit();
                storyHitFromFB++;
                storyinstance.setHit(storyHitFromFB);
                if (isHit==false) {
                    newReference.setValue(storyinstance);
                    storyHitText.setText("Görüntülenme: "+storyHitFromFB.toString());
                    isHit=true;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println("DATA READ PROBLEM !!::!!");
            }
        });


    }


    public void likeStory(View view){
        Toast.makeText(this, "LIKE CLICKED", Toast.LENGTH_SHORT).show();

        FirebaseDatabase firebaseDatabase;
        final DatabaseReference newReference;

        firebaseDatabase= FirebaseDatabase.getInstance();
        newReference = firebaseDatabase.getReference("Stories/"+ storyid);

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                storyinstance=  dataSnapshot.getValue(StoryClass.class);

                storyLikeCountFromFB = storyinstance.getLikeCount();
                storyLikeCountFromFB++;
                storyinstance.setLikeCount(storyLikeCountFromFB);
                if (isLike==false) {
                    newReference.setValue(storyinstance);
                    storyLikeCountText.setText(storyLikeCountFromFB.toString());
                    isLike=true;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println("DATA READ PROBLEM !!::!!");
            }
        });


    }

    public void dislikeStory(View view) {
        Toast.makeText(this, "DISLIKE CLICKED", Toast.LENGTH_SHORT).show();

        FirebaseDatabase firebaseDatabase;
        final DatabaseReference newReference;

        firebaseDatabase= FirebaseDatabase.getInstance();
        newReference = firebaseDatabase.getReference("Stories/"+ storyid);

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                storyinstance=  dataSnapshot.getValue(StoryClass.class);

                storyDislikeCountFromFB = storyinstance.getLikeCount();
                storyDislikeCountFromFB++;
                storyinstance.setDisLikeCount(storyDislikeCountFromFB);
                if (isDislike==false) {
                    newReference.setValue(storyinstance);
                    storyDislikecountText.setText(storyDislikeCountFromFB.toString());
                    isDislike=true;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println("DATA READ PROBLEM !!::!!");
            }
        });


    }

    public void addToFav(View view){

        Toast.makeText(this, "FAV CLICKED", Toast.LENGTH_SHORT).show();

    }


    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent= new Intent(getApplicationContext(), StoryListing.class);
        startActivity(setIntent);

        return;
    }
}
