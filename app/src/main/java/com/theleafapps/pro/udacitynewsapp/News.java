package com.theleafapps.pro.udacitynewsapp;

/**
 * Created by aviator on 06/01/18.
 */

public class News {

    private String newsTitle;
    private String publishDate;
    private String webUrl;
    private String sectionName;

    public News(String newsTitle, String publishDate, String webUrl, String sectionName) {
        this.newsTitle = newsTitle;
        this.publishDate = publishDate;
        this.webUrl = webUrl;
        this.sectionName = sectionName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsTitle='" + newsTitle + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", sectionName='" + sectionName + '\'' +
                '}';
    }
}
