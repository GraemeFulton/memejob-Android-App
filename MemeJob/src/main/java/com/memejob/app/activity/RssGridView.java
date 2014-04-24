package com.memejob.app.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.memejob.app.R;
import com.memejob.app.data.RssItem;
import com.memejob.app.listeners.ListListener;
import com.memejob.app.util.RssReader;

import java.util.List;

/**
 * Created by graeme on 19/04/14.
 */
public class RssGridView extends Activity {

    private RssGridView local;

    /**
     * This method creates main application view
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
        setContentView(R.layout.activity_rss_grid);

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute("http://www.careerbuilder.com/RTQ/rss20.aspx?rssid=RSS_PD&num=25&geoip=false&ddcompany=false&ddtitle=false&cat=JN001");

        // Debug the thread name
        Log.d("ITCRssReader", Thread.currentThread().getName());
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("ITCRssReader", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("ITCRssReader", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            GridView rssItems = (GridView) findViewById(R.id.rss_gridview);
            // Instance of ImageAdapter Class
         //   rssItems.setAdapter(new ImageAdapter(RssGridView.this));
            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new RssGridAdapter(local,R.layout.grid_item, result);
            // Set list adapter for the ListView
            rssItems.setAdapter(adapter);

            // Set list view item click listener
            rssItems.setOnItemClickListener(new ListListener(result, local));
        }

    }
}

