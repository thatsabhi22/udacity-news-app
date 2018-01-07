package com.theleafapps.pro.udacitynewsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    /**
     * URL for a Guardian API JSON Response
     */

    private static final String NEWS_LIST_API_URL = "https://content.guardianapis.com/search";
    /**
     * Constant value for the booklist loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LIST_LOADER_ID = 1;

    /**
     * ConnectivityManager Class variable to check network status
     */
    ConnectivityManager connectivityManager;

    /**
     * Textview to be displayed when the listview have no data
     */
    TextView emptyView;

    /**
     * Adapter providing data to the news listview
     */
    NewsAdapter newsAdapter;

    /**
     * Progressbar variable which is circular spinner (loading indicator)
     */
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Get the instance of Connectivity Service
        connectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Find reference to the progressbar, the loading spinner
        loadingIndicator = findViewById(R.id.news_list_loading_spinner);

        //Get the LoaderManager object
        LoaderManager loaderManager = getLoaderManager();

        //Initialize the loaderManager Object with its unique ID
        loaderManager.initLoader(NEWS_LIST_LOADER_ID, null, this);

        //Creating an empty ArrayList of type News class
        final List<News> news = new ArrayList<>();

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.news_list_listview);

        // Find a reference to the emptyView TextView
        emptyView = (TextView) findViewById(R.id.emptyView);

        //Set the emptyView for the booklistview
        newsListView.setEmptyView(emptyView);

        // Create a new {@link ArrayAdapter} of books
        newsAdapter = new NewsAdapter(this, news);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(newsAdapter);

        //Setting an OnItemClickListener to newsListView(main listview)
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the url from the corresponding book object into url variable
                String url = news.get(position).getWebUrl();

                //create an implicit intent to open the url in the browser
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                //start the activity
                startActivity(implicit);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(NEWS_LIST_API_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", getResources().getString(R.string.news_topic));
        uriBuilder.appendQueryParameter("api-key", getResources().getString(R.string.api_key));

        // Create a new loader for the given URL
        return new NewsListLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {

        // Hide loading indicator because the data has been loaded
        hideProgressBar();

        // Clear the adapter of previous booklist data
        newsAdapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsList != null && !newsList.isEmpty()) {
            newsAdapter.addAll(newsList);
        } else {
            emptyView.setText(this.getString(R.string.no_news_data_found));
        }

        //Setting text for the emptyView TextView as onLoadFinished method is called
        //Check is the internet is connected or not
        if (!QueryUtils.isNetworkConnected(connectivityManager)) {
            emptyView.setText(this.getString(R.string.no_internet_connection));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Clear the adapter of previous booklist data
        newsAdapter.clear();
    }

    /**
     * Sets visibility of the progressbar ON
     */
    public void showProgressBar() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    /**
     * Sets visibility of the progressbar OFF
     */
    public void hideProgressBar() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_news) {

            //Clear the News Adapter to show fresh content
            newsAdapter.clear();

            //Showing progressbar as refresh button is clicked
            showProgressBar();

            //Restarting the loader object as the refresh is clicked,
            //fresh content will be fetched from internet
            getLoaderManager().restartLoader(NEWS_LIST_LOADER_ID, null, NewsActivity.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
