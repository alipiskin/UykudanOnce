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

    private final ArrayList<String> storyTitle;
    private final ArrayList<String> storyBody;
    private final ArrayList<String> storyImage;
    private final Activity context;


    public PostClass(ArrayList<String> storyTitle, ArrayList<String> storyBody, ArrayList<String> storyImage, Activity context) {
        super(context,R.layout.custom_view, storyTitle);
        this.storyTitle = storyTitle;
        this.storyBody = storyBody;
        this.storyImage = storyImage;
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
        commentText.setText(storyBody.get(position).substring(0,5));
        Picasso.with(context).load(storyImage.get(position)).into(imageView);

        return customView;
    }
}
