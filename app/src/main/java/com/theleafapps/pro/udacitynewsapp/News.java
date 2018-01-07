package com.theleafapps.pro.udacitynewsapp;

/**
 * Created by aviator on 06/01/18.
 */

/**
 * An {@link News} object contains information related to a single book.
 */
public class News {

    /**
     * Title of the book
     */
    private String newsTitle;

    /**
     * Publish date of the news article
     */
    private String publishDate;

    /**
     * Web URL for the news article
     */
    private String webUrl;

    /**
     * Section to which the news belongs to
     */
    private String sectionName;

    /**
     * Constructs a new {@link News} object.
     *
     * @param newsTitle   title of the book
     * @param publishDate Publish date of the news article
     * @param webUrl      Web URL for the news article
     * @param sectionName Section to which the news belongs to
     */
    public News(String newsTitle, String publishDate, String webUrl, String sectionName) {
        this.newsTitle = newsTitle;
        this.publishDate = publishDate;
        this.webUrl = webUrl;
        this.sectionName = sectionName;
    }

    /**
     * Getter Method returns News Title
     *
     * @return newsTitle
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * Getter Method returns Publish Date of the News Article
     *
     * @return publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * Getter Method returns URL of the News Article
     *
     * @return webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * Getter Method returns Section Name of the News Article
     *
     * @return sectionName
     */
    public String getSectionName() {
        return sectionName;
    }


    /**
     * News Class toString() method returns string representation of the object
     *
     * @return String
     */
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
