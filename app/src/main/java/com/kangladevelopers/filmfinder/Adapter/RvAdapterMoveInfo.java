package com.kangladevelopers.filmfinder.Adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.DataModel.MoveInfo;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.StringUtility;


/**
 * Created by DELL PC on 2/16/2016.
 */
public class RvAdapterMoveInfo extends RecyclerView.Adapter<RvAdapterMoveInfo.CustomViewHolder>{

    private List<MoveInfo> feedItemList = new ArrayList<>();
    private Context mContext;
    String LogTAG = "RvAdapter";

    //---------------
    ArrayList<CustomViewHolder> viewHolders= new ArrayList<>();
    //---------------

    public RvAdapterMoveInfo() {
    }

    public RvAdapterMoveInfo(Context context, List<MoveInfo> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    public void setfeedItem(Context context, List<MoveInfo> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    public List<MoveInfo> getData(){
        return feedItemList;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.block_move_info, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        viewHolders.add(viewHolder);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        //Log.v(LogTAG, "onBindViewHolder, Position: " + String.valueOf(position));

        final MoveInfo current_card = feedItemList.get(position);
        String name = current_card.name;
        String director = current_card.director;
        String year = current_card.year;
        String imageUrl = current_card.imageUrl;
        String cast = StringUtility.getAllValuesAsString(current_card.getCast());

        //-----
        holder.tvName.setText("Movie: "+name);
        holder.tvDirector.setText("Directed by: "+director);
        holder.tvCast.setText("Cast: " +cast);
        holder.tvYear.setText("Year: "+year);


        if (!imageUrl.equals("")) {
            /*ImageLoader imageLoader = VolleySingleton.getInstance(mContext).getImageLoader();
            imageLoader.get(profileImage, ImageLoader.getImageListener(holder.profilePic, R.drawable.ic_profile_pic, R.drawable.ic_profile_pic));*/

        } else {
            holder.moveIcon.setImageResource(R.drawable.mov_icon);
        }

    }


    @Override
    public int getItemCount() {
        return feedItemList.size();
    }
    //----------------------------------------------------------


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        ImageView moveIcon;
        TextView tvName;
        TextView tvCast;
        TextView tvDirector;
        TextView tvYear;

        public CustomViewHolder(View view) {
            super(view);

            moveIcon = (ImageView) view.findViewById(R.id.iv_moveIcon);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvDirector = (TextView) view.findViewById(R.id.tv_director);
            tvCast = (TextView) view.findViewById(R.id.tv_cast);
            tvYear = (TextView) view.findViewById(R.id.tv_year);
            cv = (CardView) view;
            cv.setOnClickListener(this);

            /*Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");
            tvName.setTypeface(font);
            Typeface font1 = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
            tvDirector.setTypeface(font1);
            tvCast.setTypeface(font1);
            tvYear.setTypeface(font1);*/
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(mContext, "Item click at: " + String.valueOf(getAdapterPosition() + 1), Toast.LENGTH_SHORT).show();
            rvAdapterClickListener.onItemClick(getAdapterPosition(),v);
        }
    }

    //-----------------------------------INTERFACE----------------------------

    public interface RvAdapterClickListener {
        void onItemClick(int i,View v);
    }
    private RvAdapterClickListener rvAdapterClickListener;
    //----------------------
    public void setRvAdapterClickLIstener(RvAdapterClickListener rvAdapterClickListener) {
        this.rvAdapterClickListener = rvAdapterClickListener;
    }


}

