package com.theleafapps.pro.udacitynewsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by aviator on 06/01/18.
 */


/**
 * An {@link NewsAdapter} knows how to create a list item layout for each news
 * in the data source (a list of {@link News} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter} Adapter Object.
     *
     * @param context of the app
     * @param books   is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(@NonNull Context context, List<News> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        //Check if the list item view exists that can be reused,
        //otherwise if convertview is null inflate the new listitem layout
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.news_list_item, parent, false);
        }

        try {

            // Get the current News Object by position
            News currentNews = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID news_title that
            // represents title of the listview element
            TextView news_title = (TextView) listItemView.findViewById(R.id.news_title);

            news_title.setText(currentNews.getNewsTitle());

            // Find the TextView in the list_item.xml layout with the ID news_title that
            // represents title of the listview element
            TextView publish_date = (TextView) listItemView.findViewById(R.id.publish_date);

            // Specifying the date format in which date string is received from json data
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            // Specifying the date format in which date has to be displayed into the listview
            SimpleDateFormat format2 = new SimpleDateFormat("EEE, MMM d");

            // Creating the date object with the date string received from json data
            Date date = format1.parse(currentNews.getPublishDate());

            // Setting the text to the date textview inside the news listview
            publish_date.setText(format2.format(date));

            // Find the TextView in the list_item.xml layout with the ID news_title that
            // represents title of the listview element
            TextView news_section = listItemView.findViewById(R.id.news_section);

            // Setting the text for the news section
            news_section.setText(currentNews.getSectionName());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listItemView;
    }
}
