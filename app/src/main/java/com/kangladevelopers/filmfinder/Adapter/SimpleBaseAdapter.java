package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.R;

import java.util.List;

/**
 * Created by HUIDROM on 6/12/2016.
 */
public class SimpleBaseAdapter extends BaseAdapter {

    Context context;
    List<String> list;
    LayoutInflater inflate;

    public SimpleBaseAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.simple_list,null);
        TextView textView = (TextView) convertView.findViewById(R.id.textView1);
        textView.setText(list.get(position));
        return convertView;
    }
}
