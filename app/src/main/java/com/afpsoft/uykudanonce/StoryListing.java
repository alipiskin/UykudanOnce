package com.afpsoft.uykudanonce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alipi on 27.02.2018.
 */

public class StoryListing extends AppCompatActivity {

    ArrayList<String> useremailsFromFB;
    ArrayList<String>  storyImageFromFB;
    ArrayList<String> storyTitleFromFB;
    ArrayList<String> storyBodyFromFB;
    ArrayList<String> storyIdFromFB;
    ArrayList<Integer> storyHitFromFB;
    ArrayList<String> storyTypeFromFB;
    ArrayList<String> storyDateFromFB;
    ArrayList<String> storyIsFreeFromFB;
    ArrayList<String> storySentByFromFB;
    ArrayList<String> storyIsVisibleFromFB;
    ArrayList<Integer> storyLikeCountFromFB;
    ArrayList<Integer> storyDisLikeCountFromFB;
    ArrayList<String> storyAuthorFromFB;
    ArrayList<String> storyTagsFromFB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    PostClass adapter;
    ListView listView;





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.oyku_listele, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.new_stories){

            Intent intent= new Intent(getApplicationContext(),StoryListing.class);
            intent.putExtra("info","newstories");
            startActivity(intent);

        }else if (item.getItemId()==R.id.most_read) {

            Intent intent= new Intent(getApplicationContext(),StoryListing.class);
            intent.putExtra("info","mostread");
            startActivity(intent);

        } else if (item.getItemId()==R.id.send_story) {

            Intent intent= new Intent(getApplicationContext(),UploadAcitivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storylisting);


        Intent intent= getIntent();
        String info=intent.getStringExtra("info");

        if (info==null) {
            info = "newstories"; // In case of null transfer value of variable
        };

        if (info.equalsIgnoreCase("newstories")){


        } else if (info.equalsIgnoreCase("mostread")) {


        }

        //useremailsFromFB = new ArrayList<String>();
        storyTitleFromFB=  new ArrayList<String>();
        storyBodyFromFB=  new ArrayList<String>();
        storyImageFromFB= new ArrayList<String>();
        storyIdFromFB= new ArrayList<String>();
        storyHitFromFB= new ArrayList<Integer>();
        storyTypeFromFB= new ArrayList<String>();
        storyDateFromFB= new ArrayList<String>();
        storyIsFreeFromFB= new ArrayList<String>();
        storySentByFromFB= new ArrayList<String>();
        storyIsVisibleFromFB= new ArrayList<String>();
        storyLikeCountFromFB= new ArrayList<Integer>();
        storyDisLikeCountFromFB= new ArrayList<Integer>();
        storyAuthorFromFB= new ArrayList<String>();
        storyTagsFromFB= new ArrayList<String>();

        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        myRef=firebaseDatabase.getReference();

        adapter = new PostClass(storyTitleFromFB,storyBodyFromFB,storyImageFromFB,storySentByFromFB,storyHitFromFB,storyDateFromFB,storyTypeFromFB,storyIsFreeFromFB, storyIsVisibleFromFB, storyIdFromFB,storyLikeCountFromFB,storyDisLikeCountFromFB,storyAuthorFromFB,storyTagsFromFB,   this);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        getDataFromFirebase();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    System.out.println("BLOGA GİRDİ");

                    System.out.println("@@@@@ 1 TITLE:" + storyTitleFromFB);
                    System.out.println("@@@@@ 1 BODY" + storyBodyFromFB);
                    System.out.println("@@@@@ 1 IMAGE URL" + storyImageFromFB);
                    System.out.println("@@@@@ 1 ID " + storyIdFromFB);
                    System.out.println("@@@@@ 1 HIT" + storyHitFromFB);

                    Intent intent= new Intent(getApplicationContext(), StoryDetailActivity.class);
                    // intent.putExtra("info","old");
                    System.out.println("DURUM 1");
                    intent.putExtra("title",storyTitleFromFB.get(i));
                    System.out.println("@@@@@ 2 TITLE:" + storyTitleFromFB.get(i));

                    intent.putExtra("body",storyBodyFromFB.get(i));
                    System.out.println("@@@@@ 2 BODY" + storyBodyFromFB.get(i));

                    intent.putExtra("image",storyImageFromFB.get(i));
                    System.out.println("@@@@@ 2 IMAGE URL" + storyImageFromFB.get(i));

                    intent.putExtra("id",storyIdFromFB.get(i));
                    System.out.println("@@@@@ 2 ID " + storyIdFromFB.get(i));

                    intent.putExtra("hit",storyHitFromFB.get(i));
                    System.out.println("@@@@@ 2 HIT " + storyHitFromFB.get(i));

                    intent.putExtra("likeCount",storyLikeCountFromFB.get(i));
                    System.out.println("@@@@@ 2 LIKE " + storyLikeCountFromFB.get(i));

                    intent.putExtra("dislikeCount",storyLikeCountFromFB.get(i));
                    System.out.println("@@@@@ 2 DISLIKE " + storyDisLikeCountFromFB.get(i));


                    //intent.putExtra("hit",String.valueOf(storyHitFromFB.get(i)));
                    //System.out.println("@@@@@ 2 HIT" + String.valueOf(storyHitFromFB.get(i)));

                    intent.putExtra("position",i);
                    System.out.println("DURUM 2");


                    startActivity(intent);
                    System.out.println("DURUM 3");
                } catch (Exception e) {
                    System.out.println("DURUM 4");
                    e.printStackTrace();
                }


            }
        });

    }

    protected void getDataFromFirebase() {

        System.out.println("##### CLEAR ARRAYS #####");
        storyTitleFromFB.clear();
        storyBodyFromFB.clear();
        storyImageFromFB.clear();
        storyIdFromFB.clear();
        storyHitFromFB.clear();
        storyLikeCountFromFB.clear();
        storyDisLikeCountFromFB.clear();
        System.out.println("##### ARRAYS CLEARED !! #####");

        DatabaseReference newReference= firebaseDatabase.getReference("Stories");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //System.out.println("children"+ dataSnapshot.getChildren());
                //System.out.println("key"+ dataSnapshot.getKey());
                //System.out.println("value" + dataSnapshot.getValue());

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    HashMap<String,Object> hasMap= (HashMap<String,Object>) ds.getValue();
                    storyTitleFromFB.add(hasMap.get("title").toString());
                    storyBodyFromFB.add(hasMap.get("body").toString());
                    storyImageFromFB.add(hasMap.get("image").toString());
                    storyIdFromFB.add(hasMap.get("id").toString());
                    storyHitFromFB.add(Integer.valueOf(hasMap.get("hit").toString()));
                    storyLikeCountFromFB.add(Integer.valueOf(hasMap.get("likeCount").toString()));
                    storyDisLikeCountFromFB.add(Integer.valueOf(hasMap.get("disLikeCount").toString()));

                    System.out.println("storyHitFromFB " + hasMap.get("hit") );


                    adapter.notifyDataSetChanged();

                    //System.out.println("useremailsFromFB" + storyTitleFromFB);
                    //System.out.println("userImageFromFB" + storyBodyFromFB);
                    //System.out.println("usercommentFromFB" + storyImageFromFB);


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(setIntent);

        return;
    }





}
