package com.afpsoft.uykudanonce;

/**
 * Created by alipi on 14.03.2018.
 */

public class StoryClass {

    private  String id;
    private  String title;
    private  String body;
    private  String image;
    private  String sentBy;
    private  Integer hit;
    private  String date;
    private  String type;
    private  String isFree;
    private  String isVisible;
    private  Integer likeCount;
    private  Integer disLikeCount;
    private  String author;
    private  String tags;

    public StoryClass(String id, String title, String body, String image, String sentBy, Integer hit, String date, String type, String isFree, String isVisible, Integer likeCount, Integer disLikeCount, String author, String tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.image = image;
        this.sentBy = sentBy;
        this.hit = hit;
        this.date = date;
        this.type = type;
        this.isFree = isFree;
        this.isVisible = isVisible;
        this.likeCount = likeCount;
        this.disLikeCount = disLikeCount;
        this.author = author;
        this.tags = tags;
    }

    public StoryClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDisLikeCount() {
        return disLikeCount;
    }

    public void setDisLikeCount(Integer disLikeCount) {
        this.disLikeCount = disLikeCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
