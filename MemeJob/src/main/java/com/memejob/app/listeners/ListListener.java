package com.memejob.app.listeners;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.memejob.app.R;
import com.memejob.app.data.RssItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;
import java.util.Random;

/**
 * Created by graeme on 19/04/14.
 */
public class ListListener implements AdapterView.OnItemClickListener {

    public ImageLoader imageLoader=ImageLoader.getInstance();
    private int position;
    private Context m_context;
    RelativeLayout parentView;
    int screenCenterX, screenCenterY;
    int startRotation;
    int randColor;
    int x_cord, y_cord;
    int activeChildView=5;

    // List item's reference
    List<RssItem> listItems;
    Activity activity;

    public ListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }


    /**
     * onItemClick
     * this is triggered when the gridview item is clicked
     * @param parent
     * @param view
     * @param pos
     * @param id
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {


        //set the original position
        position= pos;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(view.getContext()).build();
        ImageLoader.getInstance().init(config);

        Activity a = (Activity)view.getContext();
        a.setContentView(R.layout.activity_main);
        m_context = a;

        parentView = (RelativeLayout) a.findViewById(R.id.layoutview);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        screenCenterX = screenWidth / 2;
        screenCenterY = screenHeight / 2;

        loadJobs();


    }

    /**
     * loadJobs
     */
    private void loadJobs(){

        int basePosition=50;
        int leftPosition=40;

        for (int i = position; i < position+5; i++) {

            LayoutInflater inflate = (LayoutInflater) m_context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            randColor= RandomColor();
            final View m_view = inflate.inflate(R.layout.full_image, null);
            m_view.setBackgroundColor(randColor);



            //set the job title
            TextView title = (TextView) m_view.findViewById(R.id.job_description);
            if(listItems.get(i).getTitle()!=null){
                int dark=darkerRandomColor(true);
                title.setText(listItems.get(i).getTitle().toString());
                title.setBackgroundColor(dark);
            }
            int lightish= darkerRandomColor(false);
            RelativeLayout apply_button_bar= (RelativeLayout) m_view.findViewById(R.id.apply_button_bar);
            apply_button_bar.setBackgroundColor(lightish);


            //Set the image adapter image
            // ImageAdapter imageAdapter = new ImageAdapter(view.getContext());
            ImageView m_image = (ImageView) m_view.findViewById(R.id.full_image_view);
            //actually set it here:
            imageLoader.displayImage(listItems.get(i).getImage(), m_image);

            View swipe_view= (View) m_view.findViewById(R.id.main_area);
            setUpItem(swipe_view, m_view);
            //add button listener
            addListenerOnButton(m_view, m_image, i);

//
            //position each item using layoutParams, so they look like a pack of cards
            RelativeLayout.LayoutParams head_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);;
            head_params.setMargins(leftPosition, 10, 0, basePosition); //substitute parameters for left, top, right, bottom
            m_view.setLayoutParams(head_params);

            //add view to parent
            parentView.addView(m_view, 1);
            basePosition-=10;
            leftPosition-=8;
        }

        enableDisableViewGroup(parentView, false);
        setTopViewActive();

        //add view to parent

    }


    /**
     * setSwipeItem
     * @param m_image
     * @param m_view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setUpItem(final View m_image, final View m_view ){
//        startRotation = randInt(-1,1);
//        m_view.setRotation(startRotation);

        // Touch listener on the image layout to swipe image right or left.

        m_image.setOnTouchListener(new View.OnTouchListener() {
            int originalX = (int)m_view.getX();
            int originalY = (int)m_view.getY();
            int distanceX=0;
            int move=0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //clear any margin
                RelativeLayout.LayoutParams head_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);;
                head_params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
                m_view.setLayoutParams(head_params);


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x_cord = (int) event.getRawX();
                        y_cord = (int) event.getRawY();

                        int x_motion = (int) event.getX();
                        final int historySize=(event.getHistorySize())-1;

                        if (event.getHistorySize() > 0) {

                            int diff = x_motion-(int)event.getHistoricalX(historySize);

                            distanceX+=(diff*1.1);
                            move= (int) (distanceX*2.5);

                            m_view.setRotation((float) ((move) * (Math.PI /32)));

                            m_view.setX(move);
                            m_view.setY(y_cord - screenCenterY -100);
                        }

                        break;

                    case MotionEvent.ACTION_UP:
                        /**
                         * SLIDE OUT
                         */
                        if (m_view.getX() > 100  ) {

                            Animation mSlideOutTop = AnimationUtils.loadAnimation(m_context, R.anim.slide_right);
                            m_view.startAnimation(mSlideOutTop);
                            parentView.removeView(m_view);
                            activeChildView-=1;
                            setTopViewActive();
                        }
                        else if(m_view.getX()<-100){

                            Animation mSlideOutTop = AnimationUtils.loadAnimation(m_context, R.anim.slide_left);
                            m_view.startAnimation(mSlideOutTop);
                            parentView.removeView(m_view);
                            activeChildView-=1;
                            setTopViewActive();

                        }
                        else{
                            m_view.setRotation(0);
                            TranslateAnimation trans = new TranslateAnimation(m_view.getX(),originalX, m_view.getY() , originalY);
                            trans.setDuration(500);
                            m_view.startAnimation(trans);
                        }

                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }


    private void addListenerOnButton(final View view, final ImageView iv, final int pos) {


      final ToggleButton btnSubmit = (ToggleButton) view.findViewById(R.id.btn_job_details);
      final TextView description = (TextView) view.findViewById(R.id.long_description);

        //set textView colour
        float[] hsv = new float[3];
        int color = randColor;
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.7f; // value component
        color = Color.HSVToColor(hsv);

        description.setBackgroundColor(color);

        /**
         * Button Onclick Listener
         */
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(btnSubmit.isChecked()) {
                    // handle toggle on
                    iv.setVisibility(View.INVISIBLE);
                    description.setText(listItems.get(pos).getDescription().toString());
                    description.setVisibility(View.VISIBLE);
                } else {
                    // handle toggle off
                    iv.setVisibility(View.VISIBLE);
                    TextView description = (TextView) view.findViewById(R.id.long_description);
                    description.setVisibility(View.INVISIBLE);
                }
            }

        });


        /**
         * Load more button
         */
        final Button btnLoadMore = (Button) parentView.findViewById(R.id.button_load_more);
        /**
         * Button Onclick Listener
         */
        btnLoadMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadMore();
            }

        });

    }


    /**
     * Load More
     */
    private void loadMore(){

        position= position+5;
        activeChildView=5;
        loadJobs();
    }


    /**
     * Enables/Disables all child views in a view group.
     *
     * @param viewGroup the view group
     * @param enabled <code>true</code> to enable, <code>false</code> to disable
     * the views.
     */
    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }

    /**
     * setTopViewActive
     *
     */
    private void setTopViewActive(){
        View activeChild= parentView.getChildAt(activeChildView);
        activeChild.setEnabled(true);
        if (activeChild instanceof ViewGroup) {
            enableDisableViewGroup((ViewGroup) activeChild, true);
        }
    }


    /**
     * randInt
     * @param min
     * @param max
     * @return random number between min and max
     */
    private int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * RandomColor
     * @return random colour int
     */
    private int RandomColor(){

        Random rand = new Random();

        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        int randomColor = Color.rgb(r,g,b);

        return randomColor;

    }

    private int darkerRandomColor(boolean dark){

        float[] hsv = new float[3];
        int color = randColor;
        Color.colorToHSV(color, hsv);
        if(dark==true){
        hsv[2] *= 0.5f; // value component
        }
        else{
            hsv[2] *= 1.2f; // value component

        }
        color = Color.HSVToColor(hsv);

        return color;
    }



}
