package com.afpsoft.uykudanonce;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alipi on 26.02.2018.
 */

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> storyId;
    private final ArrayList<String> storyTitle;
    private final ArrayList<String> storyBody;
    private final ArrayList<String> storyImage;
    private final ArrayList<String> storySentBy;
    private final ArrayList<String> storyHit;
    private final ArrayList<String> storyDate;
    private final ArrayList<String> storyType;
    private final ArrayList<String> storyIsFree;
    private final ArrayList<String> storyIsVisible;
    private final ArrayList<Integer> storyLikeCount;
    private final ArrayList<Integer> storyDisLikeCount;
    private final ArrayList<String> storyAuthor;
    private final ArrayList<String> storyTags;

    private final Activity context;


    public PostClass(ArrayList<String> storyTitle, ArrayList<String> storyBody, ArrayList<String> storyImage,ArrayList<String> storySentBy, ArrayList<String> storyHit,ArrayList<String> storyDate,ArrayList<String> storyType,ArrayList<String> storyIsFree,ArrayList<String> storyIsVisible,ArrayList<String> storyId,ArrayList<Integer> storyLikeCount,ArrayList<Integer> storyDisLikeCount,ArrayList<String> storyAuthor,ArrayList<String> storyTags, Activity context) {
        super(context,R.layout.custom_view, storyTitle);
        this.storyTitle = storyTitle;
        this.storyBody = storyBody;
        this.storyImage = storyImage;
        this.storySentBy = storySentBy;
        this.storyHit = storyHit;
        this.storyDate = storyDate;
        this.storyType = storyType;
        this.storyIsFree = storyIsFree;
        this.storyIsVisible = storyIsVisible;
        this.storyId = storyId;
        this.storyLikeCount = storyLikeCount;
        this.storyDisLikeCount = storyDisLikeCount;
        this.storyAuthor = storyAuthor;
        this.storyTags = storyTags;

        this.context = context;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView useremailText = (TextView) customView.findViewById(R.id.storyTitleText);
        TextView commentText = (TextView) customView.findViewById(R.id.storyBodyText);
        ImageView imageView = (ImageView) customView.findViewById(R.id.storyImageView);

        useremailText.setText(storyTitle.get(position));
        if (storyBody.get(position).length() > 80) {
                commentText.setText(storyBody.get(position).substring(0,80));

        }else {
            commentText.setText(storyBody.get(position));

        }


        Picasso.with(context).load(storyImage.get(position)).into(imageView);

        return customView;
    }
}
