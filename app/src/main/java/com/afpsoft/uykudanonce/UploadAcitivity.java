package com.afpsoft.uykudanonce;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class UploadAcitivity extends AppCompatActivity {

    EditText storyTitleText;
    EditText storyBodyText;
    ImageView imageView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageReference mStorageRef;
    Uri selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_acitivity);

        storyTitleText = findViewById(R.id.storyTitle);
        storyBodyText= findViewById(R.id.storyBody);
        imageView=findViewById(R.id.imageView2);
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();
        mStorageRef= FirebaseStorage.getInstance().getReference();


    }


    public void  upload (View view){

        UUID uuidImage= UUID.randomUUID();

        String imageName="images/"+uuidImage+".jpg";
        StorageReference storageReference =mStorageRef.child(imageName);
        storageReference.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String downloadURL= taskSnapshot.getDownloadUrl().toString();
                FirebaseUser user= mAuth.getCurrentUser();
                String userEmail=user.getEmail().toString();
                String storyTitle=storyTitleText.getText().toString();
                String storyBody=storyBodyText.getText().toString();
                Date date = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
                String dateAddedToID = ft.format(date);

                UUID uuid=UUID.randomUUID();
                String uuidString= uuid.toString()+dateAddedToID;
                System.out.println(uuidString);
                myRef.child("Stories").child(uuidString).child("id").setValue(uuidString);
                myRef.child("Stories").child(uuidString).child("sentby").setValue(userEmail);
                myRef.child("Stories").child(uuidString).child("storyTitle").setValue(storyTitle);
                myRef.child("Stories").child(uuidString).child("storyBody").setValue(storyBody);
                myRef.child("Stories").child(uuidString).child("downloadurl").setValue(downloadURL);
                myRef.child("Stories").child(uuidString).child("date").setValue(date.toString());
                myRef.child("Stories").child(uuidString).child("type").setValue("1");
                myRef.child("Stories").child(uuidString).child("isFree").setValue("1");
                myRef.child("Stories").child(uuidString).child("hit").setValue("1");
                myRef.child("Stories").child(uuidString).child("isVisible").setValue("1");
                myRef.child("Stories").child(uuidString).child("likeCount").setValue("0");
                myRef.child("Stories").child(uuidString).child("disLikeCount").setValue("0");
                myRef.child("Stories").child(uuidString).child("author").setValue("Anonymous Artist");


                Toast.makeText(getApplicationContext(),"Öykünüz İletildi.",Toast.LENGTH_LONG).show();;

                Intent intent= new Intent(getApplicationContext(),StoryListing.class);
                startActivity(intent);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    public void selectImage(View view){

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);

        } else {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode== 1) {
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);

            }

        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==2 && resultCode== RESULT_OK && data != null){

            selected= data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selected);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
