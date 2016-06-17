/*
package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

import java.util.List;

*/
/**
 * Created by HUIDROM on 3/7/2016.
 *//*

public class ActorAutoCompleteAdapter  extends BaseAdapter{

    private Context context;
    private List<String> data;
    private LayoutInflater inflater;
    public ActorAutoCompleteAdapter(Context context,List<String> data){
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.textview1,null);
        TextView textView = (TextView) convertView.findViewById(R.id.textView1);
        return convertView;
    }


}
*/
