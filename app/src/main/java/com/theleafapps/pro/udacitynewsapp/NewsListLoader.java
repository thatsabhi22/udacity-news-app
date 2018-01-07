package com.theleafapps.pro.udacitynewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by aviator on 06/01/18.
 */

public class NewsListLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = NewsListLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Create a new object of the NewsListLoader
     *
     * @param context The context of the application
     * @param url     url for fetching content of the news
     */
    public NewsListLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Constructor with only context as a parameter
     *
     * @param context The context of the application
     */
    public NewsListLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<News> newsList = QueryUtils.fetchNewsListData(mUrl);
        return newsList;
    }
}
