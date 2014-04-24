package com.memejob.app.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.memejob.app.R;
import com.memejob.app.data.RssItem;
import com.memejob.app.imageLoader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graeme on 20/04/14.
 */
public class RssGridAdapter  extends ArrayAdapter<RssItem> {

    private Context context;
    private ImageView image;
    private TextView description;
    private TextView location;
    private TextView source;
    private TextView date;
    private TextView category;

    private List<RssItem> items = new ArrayList<RssItem>();

    public ImageLoader imageLoader;


    /**
     * AlternateRowAdapter
     * @param context
     * @param layoutViewResourceId
     * @param objects
     */
    public RssGridAdapter(Context context, int layoutViewResourceId, List<RssItem> objects) {
        super(context, layoutViewResourceId, objects);
        this.context = context;
        this.items = objects;
        this.imageLoader = new ImageLoader(context);
    }


    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public RssItem getItem(int position) {
        return this.items.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row==null){
            //Row Inflation
            LayoutInflater inflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row= inflater.inflate(R.layout.grid_item, parent, false);
        }
        //get item
        RssItem item = getItem(position);

        /**
         * Get References to Layout Ids
         */
        //get reference to ImageView
        image = (ImageView) row.findViewById(R.id.gridview_image);
        imageLoader.DisplayImage(item.getImage(), image);
        //get reference to TextView - Description
        description = (TextView) row.findViewById(R.id.gridview_title);
        if(item.getTitle()!=null){
            String title = item.getTitle().toString();
            //shorten length of title
            int maxLength = (title.length() < 28)?title.length():28;
            description.setText(title.substring(0,maxLength)+"...");
        }


        return row;
    }

}
