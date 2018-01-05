package com.theleafapps.pro.udacitynewsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {

    SwipeRefreshLayout swipe;

    /**
     * ConnectivityManager Class variable to check network status
     */
    ConnectivityManager connectivityManager;

    /**
     * Textview to be displayed when the listview have no data
     */
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Get the instance of Connectivity Service
        connectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        swipe = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                try {
                    Toast.makeText(NewsActivity.this, "Refresh the things", Toast.LENGTH_LONG).show();
                    //Thread.sleep(2000);
                    swipe.setRefreshing(false);
//                }catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}
