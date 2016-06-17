package com.kangladevelopers.filmfinder.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.DataModel.MoveInfo;
import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.StringUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUIDROM on 6/12/2016.
 */
public class MovieInfoAdapter extends BaseAdapter {

    private List<MovieInfo2> feedItemList = new ArrayList<>();
    private Context mContext;

    public MovieInfoAdapter(Context context, List<MovieInfo2> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return feedItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItemList.get(position);
    }

    public void setData(List<MovieInfo2> feedItemList){
        this.feedItemList =feedItemList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        convertView = LayoutInflater.from(mContext).inflate(R.layout.block_move_info,null);
        viewHolder.moveIcon = (ImageView) convertView.findViewById(R.id.iv_moveIcon);
        viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
        viewHolder.tvDirector = (TextView) convertView.findViewById(R.id.tv_director);
        viewHolder.tvCast = (TextView) convertView.findViewById(R.id.tv_cast);
        viewHolder.tvYear = (TextView) convertView.findViewById(R.id.tv_year);
        convertView.setTag(viewHolder);
        final MovieInfo2 current_card = feedItemList.get(position);
        String name = current_card.getName();
        String director = current_card.getDirector();
        int year = current_card.getYear();
        String imageUrl = "";
        String cast = current_card.getStarring();

        //-----
        viewHolder.tvName.setText("Movie: "+name);
        viewHolder.tvDirector.setText("Directed by: "+director);
        viewHolder.tvCast.setText("Cast: " +cast);
        viewHolder.tvYear.setText("Year: "+year);

        if (!imageUrl.equals("")) {
            /*ImageLoader imageLoader = VolleySingleton.getInstance(mContext).getImageLoader();
            imageLoader.get(profileImage, ImageLoader.getImageListener(holder.profilePic, R.drawable.ic_profile_pic, R.drawable.ic_profile_pic));*/

        } else {
            viewHolder.moveIcon.setImageResource(R.drawable.mov_icon);
        }
        return convertView;
    }

    private static class ViewHolder {
        CardView cv;
        ImageView moveIcon;
        TextView tvName;
        TextView tvCast;
        TextView tvDirector;
        TextView tvYear;
    }
}
