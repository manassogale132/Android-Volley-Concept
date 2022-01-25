package com.example.volleysecondexample;

public class Articles {

    public String newsAuther;
    public String newsImage;
    public String newsTitle;
    public String newsDescription;
    public String newsSource;
    public String newsContent;
    public String name;

    public Articles(String newsAuther, String newsImage, String newsTitle, String newsDescription, String newsSource, String newsContent, String name) {
        this.newsAuther = newsAuther;
        this.newsImage = newsImage;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsSource = newsSource;
        this.newsContent = newsContent;
        this.name = name;
    }

    public String getNewsAuther() {
        return newsAuther;
    }

    public void setNewsAuther(String newsAuther) {
        this.newsAuther = newsAuther;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
