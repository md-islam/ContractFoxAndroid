package com.example.jakubkalinowski.contractfoxandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jakubkalinowski on 4/22/16.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    /**
     * ArrayAdapter that converts code to the view
     * @param context
     * @param notification
     */
    public CustomAdapter(Context context, String[] notification) {
        super(context, R.layout.custom_row, notification);
    }

    /**
     * This is how we want to lay out the strings that we passed in
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflater means prepare for rendering, getContext=background info
        LayoutInflater navInflater = LayoutInflater.from(getContext());

        View rowView = convertView;
        if (rowView == null){
            rowView = navInflater.inflate(R.layout.custom_row, parent, false);
        }

        String singleNotificationItem = getItem(position);
        TextView notificationText = (TextView) rowView.findViewById(R.id.notificationTextView);
        ImageView notificationImage = (ImageView) rowView.findViewById(R.id.notificationImageView);

        //assign data
        notificationText.setText(singleNotificationItem);
        //notificationImage.setImageResource(R.drawable.messageicon); //this only shows one icon for all notification
        //TODO: add notification images to array and pass it in here
        String currentIcon = singleNotificationItem;
        if(currentIcon.startsWith("U")){
            notificationImage.setImageResource(R.drawable.serviceicon);
        } else if(currentIcon.startsWith("N")){
            notificationImage.setImageResource(R.drawable.reviewicon);
        } else if(currentIcon.startsWith("P")){
            notificationImage.setImageResource(R.drawable.priceestimate);
        } else {
            notificationImage.setImageResource(R.drawable.logo);
        }
        return rowView;
    }
}
