package com.kangladevelopers.filmfinder.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BURNI
 */
public class NavDrawerListAdapter {//extends BaseAdapter {
    /*
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private HashMap<String,View> viewHolderMap;
    private boolean obtainScreenLimit=false;
    String currentPage="";
    int screenLimit;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        this.viewHolderMap = new HashMap<>();
    }

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems,String currentPage){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        this.viewHolderMap = new HashMap<>();
        this.currentPage = currentPage;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.navigation_drawer, null);

            //System.out.println("getView " + position + " " + convertView);
            viewHolderMap.put(String.valueOf(position),convertView);

        }else{
            if(obtainScreenLimit==false){
                screenLimit = position;
                obtainScreenLimit=true;
            }
            Log.w("TAG"," pass screen limit");
            int removeViewPosition = screenLimit - position;
            String.valueOf(removeViewPosition);if(viewHolderMap.containsKey(removeViewPosition)){
                viewHolderMap.remove(String.valueOf(removeViewPosition));
            }else{
                Log.e("Adapter Error","ERROR");
            }

            viewHolderMap.put(String.valueOf(position), convertView);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        txtTitle.setTypeface(font);
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        if(currentPage.equals(navDrawerItems.get(position).getTitle())){
            txtTitle.setTextColor(Color.parseColor("#0072bc"));
        }else{
            //Log.w("DIFF",currentPage + " "+navDrawerItems.get(position).getTitle());
        }

        NavDrawerViewHolder navDrawerViewHolder=new NavDrawerViewHolder(imgIcon,txtTitle);
        convertView.setTag(navDrawerViewHolder);

        return convertView;
    }

    //---------------------

    public View getViewHolder(int position){
        View viewHolder=null;
        int size = viewHolderMap.size();
        Log.w("TAG","Position: " + String.valueOf(position)+ "  MAP size: "+ String.valueOf(size));
        for(int i=0;i<size;i++){
            Log.v("TAG","Available keys: " + viewHolderMap.get(String.valueOf(i)));
        }

        if(viewHolderMap.containsKey(String.valueOf(position))){
            viewHolder = viewHolderMap.get(String.valueOf(position));
        }
        return  viewHolder;
    }*/



}
